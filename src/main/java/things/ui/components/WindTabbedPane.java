package things.ui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class WindTabbedPane extends JTabbedPane {
    public WindTabbedPane() {
        super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        this.setPreferredSize(new Dimension(360, 360));
        this.addTab("How to use", new WindMainTabPanel());
        this.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dropEvent) {
                try {
                    dropEvent.acceptDrop(DnDConstants.ACTION_COPY);
                    var transferData = dropEvent.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    var filesList = (List<File>) transferData;
                    var notOpenedFiles = new ArrayList<String>();

                    for (var file : filesList) {
                        try {
                            var jarFile = new JarFile(file);
                            var manifest = jarFile.getManifest();

                            manifest.getMainAttributes().putValue("MIDlet-Jar-URL", file.getName());
                            manifest.getMainAttributes().putValue("MIDlet-Jar-Size", String.valueOf(file.length()));

                            if (WindTabbedPane.this.getComponentAt(0) instanceof WindMainTabPanel) {
                                WindTabbedPane.this.removeTabAt(0);
                            }
                            WindTabbedPane.this.addTab(file.getName(), new WindEditPanel(manifest, jarFile));
                        }
                        catch (Exception exception) {
                            exception.printStackTrace();
                            notOpenedFiles.add(file.getName());
                        }
                    }
                    if (notOpenedFiles.size() > 0) {
                        var errorMessage = new StringBuilder("Failed to open these files:");
                        for (var fileName : notOpenedFiles) {
                            errorMessage.append("\n").append(fileName);
                        }
                        JOptionPane.showMessageDialog(WindTabbedPane.this,
                                errorMessage,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(WindTabbedPane.this,
                            "An error occurred while accepting files.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @SuppressWarnings("FieldCanBeLocal")
    private class WindEditPanel extends JPanel {
        private final GridBagConstraints constraints = new GridBagConstraints();
        private final WindTabbedPane tabbedPane = WindTabbedPane.this;
        private final JButton saveJadButton = new JButton("Save JAD...");
        private final JButton cancelButton = new JButton("Cancel");
        private final DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        public WindEditPanel(Manifest manifest, JarFile jarFile) {
            super(new GridBagLayout());

            tableModel.addColumn("Name");
            tableModel.addColumn("Value");
            for (var attribute : manifest.getMainAttributes().keySet()) {
                var name = attribute.toString();
                var value = manifest.getMainAttributes().getValue(name);
                tableModel.addRow(new Object[] { name, value, "Delete" });
            }

            saveJadButton.addActionListener(e -> {
                var fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(jarFile.getName().replaceAll("jar", "jad")));
                fileChooser.showSaveDialog(this);

                SwingUtilities.invokeLater(() -> {
                    try {
                        manifest.write(new FileOutputStream(fileChooser.getSelectedFile()));
                    }
                    catch (IOException exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(WindTabbedPane.this,
                                "Failed to save the file.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });
            });

            cancelButton.addActionListener(e -> {
                tabbedPane.remove(this);
                if (tabbedPane.getTabCount() == 0) {
                    tabbedPane.addTab("How to use", new WindMainTabPanel());
                }
            });

            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;
            constraints.gridwidth = 2;
            this.add(new JScrollPane(new JTable(tableModel)), constraints);

            constraints.insets.set(6, 6, 6,6);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridy = 1;
            constraints.weighty = 0;
            constraints.gridwidth = 1;
            constraints.ipady = 8;
            this.add(saveJadButton, constraints);

            constraints.insets.set(6, 0, 6,6);
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.EAST;
            constraints.gridx = 1;
            constraints.weightx = 0;
            this.add(cancelButton, constraints);
            this.revalidate();
        }
    }
}