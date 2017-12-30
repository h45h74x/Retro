import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_TestGame extends Game {
    private int x = 0;
    private int y = 0;

    private UI_Screen screen;
    private UI_StatusBar bar;

    private int STEP = 30;
    private int direction = 0; // stop 0, l 1, r 2, u 3, d 4

    private int speed = 1000;
    private int refresh = 100;

    private Timer timer = new Timer();
    private TimerTask task;
    private TimerTask task2;

    private Rectangle character;
    private Rectangle obstacle;

    public Game_TestGame(UI_Frame frame, String name) {
        super(frame, name);
        screen = new UI_Screen(this);

        setup();
        slow();
    }

    private void setup() {
        setLayout(new BorderLayout());

        add(screen, BorderLayout.CENTER);

        bar = new UI_StatusBar(this, 3);
        add(bar, BorderLayout.PAGE_START);

        character = new Rectangle(x, y, 25, 25);
        obstacle = new Rectangle(x + STEP, y + STEP, 25, 25);

        task = new TimerTask() {
            @Override
            public void run() {
                slow();
            }
        };
        task2 = new TimerTask() {
            @Override
            public void run() {
                fast();
            }
        };
        timer.schedule(task, speed * 2, speed);
        timer.schedule(task2, 0, refresh);
    }

    private void fast() {
        switch (direction) {
            case 1:
                character.x -= STEP;
                break;
            case 2:
                character.x += STEP;
                break;
            case 3:
                character.y -= STEP;
                break;
            case 4:
                character.y += STEP;
                break;
            default:
                break;
        }
        direction = 0;
        checkCollision();
    }

    private void checkCollision() {
        Launcher.con.printlnInfo("Tock");
        if (character.x == obstacle.x) {
            screen.setForeground(Color.red);
            Launcher.con.printlnWarning("Collision");
            bar.loseHeart();
            character.x = character.x - obstacle.width;
        } else {
            screen.setForeground(Const.Colors.accent_light());
        }
    }

    private void slow() {
        Launcher.con.printlnInfo("Tick");

        obstacle.x += STEP;
    }

    @Override
    protected Graphics syncGraphics(Graphics g) {
        g.fillRect(character.x, character.y, character.width, character.height);
        g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        repaint();
        return g;
    }

    public void key_W(boolean pressed) {
        Launcher.con.printInfo("W ");
        if (pressed) direction = 3;
    }

    public void key_A(boolean pressed) {
        Launcher.con.printInfo("A ");
        if (pressed) direction = 1;
    }

    public void key_S(boolean pressed) {
        Launcher.con.printInfo("S ");
        if (pressed) direction = 4;
    }

    public void key_D(boolean pressed) {
        Launcher.con.printInfo("D ");
        if (pressed) direction = 2;
    }

    public void key_ESC(boolean pressed) {
        Launcher.con.printInfo("ESC ");
        if (pressed) Launcher.home();
    }

    public void key_SPACE(boolean pressed) {
        Launcher.con.printInfo("SPACE ");
    }

    public void kill() {
        timer.cancel();
        task.cancel();
        task2.cancel();
    }

    public void pause() {
    }

    public void resume() {
    }
}
