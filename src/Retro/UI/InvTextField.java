package Retro.UI;

import Retro.Const;

import javax.swing.*;
import java.awt.*;

public class InvTextField extends JTextField {
    public InvTextField() {
        setForeground(Const.Colors.elements());
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
    }
}
