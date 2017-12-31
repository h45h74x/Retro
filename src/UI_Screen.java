import javax.swing.*;
import java.awt.*;

class UI_Screen extends JPanel {
    private final Game parent;
    private boolean active = true;

    UI_Screen(Game parent) {
        super();
        this.parent = parent;
        setBackground(Const.Colors.background());
        setForeground(Const.Colors.accent_light());
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @SuppressWarnings("UnusedAssignment")
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!active) return;
        g = parent.syncGraphics(g);
        repaint();
    }
}
