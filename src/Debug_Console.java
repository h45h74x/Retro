import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Debug_Console extends JFrame {
    private StyledDocument doc;
    private Style style;
    private int count;
    private boolean showLines = false;

    public Debug_Console(UI_Frame frame) {
        int x = frame.getX();
        int y = frame.getY();
        int w = frame.getWidth();
        int h = frame.getHeight();

        JTextPane log = new JTextPane();
        doc = log.getStyledDocument();
        style = log.addStyle("I'm a Style", null);

        setTitle("Debug_Console");
        count = 0;

        resetColor();
        log.setEditable(false);
        log.setForeground(Color.GREEN);
        log.setBackground(Color.black);
        log.setFont(new Font("Bitstream Vera Sans Mono", Font.PLAIN, 10));

        JScrollPane scroll = new JScrollPane(log);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });

        countUp();
        setBounds(x + w, y, 200, h);
        add(scroll);

        display();
    }

    public void resetColor() {
        StyleConstants.setForeground(style, Color.green);
    }

    public void print(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.green);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException e) {
        }
    }

    public void printError(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.red);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException e) {
        }
    }

    public void printWarning(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.yellow);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException e) {
        }
    }

    public void printInfo(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.blue);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException e) {
        }
    }

    public void printQuiet(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.lightGray);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException e) {
        }
    }

    public void print(String t, Color c) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, c);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException e) {
        }
    }

    public void println(String t) {
        print(t + "\n");
        countUp();
    }

    public void println(String t, Color c) {
        print(t + "\n", c);
        countUp();
    }

    public void printlnError(String t) {
        printError(t + "\n");
        countUp();
    }

    public void printlnWarning(String t) {
        printWarning(t + "\n");
        countUp();
    }

    public void printlnInfo(String t) {
        printInfo(t + "\n");
        countUp();
    }

    public void printlnQuiet(String t) {
        printQuiet(t + "\n");
        countUp();
    }

    private void countUp() {
        if (!showLines) return;
        count++;
        print(count + ": ");
    }

    public void println(int t) {
        print(String.valueOf(t) + "\n");
        countUp();
    }

    public void spacer(String symbol) {
        String output = symbol;
        for (int i = 0; i < 6; i++) output += output;
        printlnQuiet(output);
    }

    public void spacer() {
        printlnQuiet("");
    }

    public void display() {
        if (Const.Bools.debug) setVisible(true);
        else setVisible(false);
    }
}
