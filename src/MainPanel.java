import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Color c1;
    private Color c2;

    public MainPanel(Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
        setBackground(Const.Colors.background());
        setOpaque(true);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;

        Paint gradient = new GradientPaint(
                0, 0, c1,
                0, height, c2,
                true
        );

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);


    }
}
