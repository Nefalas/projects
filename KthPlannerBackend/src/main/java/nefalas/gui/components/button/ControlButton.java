package nefalas.gui.components.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Callable;

public class ControlButton extends JButton implements MouseListener {

    public enum COLOR {
        RED,
        GREEN
    }

    private static final Color RED = new Color(209, 76, 67);
    private static final Color DARK_RED = new Color(197, 70, 61);

    private static final Color GREEN = new Color(82, 178, 94);
    private static final Color DARK_GREEN = new Color(79, 172, 91);

    private COLOR color;

    private Callable action = null;

    public ControlButton(String text, COLOR color) {
        this.color = color;

        setText(text);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setRolloverEnabled(true);
        setFocusPainted(false);
        setBackground(getColor());

        addMouseListener(this);
    }

    private Color getColor() {
        switch (color) {
            case RED:
                return RED;
            case GREEN:
                return GREEN;
            default:
                return Color.WHITE;
        }
    }

    private Color getDarkColor() {
        switch (color) {
            case RED:
                return DARK_RED;
            case GREEN:
                return DARK_GREEN;
            default:
                return Color.GRAY;
        }
    }

    public void setClickAction(Callable action) {
        this.action = action;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (action != null) {
            try {
                action.call();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(getDarkColor());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(getColor());
    }

}