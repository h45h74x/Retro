public class Launcher {

    public static Debug_Console con;
    private static Launcher main;

    private Game game;
    private Menu menu;
    private UI_Frame mainFrame;

    private Launcher() {
        mainFrame = new UI_Frame(Const.Strings.name);
        con = new Debug_Console(mainFrame);
        Const.LoadFonts();
    }

    public static void main(String[] args) {
        main = new Launcher();
        home();
    }

    public static void extStartGame(int index) {
        if (main == null) return;
        main.startGame(index);
    }

    public static void extStartMenu(int index) {
        if (main == null) return;
        main.startMenu(index);
    }

    public static void home() {
        if (main != null) main.startMenu(0);
    }

    public static void kill() {
        System.exit(0);
    }

    public void startMenu(int index) {
        detach();
        switch (index) {
            case Const.Menues.GAME_SELECTOR:
                menu = new Menu_Games(this);
                break;
            case Const.Menues.CREDITS:
                menu = new Menu_Credits(this);
                break;
            default: //MainMenu
                menu = new Menu_Main(this);
                break;
        }
        attach();
    }

    public void startGame(int index) {
        detach();
        switch (index) {
            case Const.Games.SPACE_IMPACT:
                game = new Game_SpaceImpact(mainFrame, Const.Games.names[index]);
                break;
            case Const.Games.TESTGAME:
                game = new Game_TestGame(mainFrame, Const.Games.names[index]);
                break;
            default: //MainMenu
                menu = new Menu_Main(this);
                break;
        }
        attach();
    }

    private void detach() {
        if (game != null) {
            mainFrame.removeKeyListener(game.getGameKeys());
            mainFrame.remove(game);
            game.kill();
            game.setVisible(false);
            game.validate();
            game = null;
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

    private void attach() {
        if (game != null) {
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

    public UI_Frame getMainFrame() {
        return mainFrame;
    }

}
