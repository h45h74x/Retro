import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_FlappyBird extends Game {
    private final int STEP = 10;
    private final int y_STEP = 20;
    private Timer timer;
    private TimerTask slow;
    private TimerTask fast;
    private int blink = 0;
    private int direction = 0; // stop 0, up 1, down 2
    private int height = Const.Numbers.height - bar.getHeight(); // 575
    private int width = Const.Numbers.width;
    private int obst_speed = 30;
    private int char_speed = 10;
    private int charactersize = 25;
    private int spawndistance = STEP * 35;
    private int maxobstacles = 1;

    private Rectangle[] obstacles = new Rectangle[maxobstacles];
    private Rectangle character;

    private int holesize;
    private int holepos;
    private Rectangle obst_bot;
    private Rectangle obst_top;
    private Rectangle hole;


    Game_FlappyBird(String name) {
        super(name);
        setup();
    }

    protected void setup() {
        character = new Rectangle(STEP, height / 2, charactersize, charactersize);

        generateObstacle();
        resume();
    }

    private void generateObstacle() {
        holesize = random(charactersize * 2, charactersize * 4);
        holepos = random((int) (height * 0.2), (int) (height - (height * 0.2)));

        hole = new Rectangle(spawndistance, holepos, charactersize, holesize);
        obst_top = new Rectangle(spawndistance, 0, charactersize, hole.y);
        obst_bot = new Rectangle(spawndistance, hole.y + hole.height, charactersize, height - (hole.y + hole.height));
    }

    @Override
    protected Graphics syncGraphics(Graphics g) {
        g.setColor(Const.Colors.accent_light());
        g.fillRect(character.x, character.y, character.width, character.height);
        switch (blink) {
            case 1:
                g.setColor(Color.green);
                break;
            case 2:
                g.setColor(Color.red);
                break;
            default:
                g.setColor(Const.Colors.elements_light());
        }
        g.fillRect(obst_bot.x, obst_bot.y, obst_bot.width, obst_bot.height);
        g.fillRect(obst_top.x, obst_top.y, obst_top.width, obst_top.height);
        repaint();
        return g;
    }

    void fast() {
        int move;

        switch (direction) {
            case 1:
                move = character.y - y_STEP;
                if (move <= 0) break;
                character.y = move;
                break;
            case 2:
                move = character.y + y_STEP;
                if (move >= height) break;
                character.y = move;
                break;
        }
        direction = 0;
    }

    void slow() {
        blink = 0;

        obst_bot.x -= STEP;
        obst_top.x -= STEP;
        hole.x -= STEP;

        if (hole.x < 0) generateObstacle();

        if (character.x == hole.x) {
            if (character.y >= hole.y && (character.y + character.height) <= (hole.y + hole.height)) {
                blink = 1;
                bar.increaseScore();
            } else {
                bar.loseHeart();
                blink = 2;
            }
        }
    }

    int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    @Override
    protected void key_W(boolean pressed) {
        if (pressed) direction = 1;
    }

    @Override
    protected void key_A(boolean pressed) {
        if (pressed) generateObstacle();
    }

    @Override
    protected void key_S(boolean pressed) {
        if (pressed) direction = 2;
    }

    @Override
    protected void key_D(boolean pressed) {

    }

    @Override
    protected void key_SPACE(boolean pressed) {
    }

    @Override
    protected void key_ESC(boolean pressed) {
        if (pressed) pause();
    }

    @Override
    protected void kill() {
        timer.cancel();
        fast.cancel();
        slow.cancel();
    }

    protected void halt() {
        timer.cancel();
        fast.cancel();
        slow.cancel();
    }

    @Override
    protected void resume() {
        timer = new Timer();

        slow = new TimerTask() {
            @Override
            public void run() {
                slow();
            }
        };
        fast = new TimerTask() {
            @Override
            public void run() {
                fast();
            }
        };
        timer.schedule(slow, obst_speed * 2, obst_speed);
        timer.schedule(fast, 0, char_speed);
    }
}
