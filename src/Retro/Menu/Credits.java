package Retro.Menu;

import Retro.Const;
import Retro.Launcher;
import Retro.Managers.Web;
import Retro.UI.RoundButton;

import javax.swing.*;
import java.awt.*;

public class Credits extends Menu {
    private Web web = new Web();

    public Credits(Launcher main) {
        super(main);
    }

    @Override
    protected void setup() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel t1 = new JLabel(Const.Strings.name);
        t1.setForeground(Const.Colors.elements());
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 65));
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea about = new JTextArea(Const.Strings.creditText);
        about.setForeground(Const.Colors.elements());
        about.setEditable(false);
        about.setOpaque(false);
        about.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        about.setAlignmentX(Component.CENTER_ALIGNMENT);
        about.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), Const.Numbers.creditLength));

        JTextArea thanks = new JTextArea(Const.Strings.creditText);
        thanks.setForeground(Const.Colors.elements());
        thanks.setEditable(false);
        thanks.setOpaque(false);
        thanks.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        thanks.setAlignmentX(Component.CENTER_ALIGNMENT);
        thanks.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), Const.Numbers.creditLength));

        RoundButton back = new RoundButton(Const.Strings.back);
        back.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        back.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(e -> back());

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(t1);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(about);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(thanks);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(back);
        add(Box.createRigidArea(new Dimension(0, 20)));
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
