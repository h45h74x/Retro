package Retro.Menu;

import Retro.Const;
import Retro.Input.Input_RButton;
import Retro.Launcher;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Menu_Credits extends Menu {

    public Menu_Credits(Launcher main) {
        super(main);
    }

    @Override
    protected void setup() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel t1 = new JLabel(Const.Strings.name);
        t1.setForeground(Const.Colors.elements());
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 65));
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea t2 = new JTextArea(Const.Strings.creditText);
        t2.setForeground(Const.Colors.elements());
        t2.setEditable(false);
        t2.setOpaque(false);
        t2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        t2.setAlignmentX(Component.CENTER_ALIGNMENT);
        t2.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), Const.Numbers.creditLength));

        Input_RButton b1 = new Input_RButton(Const.Strings.github);
        b1.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.addActionListener(e -> openLink(Const.Strings.github_link));

        Input_RButton b2 = new Input_RButton(Const.Strings.website_h45);
        b2.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.addActionListener(e -> openLink(Const.Strings.website_h45_link));

        Input_RButton b3 = new Input_RButton(Const.Strings.back);
        b3.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b3.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.addActionListener(e -> back());

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(t1);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(t2);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(b1);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b2);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(b3);
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            Launcher.con.printlnError(e.toString());
        }
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

    }

    @Override
    protected void key_ESC(boolean pressed) {

    }
}
