import javax.swing.*;

public class RetroMain {

    public static ConsoleFrame con;

    private Game game;
    private JPanel mainPanel;

    private static RetroMain main;
    private MainMenu mainFrame;

    private RetroMain() {
        mainFrame = new MainMenu(Const.Strings.name);
        con = new ConsoleFrame(mainFrame);
        Const.LoadFonts();
    }

    public static void main(String[] args) {
        main = new RetroMain();
        home();
    }

    public static void home() {
        main.startGame(0);
    }

    public static void extStartGame(int index) {
        main.startGame(index);
    }

    private void detachGame() {
        if (game != null) {
            mainFrame.removeKeyListener(game.getGameKeys());
            game.kill();
            game.setVisible(false);
            game.validate();
            game = null;
        }
        if (mainPanel != null) {
            mainFrame.remove(mainPanel);
            mainPanel.setVisible(false);
            mainPanel.validate();
            mainPanel = null;
        }
    }

    private void attachGame() {
        if (game != null) {
            game.setVisible(true);
            game.revalidate();
            mainFrame.add(game);
            mainFrame.addKeyListener(game.getGameKeys());
        }
        if (mainPanel != null) {
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
            mainFrame.add(mainPanel);
            mainPanel.setVisible(true);
            mainPanel.revalidate();
        }
        mainFrame.repaint();
    }

    public void startGame(int index) {
        detachGame();
        switch (index) {
            case Const.Games.GAME_SELECTOR:
                mainPanel = new GameSelector(this, Const.Games.names[index]);
                break;
            case Const.Games.SPACE_IMPACT:
                break;
            case Const.Games.TESTGAME:
                game = new TestGame(mainFrame, Const.Games.names[index]);
                break;
            default: //MainMenu
                mainPanel = new MainPanel(this, Const.Games.names[index]);
                break;
        }
        attachGame();
        mainFrame.setVisible(true);
        mainFrame.requestFocus();
    }

    public MainMenu getMainFrame() {
        return mainFrame;
    }
}
