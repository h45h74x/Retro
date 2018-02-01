package Retro.Debug;

import Retro.Const;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

@SuppressWarnings("ConstantConditions")
public class Debug_Console extends JFrame {
    private static StyledDocument doc = null;
    private static Style style = null;
    private int count;

    public Debug_Console(Retro.UI.Frame frame) {
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

        scroll.getVerticalScrollBar().addAdjustmentListener(e -> e.getAdjustable().setValue(e.getAdjustable().getMaximum()));

        countUp();
        setBounds(x + w, y, Const.Numbers.con_width, h);
        add(scroll);

        setResizable(false);
        display();
    }

    public static void staticPrintlnError(Exception t) {
        if (doc != null) {
            StyleConstants.setForeground(style, Color.red);
            try {
                doc.insertString(doc.getLength(), t.toString(), style);
            } catch (BadLocationException ignored) {
            }
        } else t.printStackTrace();
    }

    private void resetColor() {
        StyleConstants.setForeground(style, Color.green);
    }

    private void print(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.green);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException ignored) {
        }
    }

    private void printError(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.red);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException ignored) {
        }
    }

    private void printWarning(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.yellow);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException ignored) {
        }
    }

    public void printInfo(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.blue);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException ignored) {
        }
    }

    private void printQuiet(String t) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, Color.lightGray);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException ignored) {
        }
    }

    private void print(String t, Color c) {
        //log.setText(log.getText() + t);
        StyleConstants.setForeground(style, c);
        try {
            doc.insertString(doc.getLength(), t, style);
        } catch (BadLocationException ignored) {
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

    private void printlnQuiet(String t) {
        printQuiet(t + "\n");
        countUp();
    }

    private void countUp() {
        boolean showLines = false;
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

    private void display() {
        if (Const.Bools.debug) setVisible(true);
        else setVisible(false);
    }
}