package Retro.Menu;

import Retro.Const;
import Retro.Game.Game;
import Retro.UI.RoundButton;
import Retro.Launcher;

import javax.swing.*;
import java.awt.*;

public class GameOver extends Menu {

    public GameOver(Game parent) {
        super(Launcher.getMain(), parent.getName());
        Game parent1 = parent;
    }

    public GameOver(Launcher main) {
        super(main);
    }


    @Override
    protected void setup() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        redGradient();

        JLabel t1 = new JLabel();
        t1.setText(Const.Strings.game_over);
        t1.setForeground(black);
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 72));
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundButton b1 = new RoundButton(Const.Strings.retry);
        b1.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.addActionListener(e -> Launcher.extStartGame());

        RoundButton b2 = new RoundButton(Const.Strings.back_to_mm);
        b2.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.addActionListener(e -> Launcher.home());

        RoundButton b3 = new RoundButton(Const.Strings.exit);
        b3.setMaximumSize(new Dimension((int) (frame.getWidth() * 0.9), 50));
        b3.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.addActionListener(e -> Launcher.kill());

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(t1);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(b1);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b2);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b3);
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
        if (pressed) Launcher.extStartGame();
    }

    @Override
    protected void key_ESC(boolean pressed) {
        if (pressed) Launcher.home();
    }
}
