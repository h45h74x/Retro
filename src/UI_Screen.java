import javax.swing.*;
import java.awt.*;

public class UI_Screen extends JPanel {
    private Game parent;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!active) return;
        g = parent.syncGraphics(g);
        repaint();
    }
}
