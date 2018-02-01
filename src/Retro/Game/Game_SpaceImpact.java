package Retro.Game;

import Retro.Const;
import Retro.Entity.Moving;
import Retro.Entity.Moving_Entity;
import Retro.Entity.Moving_Shot;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_SpaceImpact extends Game {
    private final int height = Const.Numbers.height - bar.getHeight(); // 575
    private final int width = Const.Numbers.width;
    private final int charactersize = 25;
    private final int maxEnemies = 10;
    private final int maxShots = 10;
    private final Moving[] enemies = new Moving[maxEnemies];
    private final Moving[] shots = new Moving[maxShots];
    private Timer timer;
    private TimerTask slow;
    private TimerTask fast;
    private int startdelay = 1500;
    private Moving character;

    public Game_SpaceImpact(String name) {
        super(name);
        setup();
    }

    private void slow() {
        int enemyCount = 0;
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

    private void fast() {
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
    public Graphics syncGraphics(Graphics g) {
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

    private void setup() {
        screen.setBackground(Const.Colors.lcd());
        //setGameOverSound(Const.SpaceDodge.soundpaths[2]);
        //setBackgroundMusic(Const.SpaceDodge.soundpaths[0]);
        //startBgMusic();

        character = new Moving_Entity(charactersize, charactersize, Const.SpaceImpact.iconpaths[0]);
        character.setLocation(charactersize / 2, height / 2);
        int xBound = Const.Numbers.width / 5;
        character.setBounds(0, xBound, 0, height - charactersize);

        resume();
    }

    private void newEnemy() {
        for (int i = 0; i < maxEnemies; i++) {
            if (enemies[i] == null) {
                int enemyHeight = 25;
                int enemyWidth = 20;
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
    public void kill() {
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
        int slow_speed = 200;
        timer.schedule(slow, startdelay, slow_speed);
        int fast_speed = 10;
        timer.schedule(fast, 0, fast_speed);

        startdelay = 250;
    }
}
