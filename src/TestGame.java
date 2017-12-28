import java.awt.*;

public class TestGame extends Game {
    public static int x = 0;
    public static int y = 0;
    public int direction = 0; // 0 none, 1 l, 2 r, 3 d, 4 u

    private Rectangle character;

    public TestGame(MainMenu frame, String name) {
        super(frame, name);
        RetroMain.con.println(name + " is up");
        character = new Rectangle(x, y, 25, 25);
    }

    public void key_W(boolean pressed) {
        RetroMain.con.print("W ");
        if (pressed) direction = 4;
        else direction = 0;
    }

    public void key_A(boolean pressed) {
        RetroMain.con.print("A ");
        if (pressed) direction = 1;
        else direction = 0;
    }

    public void key_S(boolean pressed) {
        RetroMain.con.print("S ");
        if (pressed) direction = 3;
        else direction = 0;
    }

    public void key_D(boolean pressed) {
        RetroMain.con.print("D ");
        if (pressed) direction = 2;
        else direction = 0;
    }

    public void key_ESC(boolean pressed) {
        RetroMain.con.print("ESC ");
    }

    public void key_SPACE(boolean pressed) {
        RetroMain.con.print("SPACE ");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Const.Colors.background());
        g.setColor(Const.Colors.accent_light());
        g.fillRect(character.x, character.y, character.width, character.height);

        if (direction == 1) character.x -= 1;
        if (direction == 2) character.x += 1;
        if (direction == 3) character.y += 1;
        if (direction == 4) character.y -= 1;
        repaint();
    }
}
