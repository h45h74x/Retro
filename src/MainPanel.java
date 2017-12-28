import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainPanel extends Game {
    private Color c1;
    private Color c2;

    private MainMenu frame;
    private RetroMain main;

    public MainPanel(Color c1, Color c2, RetroMain main) {
        super(main.getMainFrame());
        this.c1 = c1;
        this.c2 = c2;
        this.frame = main.getMainFrame();
        this.main = main;

        setBackground(Const.Colors.background());
        setup();
        setOpaque(true);
        setVisible(true);
    }

    private void setup() {
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
        tf1.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        tf1.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (!tf1.getText().equals("")) Const.Variables.username = tf1.getText();
                else Const.Variables.username = "Player";
                RetroMain.con.println(Const.Variables.username);
            }
        });

        RButton b1 = new RButton(Const.Strings.start);
        b1.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RetroMain.con.println("Start");
                main.startGame(Const.Strings.games.length - 1);
            }
        });

        RButton b2 = new RButton(Const.Strings.options);
        b2.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RetroMain.con.println("Options");
            }
        });

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(t1);
        add(tf1);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(b1);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b2);

        tf1.requestFocus();
        tf1.setCaretPosition(tf1.getText().length());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        Paint gradient = new GradientPaint(
                0, 0, c1,
                0, height, c2,
                true
        );
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
    }

    public void key_W(boolean pressed) {
        RetroMain.con.println("W");
    }

    public void key_A(boolean pressed) {
        RetroMain.con.println("A");
    }

    public void key_S(boolean pressed) {
        RetroMain.con.println("S");
    }

    public void key_D(boolean pressed) {
        RetroMain.con.println("D");
    }

    public void key_ESC(boolean pressed) {
        RetroMain.con.println("ESC");
    }

    public void key_SPACE(boolean pressed) {
        RetroMain.con.println("SPACE");
    }
}
