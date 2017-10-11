package nefalas.gui.mainwindow.console;

import nefalas.gui.components.text.ColorPane;

import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutput extends OutputStream {

    private ColorPane console;

    public ConsoleOutput(ColorPane colorPane) {
        console = colorPane;
    }

    public void write(int b) throws IOException {
        console.appendANSI(String.valueOf((char)b));
    }

}
