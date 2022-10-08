package things.ui.components;

import things.ui.frames.WindMainFrame;

import javax.swing.*;

public class WindMenuBar extends JMenuBar {
    public WindMenuBar() {
        super();
        this.add(new WindFileMenu());
        this.add(new WindHelpMenu());
    }

    @SuppressWarnings("FieldCanBeLocal")
    private static class WindFileMenu extends JMenu {
        private final JMenuItem exitMenuItem = new JMenuItem("Exit");

        public WindFileMenu() {
            super("File");
            this.add(exitMenuItem);
        }
    }

    @SuppressWarnings("FieldCanBeLocal")
    private static class WindHelpMenu extends JMenu {
        private final JMenuItem showAboutMenuItem = new JMenuItem("About");

        public WindHelpMenu() {
            super("Help");
            showAboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(WindMainFrame.getInstance(),
                    """
                            Wind JAD Creator
                            Version: 1.0""",
                    "About",
                    JOptionPane.PLAIN_MESSAGE));
            this.add(showAboutMenuItem);
        }
    }
}