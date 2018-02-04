package Retro.Entity;

import Retro.Const;
import Retro.Launcher;
import Retro.Managers.LookAndFeel;

import java.awt.*;

public abstract class Moving {
    Image icon;
    Color surfaceColor = Const.Colors.elements_light();
    double x = 0;
    double y = 0;
    int height = 25;
    int width = 25;
    private String iconPath;
    private int maxX = Const.Numbers.width;
    private int minX = 0;
    private int maxY = Const.Numbers.height;
    private int minY = 0;

    private double velX = 0;
    private double velY = 0;

    Moving(int width, int height) {
        this.width = width;
        this.height = height;
    }

    Moving(int width, int height, String iconPath) {
        this.width = width;
        this.height = height;
        this.iconPath = iconPath;
        loadIcon();
    }

    Moving(int width, int height, Color col) {
        this.width = width;
        this.height = height;
        this.surfaceColor = col;
    }

    public abstract void render(Graphics g);

    protected void setIcon(String path) {
        this.iconPath = path;
    }

    public void updateIcon(String path) {
        this.iconPath = path;
        loadIcon();
    }

    private void loadIcon() {
        try {
            icon = LookAndFeel.getImage(iconPath);
        } catch (Exception ex) {
            Launcher.con.printlnError(ex.toString());
        }
        icon = icon.getScaledInstance(width, height - 5, Image.SCALE_SMOOTH);
    }

    public void tick() {
        x += velX;
        if (x < minX) x = minX;
        if (x > maxX) x = maxX;

        y += velY;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
    }

    protected void setColor(Color color) {
        this.surfaceColor = color;
    }

    public int getX() {
        return (int) x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBounds(int minX, int maxX, int minY, int maxY) {
        setBoundsX(minX, maxX);
        setBoundsY(minY, maxY);
    }

    public void setBoundsX(int minX, int maxX) {
        this.minX = minX;
        this.maxX = maxX;
    }

    private void setBoundsY(int minY, int maxY) {
        this.minY = minY;
        this.maxY = maxY;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }


}
