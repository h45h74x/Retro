package Retro.UI;

import Retro.Const;
import Retro.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InvButton extends JButton {
    public InvButton() {
        super();
        makeInvisible();
    }

    public InvButton(ActionListener e) {
        super();
        addActionListener(e);
        makeInvisible();
    }

    public InvButton(String i, ActionListener l) {
        super();
        addActionListener(l);
        try {
            setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(i)).getScaledInstance(Const.UI.scaledSize, Const.UI.scaledSize, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            Launcher.con.printlnError(e);
        }
        makeInvisible();
    }

    public InvButton(String i, int scale, ActionListener l) {
        super();
        addActionListener(l);
        try {
            setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(i)).getScaledInstance(scale, scale, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            Launcher.con.printlnError(e);
        }
        makeInvisible();
    }

    private void makeInvisible() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(false);
    }
}
