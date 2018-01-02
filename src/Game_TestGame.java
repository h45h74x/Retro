import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_TestGame extends Game {
    private final int STEP = 30;
    private int direction = 0; // stop 0, l 1, r 2, u 3, d 4

    private Timer timer;
    private TimerTask task;
    private TimerTask task2;

    private Rectangle character;
    private Rectangle obstacle;

    Game_TestGame(String name) {
        super(name);
        setup();
    }

    protected void setup() {
        int y = 0;
        int x = 0;
        character = new Rectangle(x, y, 25, 25);
        obstacle = new Rectangle(x + STEP, y + STEP, 25, 25);


        resume();

        slow();
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
        Launcher.con.printlnInfo(character.x + " " + obstacle.x);
        if (character.x == obstacle.x && character.y == obstacle.y) {
            screen.setForeground(Color.red);
            Launcher.con.printlnWarning("Collision");
            bar.loseHeart();
            character.x = character.x - STEP;
        } else {
            screen.setForeground(Const.Colors.accent_light());
        }
    }

    private void slow() {
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
        if (pressed) direction = 3;
    }

    public void key_A(boolean pressed) {
        if (pressed) direction = 1;
    }

    public void key_S(boolean pressed) {
        if (pressed) direction = 4;
    }

    public void key_D(boolean pressed) {
        if (pressed) direction = 2;
    }

    public void key_ESC(boolean pressed) {
        if (pressed) pause();
    }

    public void key_SPACE(boolean pressed) {
        Launcher.con.printInfo("SPACE ");
    }

    public void kill() {
        timer.cancel();
        task.cancel();
        task2.cancel();
    }

    protected void halt() {
        timer.cancel();
        task.cancel();
        task2.cancel();
    }

    public void resume() {
        timer = new Timer();
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
        int speed = 1000;
        timer.schedule(task, speed * 2, speed);
        int refresh = 100;
        timer.schedule(task2, 0, refresh);
    }
}
