package things.ui.components;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class WindMainTabPanel extends JPanel {
    private final JLabel descriptionLabel = new JLabel("""
                <html>
                    <p align="center">
                        Drag and drop JAR-files here.
                    </p>
                </html>""");

    public WindMainTabPanel() {
        super(new CardLayout());

        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        descriptionLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(descriptionLabel);
    }
}