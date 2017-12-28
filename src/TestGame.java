import javax.swing.*;
import java.awt.*;

public class TestGame extends Game {
    public static int x = 0;
    public static int y = 0;

    private final int STEP = 30;
    private final int DELAY = 140;

    private boolean active = false;

    private Timer timer;

    public int direction = 0; // 0 none, 1 l, 2 r, 3 d, 4 u

    private Rectangle character;

    public TestGame(MainMenu frame, String name) {
        super(frame, name);
        active = true;
        character = new Rectangle(x, y, 25, 25);
    }

    public void paintComponent(Graphics g) {
        if (!active) return;
        super.paintComponent(g);
        this.setBackground(Const.Colors.background());
        g.setColor(Const.Colors.accent_light());
        g.fillRect(character.x, character.y, character.width, character.height);
        repaint();
    }

    public void key_W(boolean pressed) {
        RetroMain.con.printInfo("W ");
        if (pressed) character.y -= STEP;
    }

    public void key_A(boolean pressed) {
        RetroMain.con.printInfo("A ");
        if (pressed) character.x -= STEP;
    }

    public void key_S(boolean pressed) {
        RetroMain.con.printInfo("S ");
        if (pressed) character.y += STEP;
    }

    public void key_D(boolean pressed) {
        RetroMain.con.printInfo("D ");
        character.x += STEP;
    }

    public void key_ESC(boolean pressed) {
        RetroMain.con.printInfo("ESC ");
        if (pressed) RetroMain.home();
    }

    public void key_SPACE(boolean pressed) {
        RetroMain.con.printInfo("SPACE ");
    }

    public void kill() {
        active = false;
    }

    public void pause() {
    }

    public void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (getWidth() - metr.stringWidth(msg)) / 2, getHeight() / 2);
    }
}
