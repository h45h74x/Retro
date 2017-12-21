import javax.swing.*;
import java.awt.*;

public class ConsoleFrame extends JFrame {
    private JTextArea log;
    private int count;
    private boolean showLines = false;

    public ConsoleFrame(int x, int y, int w, int h) {
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

    public void display() {
        setVisible(true);
    }
}
