package Retro.UI;

import Retro.Const;
import Retro.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TopBar extends JPanel {
    public TopBar() {
        super();
        setup();
    }

    private void setup() {
        setOpaque(false);
        setLayout(new BorderLayout());
        InvButton close = new InvButton(Const.UI.actions[0], 20, e -> Launcher.kill());
        add(close, BorderLayout.LINE_END);
    }
}
