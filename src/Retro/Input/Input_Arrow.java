package Retro.Input;

import Retro.Const;
import Retro.Launcher;

import javax.swing.*;
import java.awt.*;

public class Input_Arrow extends JButton {

    public Input_Arrow(boolean left) {
        super();
        int width = 50;
        setForeground(Const.Colors.elements_light());
        setBorderPainted(false);
        setFocusPainted(false);
        setFocusable(false);
        setBackground(Const.Colors.accent_dark());

        String iconpath;
        if (left) iconpath = Const.Strings.iconpaths[1];
        else iconpath = Const.Strings.iconpaths[2];

        try {
            Image img = Const.getImage(iconpath);
            //Image img = ImageIO.read(new FileInputStream(iconpath));
            //noinspection SuspiciousNameCombination
            img = img.getScaledInstance(width, width, java.awt.Image.SCALE_SMOOTH);

            setIcon(new ImageIcon(img));
            Launcher.con.printlnWarning("Loaded " + iconpath);
        } catch (Exception ex) {
            Launcher.con.printlnError(ex.toString());
        }
    }
}
