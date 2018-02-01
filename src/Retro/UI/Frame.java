package Retro.UI;

import Retro.Const;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame(String name) {
        setTitle(name);

        setIconImage(Const.getImage(Const.Strings.iconpaths[0]));

        getContentPane().setBackground(Const.Colors.background());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(Const.Numbers.width, Const.Numbers.height);
        setLocationRelativeTo(null);
        if (Const.Bools.debug) setLocation(getX() - 200, getY());

        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        requestFocus();
    }
}
