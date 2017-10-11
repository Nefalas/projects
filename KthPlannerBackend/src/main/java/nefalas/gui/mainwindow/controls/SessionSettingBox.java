package nefalas.gui.mainwindow.controls;

import javax.swing.*;
import java.awt.*;

public class SessionSettingBox extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);

    public SessionSettingBox() {
        setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(new Label("Session settings", SwingConstants.CENTER));

        add(titlePanel, BorderLayout.PAGE_START);
    }

}
