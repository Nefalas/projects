package helpers;

import java.awt.*;

/**
 * Class helpers.Helpers
 * A class for adding useful methods
 */
public class Helpers {

    /**
     * Draw string width line break at max width
     * @param g the game graphics
     * @param text the string to draw
     * @param x the upper left corner X position
     * @param y the upper left corner Y position
     * @param width the max width
     */
    public static void drawStringWidth(Graphics g, String text, int x, int y, int width) {
        String[] words = text.split(" ");
        FontMetrics metrics = g.getFontMetrics();
        String tempText = "";
        String finalText = "";
        for (String word : words) {
            if (metrics.stringWidth(tempText + word.replace("\n", "")) <= width) {
                if (word.contains("\n")) {
                    tempText += word.split("\n")[0];
                    finalText += tempText + "\n\n";
                    tempText = word.split("\n")[1] + " ";
                } else {
                    tempText += word + " ";
                }
            } else {
                finalText += tempText + "\n";
                tempText = word + " ";
            }
        }
        if (!tempText.equals("")) {
            finalText += tempText;
        }
        drawStringWithLineBreak(g, finalText, x, y);
    }

    /**
     * Draws a String in the middle of the given rectangle
     * @param rectX x start position of the rectangle
     * @param rectY y start position of the rectangle
     * @param rectW width of the rectangle
     * @param rectH height of the rectangle
     * @param g game Graphics
     * @param text String to draw
     * @param font font to use for the String
     */
    public static void drawCenteredString(int rectX, int rectY, int rectW, int rectH, Graphics g, String text, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rectX + (rectW - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rectY + ((rectH - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    /**
     * Draw strings including line breaks
     * @param g the game graphics
     * @param text the string to draw
     * @param x the upper left corner X position
     * @param y the upper left corner Y position
     */
    private static void drawStringWithLineBreak(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

}
