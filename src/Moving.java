import javax.imageio.ImageIO;
import java.awt.*;

public abstract class Moving {
    Image icon;
    String iconPath;
    Color surfaceColor = Const.Colors.elements_light();

    double x = 0;
    double y = 0;

    int height = 25;
    int width = 25;

    int maxX = Const.Numbers.width;
    int minX = 0;
    int maxY = Const.Numbers.height;
    int minY = 0;

    double velX = 0;
    double velY = 0;

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

    protected abstract void render(Graphics g);

    protected void setIcon(String path) {
        this.iconPath = path;
    }

    private void loadIcon() {
        try {
            icon = ImageIO.read(getClass().getResourceAsStream(iconPath));
        } catch (Exception ex) {
            Launcher.con.printlnError(ex.toString());
        }
        icon = icon.getScaledInstance(width, height - 5, Image.SCALE_SMOOTH);
    }

    void tick() {
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

    int getX() {
        return (int) x;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getY() {
        return (int) y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    void setBounds(int minX, int maxX, int minY, int maxY) {
        setBoundsX(minX, maxX);
        setBoundsY(minY, maxY);
    }

    void setBoundsX(int minX, int maxX) {
        this.minX = minX;
        this.maxX = maxX;
    }

    void setBoundsY(int minY, int maxY) {
        this.minY = minY;
        this.maxY = maxY;
    }

    void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void setVelX(double velX) {
        this.velX = velX;
    }

    void setVelY(double velY) {
        this.velY = velY;
    }


}
