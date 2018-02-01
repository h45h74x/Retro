package Retro.Game;

import Retro.Const;
import Retro.Entity.Moving;
import Retro.Entity.Moving_Entity;
import Retro.Entity.Moving_Surface;
import Retro.Launcher;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_SpaceDodge extends Game {
    private final int height = Const.Numbers.height - bar.getHeight(); // 575
    private final int width = Const.Numbers.width;
    private final int charactersize = 25;
    private final int maxHoleSize = (int) (charactersize * 3);
    private final int minHoleSize = (int) (charactersize * 2);
    private final int maxObstacles = 3;
    private final int xStep = -7;
    private final int yStep = 3;
    private final int distance = (int) ((width / maxObstacles));
    private final Moving[] obst_tops = new Moving[maxObstacles];
    private final Moving[] obst_bots = new Moving[maxObstacles];
    private final Moving[] holes = new Moving[maxObstacles];
    private Timer timer;
    private TimerTask slow;
    private TimerTask fast;
    private Image bg;
    private Paint gradient;
    private int startdelay = 1500;
    private Moving character;

    public Game_SpaceDodge(String name) {
        super(name);
        setup();
    }

    private void setup() {
        try {
            bg = Const.getImage(Const.SpaceImpact.bg);
            bg = bg.getScaledInstance(Const.Numbers.width, Const.Numbers.height, Image.SCALE_SMOOTH);
        } catch (Exception ex) {
            Launcher.con.printlnError(ex.toString());
        }

        /*gradient = new GradientPaint(
                0, 0, Const.Colors.accent_light(),
                0, height, Const.Colors.background(),
                true
        );*/

        setGameOverSound(Const.SpaceImpact.soundpaths[2]);
        setBackgroundMusic(Const.SpaceImpact.soundpaths[0]);
        startBgMusic();

        character = new Moving_Entity(charactersize, charactersize, Const.SpaceDodge.iconpaths[0]);
        character.setLocation(charactersize / 2, height / 2);
        character.setBounds(0, width, 0, height - charactersize);

        generateObstacles();
        resume();
    }

    private void generateObstacles() {
        int holesize = 0;
        int holepos = 0;
        for (int i = 0; i < maxObstacles; i++) {
            holesize = random(minHoleSize, maxHoleSize);
            holepos = random((int) (height * 0.1), (int) (height * 0.9));

            holes[i] = new Moving_Surface(charactersize, holesize);
            holes[i].setBounds(0, width, 0, height - charactersize);
            holes[i].setLocation(distance * (i + 1), holepos);
            holes[i].setVelX(xStep);

            obst_tops[i] = new Moving_Surface(charactersize, holepos);
            obst_tops[i].setBounds(0, width, 0, height - charactersize);
            obst_tops[i].setLocation(distance * (i + 1), 0);
            obst_tops[i].setVelX(xStep);

            obst_bots[i] = new Moving_Surface(charactersize, height - (holepos + holesize));
            obst_bots[i].setBounds(0, width, 0, height - charactersize);
            obst_bots[i].setLocation(distance * (i + 1), holepos + holesize);
            obst_bots[i].setVelX(xStep);
        }
    }

    private void newObstacle() {
        int holesize = random(minHoleSize, maxHoleSize);
        int holepos = random((int) (height * 0.1), (int) (height * 0.9));

        Moving_Surface hole = new Moving_Surface(charactersize, holesize);
        hole.setLocation(distance * (maxObstacles), holepos);
        hole.setVelX(xStep);

        Moving_Surface obst_top = new Moving_Surface(charactersize, holepos);
        obst_top.setLocation(distance * (maxObstacles), 0);
        obst_top.setVelX(xStep);

        Moving_Surface obst_bot = new Moving_Surface(charactersize, height - (holepos + holesize));
        obst_bot.setLocation(distance * (maxObstacles), holepos + holesize);
        obst_bot.setVelX(xStep);

        for (int i = 0; i < maxObstacles; i++) {
            if ((i + 1) == maxObstacles) {
                holes[i] = hole;
                obst_tops[i] = obst_top;
                obst_bots[i] = obst_bot;
                break;
            }

            holes[i] = holes[i + 1];
            obst_tops[i] = obst_tops[i + 1];
            obst_bots[i] = obst_bots[i + 1];
        }
    }

    @Override
    public Graphics syncGraphics(Graphics g) {
        if (gradient != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(gradient);
            g2.fillRect(0, 0, width, height);
        }

        if (bg != null) g.drawImage(bg, 0, 0, null);

        character.render(g);

        for (int i = 0; i < maxObstacles; i++) {
            obst_tops[i].render(g);
            obst_bots[i].render(g);
        }

        repaint();
        return g;
    }

    private void fast() {
        character.tick();
    }

    private void slow() {
        for (int i = 0; i < maxObstacles; i++) {
            obst_tops[i].tick();
            obst_bots[i].tick();
            holes[i].tick();
        }

        if (holes[0].getX() <= 0) newObstacle();

        boolean xCond = (character.getX() >= holes[0].getX()) && (character.getX() <= (holes[0].getX() + holes[0].getWidth()));
        boolean yCond = (character.getY() >= holes[0].getY()) && ((character.getY() + character.getHeight()) <= (holes[0].getY() + holes[0].getHeight()));

        if (xCond) {
            if (yCond) {
                bar.increaseScore();
                Const.playSound(Const.SpaceDodge.soundpaths[1]);
            } else bar.loseHeart();
            newObstacle();
        }

    }

    private int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    @Override
    protected void key_W(boolean pressed) {
        if (pressed) character.setVelY(-yStep);
        if (!pressed) character.setVelY(0);
    }

    @Override
    protected void key_A(boolean pressed) {
        if (pressed) generateObstacles();
    }

    @Override
    protected void key_S(boolean pressed) {
        if (pressed) character.setVelY(yStep);
        if (!pressed) character.setVelY(0);
    }

    @Override
    protected void key_D(boolean pressed) {
        if (pressed) newObstacle();
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
        int obst_speed = 35;
        timer.schedule(slow, startdelay, obst_speed);
        int char_speed = 10;
        timer.schedule(fast, 0, char_speed);

        startdelay = 250;
    }
}
