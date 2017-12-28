import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class GameScrollPanel extends JPanel {

    private JButton left;
    private JButton right;
    private JButton middle;

    private Image[] icons;
    private Image[] iconsSmall;

    private Image icon_left;
    private Image icon_middle;
    private Image icon_right;

    private GameSelector parent;

    private int height;

    private int position = 0;
    private int game;
    private int imageSize = 160;
    private int imageSizeSmall = 100;
    private int yPadding = 50;
    private int yPos = 500;

    public GameScrollPanel(GameSelector parent) {
        super();
        this.parent = parent;
        height = getHeight();
        setup();
    }

    private void setup() {
        setLayout(new BorderLayout());

        left = new JButton();
        left.setPreferredSize(new Dimension(imageSizeSmall, imageSizeSmall));
        left.setAlignmentY(Component.CENTER_ALIGNMENT);
        left.setBackground(Const.Colors.accent());
        left.setFocusPainted(false);
        left.setFocusable(false);
        left.setBorderPainted(false);

        middle = new JButton();
        middle.setPreferredSize(new Dimension(imageSize, imageSize));
        middle.setAlignmentY(Component.CENTER_ALIGNMENT);
        middle.setBackground(Const.Colors.accent());
        middle.setFocusPainted(false);
        middle.setFocusable(false);
        middle.setBorderPainted(false);

        right = new JButton();
        right.setPreferredSize(new Dimension(imageSizeSmall, imageSizeSmall));
        right.setAlignmentY(Component.CENTER_ALIGNMENT);
        right.setBackground(Const.Colors.accent());
        right.setFocusPainted(false);
        right.setFocusable(false);
        right.setBorderPainted(false);

        loadImages();
        swapImages(position);

        JPanel scrollPanel = new JPanel();
        scrollPanel.setBackground(Const.Colors.accent());
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.LINE_AXIS));
        scrollPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        scrollPanel.setPreferredSize(new Dimension(0, yPos));
        scrollPanel.add(left);
        scrollPanel.add(Box.createRigidArea(new Dimension(yPadding, 0)));
        scrollPanel.add(middle);
        scrollPanel.add(Box.createRigidArea(new Dimension(yPadding, 0)));
        scrollPanel.add(right);

        JPanel wrapper = new JPanel();
        wrapper.setBackground(Const.Colors.accent());
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));
        wrapper.add(scrollPanel);
        add(wrapper, BorderLayout.CENTER);


        ArrowButton bt_left = new ArrowButton(true);
        bt_left.setPreferredSize(new Dimension(50, height));
        bt_left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                left();
            }
        });
        add(bt_left, BorderLayout.LINE_START);

        ArrowButton bt_right = new ArrowButton(false);
        bt_right.setPreferredSize(new Dimension(50, height));
        bt_right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                right();
            }
        });
        add(bt_right, BorderLayout.LINE_END);
    }

    private void swapImages(int i) {
        position = i;
        game = position + 2;

        switch (position) {
            case 0:
                icon_left = iconsSmall[2];
                icon_middle = icons[0];
                icon_right = iconsSmall[1];
                break;
            case 1:
                icon_left = iconsSmall[0];
                icon_middle = icons[1];
                icon_right = iconsSmall[2];
                break;
            case 2:
                icon_left = iconsSmall[1];
                icon_middle = icons[2];
                icon_right = iconsSmall[0];
                break;
        }

        left.setIcon(new ImageIcon(icon_left));
        middle.setIcon(new ImageIcon(icon_middle));
        right.setIcon(new ImageIcon(icon_right));
    }

    private void loadImages() {
        icons = new Image[3];
        iconsSmall = new Image[3];
        try {
            icons[0] = ImageIO.read(new FileInputStream(Const.Games.icons[0]));
            icons[1] = ImageIO.read(new FileInputStream(Const.Games.icons[1]));
            icons[2] = ImageIO.read(new FileInputStream(Const.Games.icons[2]));
        } catch (Exception ex) {
            RetroMain.con.printlnError(ex.toString());
        }

        iconsSmall[0] = icons[0].getScaledInstance(imageSizeSmall, imageSizeSmall, java.awt.Image.SCALE_SMOOTH);
        iconsSmall[1] = icons[1].getScaledInstance(imageSizeSmall, imageSizeSmall, java.awt.Image.SCALE_SMOOTH);
        iconsSmall[2] = icons[2].getScaledInstance(imageSizeSmall, imageSizeSmall, java.awt.Image.SCALE_SMOOTH);

        icons[0] = icons[0].getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
        icons[1] = icons[1].getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
        icons[2] = icons[2].getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
    }

    public void right() {
        int swap = position - 1;
        if (swap < 0) swapImages(icons.length - 1);
        else swapImages(swap);
        parent.setGamePosition(game);
    }

    public void left() {
        int swap = position + 1;
        if (swap >= icons.length) swapImages(0);
        else swapImages(swap);
        parent.setGamePosition(game);
    }


}
