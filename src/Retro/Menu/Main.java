package Retro.Menu;

import Retro.Const;
import Retro.Launcher;
import Retro.Managers.Web;
import Retro.UI.InvButton;
import Retro.UI.InvTextField;
import Retro.UI.RoundButton;
import Retro.UI.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Menu {
    private InvTextField tf1;
    private Web web = new Web();

    public Main(Launcher main) {
        super(main);
    }

    private JPanel createMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        menu.setOpaque(false);

        JLabel t1 = new JLabel();
        t1.setText(Const.Strings.greeting);
        t1.setForeground(black);
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 72));
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);

        tf1 = new InvTextField();
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

        RoundButton b1 = new RoundButton(Const.Strings.choose_game);
        b1.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.addActionListener(e -> start());

        RoundButton b2 = new RoundButton(Const.Strings.credits);
        b2.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.addActionListener(e -> credits());

        JLabel howto = new JLabel(Const.Strings.howto);
        howto.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        howto.setAlignmentX(Component.CENTER_ALIGNMENT);

        menu.add(Box.createRigidArea(new Dimension(0, 60)));
        menu.add(t1);
        menu.add(tf1);
        menu.add(Box.createRigidArea(new Dimension(0, 50)));
        menu.add(b1);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(b2);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(howto);

        return menu;
    }

    private JPanel createBottomBar() {
        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(new BorderLayout());
        bottomBar.setOpaque(false);

        JPanel social = new JPanel();
        social.setOpaque(false);
        InvButton git = new InvButton(Const.UI.web[0], e -> web.openInBrowser(Const.Strings.github_link));
        social.add(git);
        InvButton insta = new InvButton(Const.UI.web[1], e -> web.openInBrowser(Const.Strings.instagram_link));
        social.add(insta);
        InvButton website = new InvButton(Const.UI.web[2], 30, e -> web.openInBrowser(Const.Strings.website_h45_link));
        social.add(website);
        bottomBar.add(social, BorderLayout.LINE_END);

        JPanel settings = new JPanel();
        settings.setOpaque(false);
        InvButton sound = new InvButton(Const.UI.sound[0], 30, e -> toggleSound());
        settings.add(sound);
        InvButton music = new InvButton(Const.UI.music[0], 30, e -> toggleMusic());
        settings.add(music);
        InvButton debug = new InvButton(Const.UI.debug[0], 30, e -> toggleDebug());
        settings.add(debug);
        bottomBar.add(settings, BorderLayout.LINE_START);

        return bottomBar;
    }

    private void toggleDebug() {
        Const.Bools.debug = !Const.Bools.debug;
        Launcher.restart();
    }

    private void toggleMusic() {
        Const.Bools.music = !Const.Bools.music;
    }

    private void toggleSound() {
        Const.Bools.sounds = !Const.Bools.sounds;
    }

    protected void setup() {
        setLayout(new BorderLayout());

        add(createMenu(), BorderLayout.CENTER);
        add(createBottomBar(), BorderLayout.PAGE_END);
        add(new TopBar(), BorderLayout.PAGE_START);

        tf1.requestFocus();
        tf1.setCaretPosition(tf1.getText().length());
    }

    private void start() {
        Launcher.extStartMenu(Const.Menues.GAME_SELECTOR);
    }

    private void credits() {
        Launcher.extStartMenu(Const.Menues.CREDITS);
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
