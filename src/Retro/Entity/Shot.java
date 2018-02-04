package Retro.Entity;

import java.awt.*;

public class Shot extends Moving {

    public Shot(int width, int height, Color col) {
        super(width, height, col);
    }

    @Override
    public void render(Graphics g) {
        Color temp = g.getColor();
        g.setColor(surfaceColor);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(temp);
    }
}
