package nefalas.gui.mainwindow.controls;

import javax.swing.*;
import java.awt.*;

public class ControlBox extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);

    public ControlBox() {
        setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(new Label("Controls", SwingConstants.CENTER));

        add(titlePanel, BorderLayout.PAGE_START);
    }

}
