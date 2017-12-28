import javax.swing.*;

public abstract class Game extends JPanel {

    protected MainMenu frame;
    protected String name;

    protected Game(MainMenu frame, String name) {
        this.frame = frame;
        this.name = name;
        RetroMain.con.spacer("-");
        RetroMain.con.println("created " + name);
    }

    protected Game(MainMenu frame) {
        this.frame = frame;
        this.name = Const.Strings.menutitle;
        RetroMain.con.spacer("-");
        RetroMain.con.println("created " + name);
    }

    public String getName() {
        return this.name;
    }

    protected abstract void key_W(boolean pressed);

    protected abstract void key_A(boolean pressed);

    protected abstract void key_S(boolean pressed);

    protected abstract void key_D(boolean pressed);

    protected abstract void key_SPACE(boolean pressed);

    protected abstract void key_ESC(boolean pressed);
}
