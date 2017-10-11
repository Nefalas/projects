package nefalas.gui.mainwindow.controls;

import javax.swing.*;
import java.awt.*;

public class ServerSettingsBox extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);

    public ServerSettingsBox() {
        setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(new Label("Server settings", SwingConstants.CENTER));

        add(titlePanel, BorderLayout.PAGE_START);
    }

}
