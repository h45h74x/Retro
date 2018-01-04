import java.awt.*;

public class Moving_Entity extends Moving {

    Moving_Entity(int width, int height, String iconPath) {
        super(width, height, iconPath);
    }

    protected void render(Graphics g) {
        g.drawImage(icon, (int) x, (int) y, null);
    }

}
