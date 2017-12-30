import java.awt.*;

public class Game_SpaceImpact extends Game {

    private UI_Screen screen;

    private boolean up;
    private boolean down;
    private boolean shoot;

    protected Game_SpaceImpact(UI_Frame frame, String name) {
        super(frame, name);
        setup();
    }

    @Override
    protected Graphics syncGraphics(Graphics g) {
        return null;
    }

    private void setup() {
        setLayout(new BorderLayout());

        screen = new UI_Screen(this);
        screen.setBackground(Const.SpaceImpact.background());
        add(screen, BorderLayout.CENTER);

        UI_StatusBar bar = new UI_StatusBar(this, 3);
        add(bar, BorderLayout.PAGE_START);
    }


    @Override
    protected void key_W(boolean pressed) {
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
    protected void pause() {
        Launcher.con.printlnInfo("Pause @ " + name);
    }

    protected void resume() {
        Launcher.con.printlnInfo("Resume @ " + name);
    }
}
