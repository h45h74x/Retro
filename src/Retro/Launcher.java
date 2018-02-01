package Retro;

import Retro.Debug.Debug_Console;
import Retro.Game.Game;
import Retro.Game.Game_Snake;
import Retro.Game.Game_SpaceDodge;
import Retro.Game.Game_SpaceImpact;
import Retro.Menu.*;
import Retro.UI.Frame;

public class Launcher {

    public static Debug_Console con;
    private static Launcher main;
    private static int lastGameIndex;
    private final Frame mainFrame;
    private Thread gameThread;
    private Game game;
    private Menu menu;

    private Launcher() {
        mainFrame = new Frame(Const.Strings.name);
        con = new Debug_Console(mainFrame);
        Const.LoadFonts();
    }

    public static void main(String[] args) {
        main = new Launcher();
        home();
    }

    public static void extStartMenu(int index) {
        if (main == null) return;
        main.startMenu(index);
    }

    public static void extStartGame(int index) {
        if (main == null) return;
        main.startGame(index);
    }

    public static void extStartGame() {
        if (main == null) return;
        main.startGame(lastGameIndex);
    }

    public static void home() {
        if (main != null) main.startMenu(0);
    }

    public static void kill() {
        System.exit(0);
    }

    public static Launcher getMain() {
        return main;
    }

    private void attach() {
        if (game != null) {
            /*gameThread = new Thread(game);
            gameThread.start();*/

            game.setVisible(true);
            game.revalidate();
            mainFrame.add(game);
            mainFrame.addKeyListener(game.getGameKeys());
        }
        if (menu != null) {
            menu.setVisible(true);
            menu.revalidate();
            mainFrame.add(menu);
            mainFrame.addKeyListener(menu.getMenuKeys());
        }
        mainFrame.repaint();
        mainFrame.revalidate();
        mainFrame.setVisible(true);
        mainFrame.requestFocus();
    }

    private void detach() {
        if (game != null) {
            mainFrame.removeKeyListener(game.getGameKeys());
            mainFrame.remove(game);
            con.printlnWarning("Killed " + game.getName());
            game.kill();
            game.setVisible(false);
            game.validate();
            game = null;
            /*if (gameThread != null) {
                gameThread.stop();
                gameThread = null;
            }*/
        }
        if (menu != null) {
            mainFrame.removeKeyListener(menu.getMenuKeys());
            mainFrame.remove(menu);
            menu.setVisible(false);
            menu.validate();
            menu = null;
        }
        mainFrame.repaint();
        mainFrame.revalidate();
        mainFrame.setVisible(true);
        mainFrame.requestFocus();
    }

    private void startMenu(int index) {
        detach();
        switch (index) {
            case Const.Menues.GAME_SELECTOR:
                menu = new Menu_Games(this);
                break;
            case Const.Menues.CREDITS:
                menu = new Menu_Credits(this);
                break;
            case Const.Menues.PAUSE:
                menu = new Menu_Pause(this);
                break;
            case Const.Menues.GAME_OVER:
                menu = new Menu_GameOver(this);
                break;
            default: //MainMenu
                menu = new Menu_Main(this);
                break;
        }
        attach();
    }

    private void startGame(int index) {
        detach();
        lastGameIndex = index;
        switch (index) {
            case Const.Games.SPACE_IMPACT:
                game = new Game_SpaceImpact(Const.Games.names[index]);
                break;
            case Const.Games.SNAKE:
                game = new Game_Snake(Const.Games.names[index]);
                break;
            case Const.Games.SPACE_DODGE:
                game = new Game_SpaceDodge(Const.Games.names[index]);
                break;
            default: //MainMenu
                menu = new Menu_Main(this);
                break;
        }
        attach();
    }

    public Frame getMainFrame() {
        return mainFrame;
    }

}