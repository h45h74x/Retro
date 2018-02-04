package Retro.Entity;

import java.awt.*;

public class Surface extends Moving {

    public Surface(int width, int height) {
        super(width, height);
    }

    public void render(Graphics g) {
        Color temp = g.getColor();
        g.setColor(surfaceColor);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(temp);
    }
}
