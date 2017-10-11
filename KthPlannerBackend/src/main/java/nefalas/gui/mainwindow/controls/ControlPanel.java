package nefalas.gui.mainwindow.controls;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ControlPanel extends JPanel {

    public ControlPanel() {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(0,3,10,0));
        setBorder(new EmptyBorder(10,10,10,10));

        add(new ControlBox());
        add(new SessionSettingBox());
        add(new ServerSettingsBox());
    }

}
