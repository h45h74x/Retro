import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class Menu_Games extends Menu {
    private static final JLabel t1 = new JLabel();
    private GameScrollPanel scrollPanel;
    private int position;

    Menu_Games(Launcher main) {
        super(main);
    }

    protected void setup() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        disableGradient();

        scrollPanel = new GameScrollPanel(this);
        scrollPanel.setBackground(Const.Colors.accent_dark());
        setGamePosition(scrollPanel.getPosition());

        t1.setForeground(Const.Colors.elements_light());
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 16));

        JLabel t2 = new JLabel("HiScore");
        t2.setForeground(Const.Colors.elements());
        t2.setFont(new Font("Noto Sans", Font.PLAIN, 16));

        Input_RButton bt1 = new Input_RButton(Const.Strings.start);
        bt1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        bt1.addActionListener(e -> {
            Launcher.con.printlnInfo("Start");
            startGame();
        });
        bt1.setForeground(Color.white);
        bt1.setPreferredSize(new Dimension(250, 50));

        Input_RButton bt2 = new Input_RButton(Const.Strings.back);
        bt2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        bt2.addActionListener(e -> back());
        bt2.setForeground(Color.white);
        bt2.setPreferredSize(new Dimension(250, 50));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Const.Colors.accent_dark());
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(bt1, BorderLayout.EAST);
        buttonPanel.add(bt2, BorderLayout.WEST);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Const.Colors.accent_dark());
        textPanel.setLayout(new BorderLayout());
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(t1, BorderLayout.LINE_START);
        textPanel.add(t2, BorderLayout.LINE_END);

        int xPadding = 50;
        textPanel.setMaximumSize(new Dimension(Const.Numbers.width - xPadding, 100));
        buttonPanel.setMaximumSize(new Dimension(Const.Numbers.width - xPadding, 100));

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        int yPadding = 40;
        add(Box.createRigidArea(new Dimension(0, yPadding / 2)));
        add(textPanel);
        add(Box.createRigidArea(new Dimension(0, yPadding / 2)));
        add(scrollPanel);
        add(Box.createRigidArea(new Dimension(0, yPadding)));
        add(buttonPanel);
        add(Box.createRigidArea(new Dimension(0, yPadding)));
    }

    @Override
    protected void key_W(boolean pressed) {

    }

    @Override
    protected void key_A(boolean pressed) {
        if (pressed) scrollPanel.left();
    }

    @Override
    protected void key_S(boolean pressed) {

    }

    @Override
    protected void key_D(boolean pressed) {
        if (pressed) scrollPanel.right();
    }

    @Override
    protected void key_SPACE(boolean pressed) {
        if (pressed) startGame();
    }

    @Override
    protected void key_ESC(boolean pressed) {
        if (pressed) back();
    }

    private void setGamePosition(int i) {
        t1.setText(Const.Games.names[i]);
        position = i;
        Launcher.con.printInfo(position + " ");
    }

    private void startGame() {
        Launcher.con.println("ext starting " + Const.Games.names[position] + " @ " + position);
        Launcher.extStartGame(position);
    }

    static class GameScrollPanel extends JPanel {
        private JButton left;
        private JButton right;
        private JButton middle;

        private Image[] icons;
        private Image[] iconsSmall;

        private Image icon_left;
        private Image icon_middle;
        private Image icon_right;

        private final Menu_Games parent;
        private final int height;

        private final int imageSize = 160;
        private final int imageSizeSmall = 100;
        private final int yPadding = 50;
        private final int yPos = 500;

        private int position = Const.Numbers.selectorPos;
        private final int numgames = Const.Games.names.length;

        GameScrollPanel(Menu_Games parent) {
            super();
            this.parent = parent;
            height = getHeight();
            setup();
        }

        int getPosition() {
            return position;
        }

        private void setup() {
            setLayout(new BorderLayout());
            setBackground(Const.Colors.accent_dark());

            left = new JButton();
            left.setPreferredSize(new Dimension(imageSizeSmall, imageSizeSmall));
            left.setAlignmentY(Component.CENTER_ALIGNMENT);
            left.setContentAreaFilled(false);
            left.setFocusPainted(false);
            left.setFocusable(false);
            left.setBorderPainted(false);

            middle = new JButton();
            middle.setPreferredSize(new Dimension(imageSize, imageSize));
            middle.setAlignmentY(Component.CENTER_ALIGNMENT);
            middle.setContentAreaFilled(false);
            middle.setFocusPainted(false);
            middle.setFocusable(false);
            middle.setBorderPainted(false);

            right = new JButton();
            right.setPreferredSize(new Dimension(imageSizeSmall, imageSizeSmall));
            right.setAlignmentY(Component.CENTER_ALIGNMENT);
            right.setContentAreaFilled(false);
            right.setFocusPainted(false);
            right.setFocusable(false);
            right.setBorderPainted(false);

            loadImages();
            swapImages(position);

            JPanel scrollPanel = new JPanel();
            scrollPanel.setBackground(Const.Colors.accent_dark());
            scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.LINE_AXIS));
            scrollPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            scrollPanel.setPreferredSize(new Dimension(0, yPos));
            scrollPanel.add(left);
            scrollPanel.add(Box.createRigidArea(new Dimension(0, yPadding)));
            scrollPanel.add(middle);
            scrollPanel.add(Box.createRigidArea(new Dimension(0, yPadding)));
            scrollPanel.add(right);

            JPanel wrapper = new JPanel();
            wrapper.setBackground(Const.Colors.accent_dark());
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));
            wrapper.add(scrollPanel);
            add(wrapper, BorderLayout.CENTER);

            Input_Arrow bt_left = new Input_Arrow(true);
            bt_left.setPreferredSize(new Dimension(50, height));
            bt_left.addActionListener(e -> left());
            add(bt_left, BorderLayout.LINE_START);

            Input_Arrow bt_right = new Input_Arrow(false);
            bt_right.setPreferredSize(new Dimension(50, height));
            bt_right.addActionListener(e -> right());
            add(bt_right, BorderLayout.LINE_END);
        }

        private void swapImages(int i) {
            position = i;

            if (position > 0 && position < numgames - 1) {
                icon_left = iconsSmall[position - 1];
                icon_middle = icons[position];
                icon_right = iconsSmall[position + 1];
            } else if (position == 0) {
                icon_left = icons[position];
                icon_middle = iconsSmall[position + 1];
                icon_right = iconsSmall[position + 2];
            } else if (position >= numgames - 1) {
                icon_left = iconsSmall[position - 2];
                icon_middle = iconsSmall[position - 1];
                icon_right = icons[position];
            }

            left.setIcon(new ImageIcon(icon_left));
            middle.setIcon(new ImageIcon(icon_middle));
            right.setIcon(new ImageIcon(icon_right));

            parent.setGamePosition(i);
        }

        private void loadImages() {
            icons = new Image[numgames];
            iconsSmall = new Image[numgames];

            for (int i = 0; i < numgames; i++) {
                try {
                    icons[i] = ImageIO.read(new FileInputStream(Const.Games.icons[i]));
                } catch (Exception ex) {
                    Launcher.con.printlnError(ex.toString());
                }
                iconsSmall[i] = icons[i].getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                icons[i] = icons[i].getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);

            }
        }

        void right() {
            int swap = position + 1;
            if (swap >= numgames - 1) {
                swap = numgames - 1;
            }
            swapImages(swap);
        }

        void left() {
            int swap = position - 1;
            if (swap < 0) {
                swap = 0;
            }
            swapImages(swap);
        }

    }
}
