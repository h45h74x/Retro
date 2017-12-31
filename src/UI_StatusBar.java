import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

//Hearts    Boss bar    (Score &) Pause

class UI_StatusBar extends JPanel {
    private ImageIcon[] icons;
    private JLabel[] hearts;
    private Game parent;

    private final Color bg = Const.Colors.gray_light();

    private final int imageSize = 15;
    private int heartCount = 0;
    private final int yPadding = 10;

    private final int height = imageSize + yPadding;
    private final int width = Const.Numbers.width;

    private UI_StatusBar() {
    }

    UI_StatusBar(Game parent) {
        super();
        this.parent = parent;
        setup();
    }

    UI_StatusBar(Game parent, int hearts) {
        super();
        this.parent = parent;
        setHeartCount(hearts);
        setup();
    }

    private void setup() {
        BorderLayout layout = new BorderLayout();
        layout.setVgap(yPadding);
        setLayout(layout);

        loadImages();

        setBackground(bg);
        setPreferredSize(new Dimension(width, height));


        /*label = new JLabel();
        add(label, BorderLayout.CENTER);*/

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
        add(pause, BorderLayout.LINE_END);
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

    int getHeartCount() {
        return heartCount;
    }

    private void setHeartCount(int num) {
        heartCount = num;
    }

    void loseHeart() {
        hearts[heartCount - 1].setVisible(false);
        setHeartCount(heartCount - 1);
    }

    void gainHeart() {
        setHeartCount(heartCount + 1);
        hearts[heartCount].setVisible(true);
    }

    private void pause() {
        parent.pause();
    }
}
