import java.awt.*;

public class Moving_Entity extends Moving {
    private boolean hit = false;

    Moving_Entity(int width, int height, String iconPath) {
        super(width, height, iconPath);
    }

    Moving_Entity(int a, String iconPath) {
        super(a, a, iconPath);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    protected void render(Graphics g) {
        g.drawImage(icon, (int) x, (int) y, null);
    }

}
