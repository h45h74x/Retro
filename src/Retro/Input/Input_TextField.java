package Retro.Input;

import Retro.Const;

import javax.swing.*;
import java.awt.*;

public class Input_TextField extends JTextField {
    public Input_TextField() {
        setForeground(Const.Colors.elements());
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
    }
}
