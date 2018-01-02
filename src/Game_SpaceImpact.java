import java.awt.*;

public class Game_SpaceImpact extends Game {

    Game_SpaceImpact(String name) {
        super(name);
        setup();
    }

    @Override
    protected Graphics syncGraphics(Graphics g) {
        return null;
    }

    protected void setup() {

    }


    @Override
    protected void key_W(boolean pressed) {
        boolean up;
        if (pressed) {
            Launcher.con.printlnInfo("Up @ " + name);
            up = true;
        } else up = false;
    }

    @Override
    protected void key_A(boolean pressed) {

    }

    @Override
    protected void key_S(boolean pressed) {
        boolean down;
        if (pressed) {
            Launcher.con.printlnInfo("Down @ " + name);
            down = true;
        } else down = false;
    }

    @Override
    protected void key_D(boolean pressed) {

    }

    @Override
    protected void key_SPACE(boolean pressed) {
        boolean shoot;
        if (pressed) {
            Launcher.con.printlnInfo("Shoot @ " + name);
            shoot = true;
        } else shoot = false;
    }

    @Override
    protected void key_ESC(boolean pressed) {
        if (pressed) pause();
    }

    @Override
    protected void kill() {
        Launcher.con.printlnInfo("Killed @ " + name);
    }


    @Override
    protected void halt() {
        Launcher.con.printlnInfo("Pause @ " + name);
        Launcher.extStartMenu(Const.Menues.MAIN_MENU);
    }

    protected void resume() {
        Launcher.con.printlnInfo("Resume @ " + name);
    }
}
