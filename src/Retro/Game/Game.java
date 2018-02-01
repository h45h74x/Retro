package Retro.Game;

import Retro.Const;
import Retro.Launcher;
import Retro.Menu.Menu_GameOver;
import Retro.Menu.Menu_Pause;
import Retro.UI.Screen;
import Retro.UI.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("Duplicates")
public abstract class Game extends JPanel /*implements Runnable*/ {

    final Screen screen;
    final StatusBar bar;
    private final String name;
    private final Menu_Pause pauseMenu;
    private final Menu_GameOver overMenu;
    private final KeyAdapter gameKeys = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    key_W(true);
                    break;
                case KeyEvent.VK_A:
                    key_A(true);
                    break;
                case KeyEvent.VK_S:
                    key_S(true);
                    break;
                case KeyEvent.VK_D:
                    key_D(true);
                    break;
                case KeyEvent.VK_SPACE:
                    key_SPACE(true);
                    break;
                case KeyEvent.VK_ESCAPE:
                    key_ESC(true);
                    break;
                default:
                    Launcher.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    key_W(false);
                    break;
                case KeyEvent.VK_A:
                    key_A(false);
                    break;
                case KeyEvent.VK_S:
                    key_S(false);
                    break;
                case KeyEvent.VK_D:
                    key_D(false);
                    break;
                case KeyEvent.VK_SPACE:
                    key_SPACE(false);
                    break;
                case KeyEvent.VK_ESCAPE:
                    key_ESC(false);
                    break;
                default:
                    Launcher.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
                    break;
            }
        }
    };
    private final Retro.UI.Frame frame;
    private String gameOverSound = "none";
    private String backgroundMusic = "none";
    private boolean isPaused = false;

    Game(String name, int hearts) {
        super();
        this.name = name;
        this.frame = Launcher.getMain().getMainFrame();

        setLayout(new BorderLayout());

        pauseMenu = new Menu_Pause(this);
        overMenu = new Menu_GameOver(this);

        screen = new Screen(this);
        add(screen, BorderLayout.CENTER);

        bar = new StatusBar(this, hearts);
        add(bar, BorderLayout.PAGE_START);

        Launcher.con.spacer("-");
        Launcher.con.println("created " + name);
    }

    Game(String name) {
        super();
        this.name = name;
        this.frame = Launcher.getMain().getMainFrame();

        setLayout(new BorderLayout());

        pauseMenu = new Menu_Pause(this);
        overMenu = new Menu_GameOver(this);

        screen = new Screen(this);
        add(screen, BorderLayout.CENTER);

        bar = new StatusBar(this, 3);
        add(bar, BorderLayout.PAGE_START);

        Launcher.con.spacer("-");
        Launcher.con.println("created " + name);
    }

    public KeyAdapter getGameKeys() {
        return gameKeys;
    }

    public String getName() {
        return this.name;
    }

    public abstract Graphics syncGraphics(Graphics g);

    protected abstract void key_W(boolean pressed);

    protected abstract void key_A(boolean pressed);

    protected abstract void key_S(boolean pressed);

    protected abstract void key_D(boolean pressed);

    protected abstract void key_SPACE(boolean pressed);

    protected abstract void key_ESC(boolean pressed);

    public abstract void kill();

    void setGameOverSound(String path) {
        this.gameOverSound = path;
    }

    void setBackgroundMusic(String path) {
        this.backgroundMusic = path;
    }

    void startBgMusic() {
        if (!backgroundMusic.equals("none")) {
            Const.playBgSound(backgroundMusic);
        }
    }

    public void game_over() {
        if (!gameOverSound.equals("none")) {
            Const.stopBgSound();
            Const.playSound(gameOverSound);
        }

        halt();
        Launcher.con.printlnWarning("Game Over");
        isPaused = true;
        remove(screen);
        remove(bar);
        add(overMenu, BorderLayout.CENTER);
        frame.removeKeyListener(getGameKeys());
        frame.addKeyListener(overMenu.getMenuKeys());
        halt();
        revalidate();
        repaint();
        frame.requestFocus();
    }

    public void pause() {
        if (isPaused) { // Resume
            if (!backgroundMusic.equals("none")) {
                Const.playBgSound(backgroundMusic);
            }
            isPaused = false;
            remove(pauseMenu);
            add(screen, BorderLayout.CENTER);
            frame.removeKeyListener(pauseMenu.getMenuKeys());
            frame.addKeyListener(getGameKeys());
            resume();
        } else { // Pause
            if (!backgroundMusic.equals("none")) {
                Const.stopBgSound();
            }
            isPaused = true;
            remove(screen);
            add(pauseMenu, BorderLayout.CENTER);
            frame.removeKeyListener(getGameKeys());
            frame.addKeyListener(pauseMenu.getMenuKeys());
            halt();
        }
        revalidate();
        repaint();
        frame.requestFocus();
    }

    protected abstract void halt();

    protected abstract void resume();
}
