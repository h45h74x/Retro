import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RetroMain {

    public static ConsoleFrame con;

    private static RetroMain main;
    private static TestGame testgame;
    private static KeyAdapter gameKeys;
    private MainMenu mainFrame;
    private MainPanel p1;

    private RetroMain() {
        mainFrame = new MainMenu(Const.Strings.name);
        con = new ConsoleFrame(mainFrame);
        Const.LoadFonts();
    }

    public static void main(String[] args) {
        main = new RetroMain();
        main.startGame(0);
    }

    public void startGame(int index) {
        if (index != 0) {
            mainFrame.removeKeyListener(gameKeys);
            mainFrame.remove(p1);
        }
        switch (index) {
            case 1: //Space Invaders
                break;
            case 2: //TestGame
                gameKeys = new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_W) testgame.key_W(true);
                        else if (e.getKeyCode() == KeyEvent.VK_A) testgame.key_A(true);
                        else if (e.getKeyCode() == KeyEvent.VK_S) testgame.key_S(true);
                        else if (e.getKeyCode() == KeyEvent.VK_D) testgame.key_D(true);
                        else if (e.getKeyCode() == KeyEvent.VK_SPACE) testgame.key_SPACE(true);
                        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) testgame.key_ESC(true);
                        else con.println(KeyEvent.getKeyText(e.getKeyCode()));
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_W) testgame.key_W(false);
                        else if (e.getKeyCode() == KeyEvent.VK_A) testgame.key_A(false);
                        else if (e.getKeyCode() == KeyEvent.VK_S) testgame.key_S(false);
                        else if (e.getKeyCode() == KeyEvent.VK_D) testgame.key_D(false);
                        else if (e.getKeyCode() == KeyEvent.VK_SPACE) testgame.key_SPACE(false);
                        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) testgame.key_ESC(false);
                        else con.println(KeyEvent.getKeyText(e.getKeyCode()));
                    }
                };
                testgame = new TestGame(mainFrame, Const.Strings.games[index]);
                mainFrame.add(testgame);
                break;
            default: //MainMenu
                gameKeys = new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        con.println("Key Pressed @ MM");
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        con.println("Key Pressed @ MM");
                    }
                };

                p1 = new MainPanel(Const.Colors.accent_dark(), Const.Colors.accent(), this);
                p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

                mainFrame.add(p1);
                break;
        }
        mainFrame.addKeyListener(gameKeys);
        mainFrame.setVisible(true);
        mainFrame.requestFocus();
    }

    public MainMenu getMainFrame() {
        return mainFrame;
    }
}
