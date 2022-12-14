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