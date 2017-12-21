import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RetroMain {

    public static ConsoleFrame con;

    public static void main(String[] args) {
        MainMenu f1 = new MainMenu(Const.Strings.name);
        con = new ConsoleFrame(f1.getX(), f1.getY(), f1.getWidth(), f1.getHeight());
        Const.LoadFonts();

        MainPanel p1 = new MainPanel(Const.Colors.accent_dark(), Const.Colors.accent());
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

        JLabel t1 = new JLabel();
        t1.setText(Const.Strings.title);
        t1.setForeground(Const.Colors.elements());
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 72));
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);

        InputField tf1 = new InputField();
        tf1.setText(Const.Variables.username);
        tf1.setFont(new Font("Noto Sans", Font.PLAIN, 40));
        tf1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tf1.setHorizontalAlignment(SwingConstants.CENTER);
        tf1.setMaximumSize(new Dimension((int) (f1.getWidth() * 0.9), 50));
        tf1.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (!tf1.getText().equals("")) Const.Variables.username = tf1.getText();
                else Const.Variables.username = "Player";
                con.println(Const.Variables.username);
            }
        });

        RButton b1 = new RButton(Const.Strings.start);
        b1.setMaximumSize(new Dimension((int) (f1.getWidth() * 0.9), 50));
        b1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.println("Start");
            }
        });

        RButton b2 = new RButton(Const.Strings.options);
        b2.setMaximumSize(new Dimension((int) (f1.getWidth() * 0.9), 50));
        b2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.println("Options");
            }
        });

        p1.add(Box.createRigidArea(new Dimension(0, 100)));
        p1.add(t1);
        p1.add(tf1);
        p1.add(Box.createRigidArea(new Dimension(0, 50)));
        p1.add(b1);
        p1.add(Box.createRigidArea(new Dimension(0, 10)));
        p1.add(b2);
        f1.add(p1);
        f1.display();

        if (Const.Bools.debug) {
            con.display();
        }
        f1.requestFocus();
        tf1.requestFocus();
        tf1.setCaretPosition(tf1.getText().length());
    }
}
