import javax.swing.*;
import java.awt.*;

public class ConsoleFrame extends JFrame {
    private JTextArea log;
    private int count;
    private boolean showLines = false;

    public ConsoleFrame(MainMenu frame) {
        int x = frame.getX();
        int y = frame.getY();
        int w = frame.getWidth();
        int h = frame.getHeight();

        setTitle("Console");
        count = 0;
        log = new JTextArea();
        log.setLineWrap(true);
        log.setEditable(false);
        log.setForeground(Color.GREEN);
        log.setBackground(Color.black);
        log.setFont(new Font("Bitstream Vera Sans Mono", Font.PLAIN, 10));

        JScrollPane scroll = new JScrollPane(log);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        countUp();
        setBounds(x + w, y, 200, h);
        add(scroll);

        display();
    }

    public void print(String t) {
        log.setText(log.getText() + t);
    }

    private void countUp() {
        if (!showLines) return;
        count++;
        print(count + ": ");
    }

    public void println(String t) {
        print(t + "\n");
        countUp();
    }

    public void println(int t) {
        print(String.valueOf(t) + "\n");
        countUp();
    }

    public void spacer(String symbol) {
        String output = symbol;
        for (int i = 0; i < 6; i++) output += output;
        println(output);
    }

    public void spacer() {
        println("");
    }

    public void display() {
        if (Const.Bools.debug) setVisible(true);
    }
}
