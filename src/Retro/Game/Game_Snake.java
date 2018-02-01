package Retro.Game;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Retro.Const;
import Retro.Entity.Moving_Entity;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_Snake extends Game {

    private final int maxBody = 100; //(Const.Numbers.panel_height * Const.Numbers.game_width) / 400;
    private final long createdMillis = System.currentTimeMillis();
    private final int[][] traces = new int[maxBody][2];
    private final Moving_Entity[] body = new Moving_Entity[maxBody];
    private final int entity_size = 15;
    private final int padding = 5;
    private final int step = entity_size + padding;
    private Timer timer;
    private int dir = 1; // 0 nix, 1 links; 2 rechts; 3 oben; 4 unten
    private int lengthofsnake = 3;
    private boolean lock = false;
    private boolean draw = false;
    private Moving_Entity apple;

    public Game_Snake(String name) {
        super(name, 0);
        generateFirst();
        bar.setScore(lengthofsnake);
        bar.setHeartCount(0);
        screen.setBackground(Const.Colors.gray_light());
        resume();
    }

    private void generate() {
        body[0].updateIcon(Const.Snake.iconpaths[dir]);
        for (int i = 1; i < lengthofsnake; i++) {
            if (body[i] == null)
                body[i] = new Moving_Entity(entity_size, entity_size + padding, Const.Snake.iconpaths[0]);
        }
    }

    private void generateFirst() {
        body[0] = new Moving_Entity(entity_size, entity_size + padding, Const.Snake.iconpaths[dir]);
        body[0].setX(Const.Numbers.width - body[0].getWidth() - padding);
        body[0].setY(body[0].getY() + padding);
        generate();
    }

    private void updateTraces() {
        int tempX = body[0].getX();
        int tempY = body[0].getY();

        if (!(tempX == traces[0][0] && tempY == traces[0][1])) {
            for (int i = traces.length - 1; i > 0; i--) {
                traces[i][0] = traces[i - 1][0];
                traces[i][1] = traces[i - 1][1];
            }

            traces[0][0] = tempX;
            traces[0][1] = tempY;
        }
    }

    private void move() {
        if (draw) {
            switch (dir) {
                case 1:
                    body[0].setVelX(-step);
                    body[0].setVelY(0);
                    break;
                case 2:
                    body[0].setVelX(step);
                    body[0].setVelY(0);
                    break;
                case 3:
                    body[0].setVelX(0);
                    body[0].setVelY(-step);
                    break;
                case 4:
                    body[0].setVelX(0);
                    body[0].setVelY(step);
                    break;
                default:
                    body[0].setVelX(0);
                    body[0].setVelY(0);
            }
        }
        body[0].updateIcon(Const.Snake.iconpaths[dir]);
        body[0].tick();
        generate();
        updateTraces();
        follow();
        checkBorder();
        lock = false;
    }

    private void checkBorder() {
        if (body[0].getX() <= 0) game_over();
        else if (body[0].getY() <= 0) game_over();
        else if (body[0].getX() + entity_size + padding > Const.Numbers.width)
            game_over();
        else if (body[0].getY() + entity_size > Const.Numbers.height - bar.getHeight())
            game_over();
        for (int i = 1; i < body.length; i++) {
            if (body[i] != null) {
                if (body[0].getX() == body[i].getX() & body[0].getY() == body[i].getY())
                    game_over();
            }
        }
        if (apple != null) {
            if (body[0].getX() == apple.getX() && body[0].getY() == apple.getY()) {
                apple = null;
                if (++lengthofsnake >= maxBody) {
                    lengthofsnake--;
                }
                bar.setScore(lengthofsnake);
            }
        }
    }

    private void generateApple() {
        if (apple != null) return;

        apple = new Moving_Entity(entity_size, entity_size + padding, Const.Snake.iconpaths[5]);
        apple.setX((int) (Math.random() * 35) * 20);
        apple.setY((int) (Math.random() * 25) * 20 + 5);
    }

    private void follow() {
        for (int i = 1; i < lengthofsnake; i++) {
            body[i].setX(traces[i][0]);
            body[i].setY(traces[i][1]);
        }
    }

    @Override
    public Graphics syncGraphics(Graphics g) {
        body[0].render(g);
        for (int i = 1; i < body.length; i++) {
            if (body[i] != null && draw) body[i].render(g);
        }
        if (apple != null) apple.render(g);
        return g;
    }

    @Override
    protected void key_W(boolean pressed) {
        if (!pressed) return;
        if (dir != 4 && !lock) {
            dir = 3;
            lock = true;
        }
        draw = true;
    }

    @Override
    protected void key_A(boolean pressed) {
        if (!pressed) return;
        if (dir != 2 && !lock) {
            dir = 1;
            lock = true;
        }
        draw = true;
    }

    @Override
    protected void key_S(boolean pressed) {
        if (!pressed) return;
        if (dir != 3 && !lock) {
            dir = 4;
            lock = true;
        }
        draw = true;
    }

    @Override
    protected void key_D(boolean pressed) {
        if (!pressed) return;
        if (dir != 1 && !lock) {
            dir = 2;
            lock = true;
        }
        draw = true;
    }

    @Override
    protected void key_SPACE(boolean pressed) {

    }

    @Override
    protected void key_ESC(boolean pressed) {
        if (pressed) pause();
    }

    @Override
    public void kill() {

    }

    @Override
    protected void halt() {
        timer.cancel();
    }

    @Override
    protected void resume() {
        timer = new Timer();
        TimerTask fast = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };
        TimerTask appletime = new TimerTask() {
            @Override
            public void run() {
                generateApple();
            }
        };
        timer.schedule(fast, 0, 100);
        timer.schedule(appletime, 500, 100);
    }
}
