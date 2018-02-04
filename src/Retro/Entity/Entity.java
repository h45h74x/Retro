package Retro.Entity;

import java.awt.*;

public class Entity extends Moving {
    private boolean hit = false;

    public Entity(int width, int height, String iconPath) {
        super(width, height, iconPath);
    }

    Entity(int a, String iconPath) {
        super(a, a, iconPath);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void render(Graphics g) {
        g.drawImage(icon, (int) x, (int) y, null);
    }

}
