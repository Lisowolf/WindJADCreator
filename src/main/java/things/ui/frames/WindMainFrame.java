package things.ui.frames;

import things.ui.components.WindMenuBar;
import things.ui.components.WindTabbedPane;

import javax.swing.*;

public class WindMainFrame extends JFrame {
    private static final WindMainFrame instance = new WindMainFrame();

    private WindMainFrame() {
        super();
        this.add(new WindTabbedPane());
        this.setJMenuBar(new WindMenuBar());
        this.setTitle("Wind JAD Creator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.revalidate();
    }

    public static WindMainFrame getInstance() {
        return instance;
    }
}