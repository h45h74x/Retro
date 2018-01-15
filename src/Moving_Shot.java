import java.awt.*;

public class Moving_Shot extends Moving {

    public Moving_Shot(int width, int height, Color col) {
        super(width, height, col);
    }

    @Override
    protected void render(Graphics g) {
        Color temp = g.getColor();
        g.setColor(surfaceColor);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(temp);
    }
}
