import com.formdev.flatlaf.FlatDarkLaf;
import things.ui.frames.WindMainFrame;

public class WindJADCreator {
    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        }
        catch (NoClassDefFoundError error) {
            error.printStackTrace();
        }

        var mainFrame = WindMainFrame.getInstance();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.revalidate();
        mainFrame.setVisible(true);
    }
}