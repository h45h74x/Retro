package Retro.Menu;

import Retro.Const;
import Retro.Input.Input_RButton;
import Retro.Input.Input_TextField;
import Retro.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu_Main extends Menu {
    public Menu_Main(Launcher main) {
        super(main);
    }

    protected void setup() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel t1 = new JLabel();
        t1.setText(Const.Strings.greeting);
        t1.setForeground(black);
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 72));
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);

        Input_TextField tf1 = new Input_TextField();
        tf1.setText(Const.Strings.username);
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
                if (!tf1.getText().equals("")) Const.Strings.username = tf1.getText();
                else Const.Strings.username = "Player";
                Launcher.con.printlnInfo(Const.Strings.username);
            }
        });

        Input_RButton b1 = new Input_RButton(Const.Strings.choose_game);
        b1.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.addActionListener(e -> start());

        Input_RButton b2 = new Input_RButton(Const.Strings.credits);
        b2.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.addActionListener(e -> credits());

        Input_RButton b3 = new Input_RButton(Const.Strings.exit);
        b3.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b3.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.addActionListener(e -> Launcher.kill());

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(t1);
        add(tf1);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(b1);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b2);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b3);

        tf1.requestFocus();
        tf1.setCaretPosition(tf1.getText().length());
    }

    private void start() {
        Launcher.extStartMenu(Const.Menues.GAME_SELECTOR);
    }

    private void credits() {
        Launcher.extStartMenu(Const.Menues.CREDITS);
    }

    public void playSound(String path) {

    }


    @Override
    protected void key_W(boolean pressed) {
    }

    @Override
    protected void key_A(boolean pressed) {

    }

    @Override
    protected void key_S(boolean pressed) {
    }

    @Override
    protected void key_D(boolean pressed) {

    }

    @Override
    protected void key_SPACE(boolean pressed) {
        start();
    }

    @Override
    protected void key_ESC(boolean pressed) {
        if (pressed) Launcher.kill();
    }
}
