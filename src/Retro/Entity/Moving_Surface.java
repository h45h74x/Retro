package Retro.Entity;

import java.awt.*;

public class Moving_Surface extends Moving {

    public Moving_Surface(int width, int height) {
        super(width, height);
    }

    public void render(Graphics g) {
        Color temp = g.getColor();
        g.setColor(surfaceColor);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(temp);
    }
}
