package nefalas.gui.mainwindow.console;

import nefalas.gui.components.text.ColorPane;
import nefalas.gui.components.scrolling.ModernScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class ConsolePanel extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(34, 34, 34);

    public ColorPane console;

    public ConsolePanel() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        createConsole();

        ModernScrollPane scrollPane = new ModernScrollPane(console, ModernScrollPane.SCROLLBAR_COLOR.LIGHT, 10);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createConsole() {
        console = new ColorPane();
        DefaultCaret caret = (DefaultCaret) console.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        console.setBackground(BACKGROUND_COLOR);
        console.setForeground(Color.WHITE);
        console.setBorder(null);
        console.setFont(new Font("Monospaced", Font.PLAIN, 14));
    }

}
