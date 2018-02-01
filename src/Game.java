import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("Duplicates")
abstract class Game extends JPanel /*implements Runnable*/ {

    final String name;
    private String gameOverSound = "none";
    private String backgroundMusic = "none";

    private boolean isPaused = false;

    private Menu_Pause pauseMenu;
    private Menu_GameOver overMenu;

    private final KeyAdapter gameKeys = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) key_W(true);
            else if (e.getKeyCode() == KeyEvent.VK_A) key_A(true);
            else if (e.getKeyCode() == KeyEvent.VK_S) key_S(true);
            else if (e.getKeyCode() == KeyEvent.VK_D) key_D(true);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) key_SPACE(true);
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) key_ESC(true);
            else Launcher.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) key_W(false);
            else if (e.getKeyCode() == KeyEvent.VK_A) key_A(false);
            else if (e.getKeyCode() == KeyEvent.VK_S) key_S(false);
            else if (e.getKeyCode() == KeyEvent.VK_D) key_D(false);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) key_SPACE(false);
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) key_ESC(false);
            else Launcher.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
        }
    };

    UI_Screen screen;
    UI_StatusBar bar;
    private UI_Frame frame;

    Game(String name, int hearts) {
        super();
        this.name = name;
        this.frame = Launcher.getMain().getMainFrame();

        setLayout(new BorderLayout());

        pauseMenu = new Menu_Pause(this);
        overMenu = new Menu_GameOver(this);

        screen = new UI_Screen(this);
        add(screen, BorderLayout.CENTER);

        bar = new UI_StatusBar(this, hearts);
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

        screen = new UI_Screen(this);
        add(screen, BorderLayout.CENTER);

        bar = new UI_StatusBar(this, 3);
        add(bar, BorderLayout.PAGE_START);

        Launcher.con.spacer("-");
        Launcher.con.println("created " + name);
    }

    KeyAdapter getGameKeys() {
        return gameKeys;
    }

    public String getName() {
        return this.name;
    }

    protected abstract Graphics syncGraphics(Graphics g);

    protected abstract void key_W(boolean pressed);

    protected abstract void key_A(boolean pressed);

    protected abstract void key_S(boolean pressed);

    protected abstract void key_D(boolean pressed);

    protected abstract void key_SPACE(boolean pressed);

    protected abstract void key_ESC(boolean pressed);

    protected abstract void kill();

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

    void game_over() {
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

    void pause() {
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
