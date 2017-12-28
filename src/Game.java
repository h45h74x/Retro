import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class Game extends JPanel {

    protected MainMenu frame;
    protected String name;
    protected KeyAdapter gameKeys = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) key_W(true);
            else if (e.getKeyCode() == KeyEvent.VK_A) key_A(true);
            else if (e.getKeyCode() == KeyEvent.VK_S) key_S(true);
            else if (e.getKeyCode() == KeyEvent.VK_D) key_D(true);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) key_SPACE(true);
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) key_ESC(true);
            else RetroMain.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) key_W(false);
            else if (e.getKeyCode() == KeyEvent.VK_A) key_A(false);
            else if (e.getKeyCode() == KeyEvent.VK_S) key_S(false);
            else if (e.getKeyCode() == KeyEvent.VK_D) key_D(false);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) key_SPACE(false);
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) key_ESC(false);
            else RetroMain.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
        }
    };

    public KeyAdapter getGameKeys() {
        return gameKeys;
    }

    protected Game(MainMenu frame, String name) {
        this.frame = frame;
        this.name = name;
        RetroMain.con.spacer("-");
        RetroMain.con.println("created " + name);
    }

    protected Game(MainMenu frame) {
        this.frame = frame;
        this.name = Const.Strings.menutitle;
        RetroMain.con.spacer("-");
        RetroMain.con.println("created " + name);
    }

    public String getName() {
        return this.name;
    }

    protected abstract void key_W(boolean pressed);

    protected abstract void key_A(boolean pressed);

    protected abstract void key_S(boolean pressed);

    protected abstract void key_D(boolean pressed);

    protected abstract void key_SPACE(boolean pressed);

    protected abstract void key_ESC(boolean pressed);

    protected abstract void kill();

    protected abstract void pause();

    protected abstract void gameOver(Graphics g);
}
