import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("Duplicates")
abstract class Game extends JPanel {
    final String name;
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

    Game(String name) {
        super();
        this.name = name;
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

    protected abstract void pause();

    protected abstract void resume();
}
