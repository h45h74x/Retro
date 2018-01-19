import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_SpaceImpact extends Game {
    private Timer timer;
    private TimerTask slow;
    private TimerTask fast;

    private int height = Const.Numbers.height - bar.getHeight(); // 575
    private int width = Const.Numbers.width;
    private int xBound = Const.Numbers.width / 5;

    private int charactersize = 25;
    private int enemyWidth = 20;
    private int enemyHeight = 25;

    private int maxEnemies = 4;
    private int enemyCount;
    private int maxShots = 10;

    private int slow_speed = 125;
    private int fast_speed = 10;
    private int startdelay = 1500;

    private Moving[] enemies = new Moving[maxEnemies];
    private Moving[] shots = new Moving[maxShots];
    private Moving character;

    Game_SpaceImpact(String name) {
        super(name);
        setup();
    }

    void slow() {
        enemyCount = 0;
        for (int i = 0; i < maxEnemies; i++) {
            if (enemies[i] != null) {
                enemies[i].tick();
                enemyCount++;
                if (enemies[i].getX() <= 0) {
                    enemies[i] = null;
                    enemyCount--;
                    bar.loseHeart();
                }
            }
        }
        if (enemyCount <= maxEnemies) newEnemy();
    }

    void fast() {
        character.tick();
        for (int i = 0; i < maxShots; i++) {
            if (shots[i] != null) {
                shots[i].tick();
                if (shots[i].getX() >= width) shots[i] = null;
            }
        }
        for (int i = 0; i < maxEnemies; i++) {
            for (int j = 0; j < maxShots && enemies[i] != null && shots[j] != null; j++) {
                boolean xCond = (shots[j].getX() >= enemies[i].getX()) && (shots[j].getX() + shots[j].getWidth() <= enemies[i].getX() + enemies[i].getWidth());
                boolean yCond = (shots[j].getY() >= enemies[i].getY()) && (shots[j].getY() + shots[j].getHeight() <= enemies[i].getY() + enemies[i].getHeight());
                if (xCond && yCond) {
                    enemies[i] = null;
                    shots[j] = null;
                    bar.increaseScore();
                }
            }
        }
    }

    @Override
    protected Graphics syncGraphics(Graphics g) {
        character.render(g);
        for (Moving shot : shots) {
            if (shot != null) shot.render(g);
        }

        for (Moving enemy : enemies) {
            if (enemy != null) enemy.render(g);
        }

        repaint();
        return g;
    }

    private void shoot() {
        for (int i = 0; i < maxShots; i++) {
            if (shots[i] == null) {
                shots[i] = new Moving_Shot(6, 3, Color.black);
                shots[i].setVelX(4);
                shots[i].setBoundsX(0, width);
                shots[i].setY(character.getY() + (character.getHeight() / 2) - shots[0].getHeight());
                shots[i].setX(character.getX() + character.getHeight());
                break;
            }
        }
    }

    protected void setup() {
        screen.setBackground(Const.Colors.lcd());
        //setGameOverSound(Const.SpaceDodge.soundpaths[2]);
        //setBackgroundMusic(Const.SpaceDodge.soundpaths[0]);
        //startBgMusic();

        character = new Moving_Entity(charactersize, charactersize, Const.SpaceImpact.iconpaths[0]);
        character.setLocation(charactersize / 2, height / 2);
        character.setBounds(0, xBound, 0, height - charactersize);

        resume();
    }

    private void newEnemy() {
        for (int i = 0; i < maxEnemies; i++) {
            if (enemies[i] == null) {
                enemies[i] = new Moving_Entity(enemyWidth, enemyHeight, Const.SpaceImpact.iconpaths[1]);
                enemies[i].setVelX(-5);
                enemies[i].setX(width - charactersize - random(0, width / 10));
                enemies[i].setY(random(charactersize - 1, height - charactersize));
                enemies[i].setBounds(0, width, 0, height - charactersize);
                break;
            }
        }
    }

    private int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    @Override
    protected void key_W(boolean pressed) {
        if (pressed) character.setVelY(-5);
        if (!pressed) character.setVelY(0);
    }

    @Override
    protected void key_A(boolean pressed) {
        if (pressed) character.setVelX(-5);
        if (!pressed) character.setVelX(0);
    }

    @Override
    protected void key_S(boolean pressed) {
        if (pressed) character.setVelY(5);
        if (!pressed) character.setVelY(0);
    }

    @Override
    protected void key_D(boolean pressed) {
        if (pressed) character.setVelX(5);
        if (!pressed) character.setVelX(0);
    }

    @Override
    protected void key_SPACE(boolean pressed) {
        if (pressed) shoot();
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
        timer.schedule(slow, startdelay, slow_speed);
        timer.schedule(fast, 0, fast_speed);

        startdelay = 250;
    }
}
