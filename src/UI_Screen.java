import javax.swing.*;
import java.awt.*;

class UI_Screen extends JPanel {
    private final Game parent;

    UI_Screen(Game parent) {
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
