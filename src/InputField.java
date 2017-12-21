import javax.swing.*;
import java.awt.*;

public class InputField extends JTextField {
    public InputField() {
        setForeground(Const.Colors.elements());
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
    }
}
