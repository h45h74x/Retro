import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

class UI_StatusBar extends JPanel {
    private ImageIcon[] icons;
    private JLabel[] hearts;
    private JLabel scoreLabel;
    private Game parent;

    private final Color bg = Const.Colors.gray_light();

    private Timer timer;

    private final int imageSize = 15;
    private int heartCount = 0;
    private int score = 0;
    private final int yPadding = 10;

    private final int height = imageSize + yPadding;
    private final int width = Const.Numbers.width;

    UI_StatusBar(Game parent, int hearts) {
        super();
        this.parent = parent;
        setHeartCount(hearts);
        setup();
    }

    public int getHeight() {
        return height;
    }

    private void changeColor(int status) {
        switch (status) {
            case 1:
                setBackground(Color.green);
                break;
            case 2:
                setBackground(Color.red);
                break;
            default:
                setBackground(bg);
        }
        timer.start();
    }

    private void setup() {
        timer = new Timer(500, arg0 -> changeColor(0));
        timer.setRepeats(false); // Only execute once

        BorderLayout layout = new BorderLayout();
        layout.setVgap(yPadding);
        setLayout(layout);
        setPreferredSize(new Dimension(width, height));
        changeColor(0);

        loadImages();

        JPanel heartPanel = new JPanel();
        heartPanel.setBackground(bg);
        JLabel username = new JLabel(Const.Variables.username);
        username.setFont(new Font("PixelVerdana", Font.PLAIN, 8));
        heartPanel.add(username);
        hearts = new JLabel[heartCount];
        for (int i = 0; i < hearts.length; i++) {
            hearts[i] = new JLabel(icons[0]);
            hearts[i].setBackground(bg);
            heartPanel.add(hearts[i]);
        }
        add(heartPanel, BorderLayout.LINE_START);

        JButton pause = new JButton(icons[1]);
        pause.addActionListener(e -> pause());
        pause.setContentAreaFilled(false);
        pause.setFocusPainted(false);
        pause.setFocusable(false);
        pause.setBorderPainted(false);
        pause.setForeground(bg);
        pause.setBackground(bg);

        scoreLabel = new JLabel(String.valueOf(score));
        scoreLabel.setFont(new Font("PixelVerdana", Font.PLAIN, 8));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(bg);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(pause, BorderLayout.LINE_END);
        rightPanel.add(scoreLabel, BorderLayout.LINE_START);

        add(rightPanel, BorderLayout.LINE_END);
    }

    private void loadImages() {
        icons = new ImageIcon[2];
        try {
            icons[0] = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(Const.Strings.iconpaths[3])));
            icons[1] = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(Const.Strings.iconpaths[4])));
            //icons[0] = new ImageIcon(ImageIO.read(new FileInputStream(Const.Strings.iconpaths[3])));
            //icons[1] = new ImageIcon(ImageIO.read(new FileInputStream(Const.Strings.iconpaths[4])));
        } catch (Exception ex) {
            Launcher.con.printlnError(ex.toString());
        }
    }

    private void increaseScore(int score) {
        changeColor(1);

        this.score += score;
        scoreLabel.setText(String.valueOf(this.score));
    }

    void increaseScore() {
        increaseScore(1);
    }

    void loseHeart() {
        changeColor(2);

        hearts[heartCount - 1].setVisible(false);
        heartCount -= 1;

        if (heartCount <= 0) parent.game_over();
    }

    private void setHeartCount(int num) {
        heartCount = num;
    }

    private void pause() {
        parent.pause();
    }
}
