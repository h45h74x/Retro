import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class Input_RButton extends JButton {

    public Input_RButton(String text) {
        setBorder(new RoundedBorder());
        setText(text);
        setOpaque(false);
        setForeground(Const.Colors.elements_light());
        setContentAreaFilled(false);
        setFocusPainted(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setForeground(Color.white);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setForeground(Const.Colors.elements_light());
            }
        });
    }

    private static class RoundedBorder implements Border {
        private final int radius;

        RoundedBorder() {
            this.radius = 10;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            //g.setColor(Const.Colors.button());
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
