package Retro.UI;

import Retro.Const;
import Retro.Game.Game;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {
    private final Game parent;

    public Screen(Game parent) {
        super();
        this.parent = parent;
        setBackground(Const.Colors.background());
        setForeground(Const.Colors.accent_light());
    }

    @SuppressWarnings("UnusedAssignment")
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = parent.syncGraphics(g);
        repaint();
    }
}
