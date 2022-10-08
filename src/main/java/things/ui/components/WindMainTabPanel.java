/*
 * Copyright 2022 Kirill Lomakin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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