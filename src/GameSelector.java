import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSelector extends Game {
    private Color c1 = Const.Colors.accent_dark();
    private Color c2 = Const.Colors.accent();
    private GameScrollPanel scrollPanel;
    private int xPadding = 50;
    private int yPadding = 10;
    private JLabel t1;
    private int position = 0;

    private RetroMain main;

    protected GameSelector(RetroMain main, String name) {
        super(main.getMainFrame(), name);
        this.main = main;

        setBackground(Const.Colors.accent());
        setup();
        revalidate();
        setOpaque(true);
        setVisible(true);
    }

    public void setGamePosition(int i) {
        t1.setText(Const.Games.names[i]);
        position = i;
        RetroMain.con.printInfo(position + " ");
    }

    private void setup() {
        scrollPanel = new GameScrollPanel(this);
        scrollPanel.setBackground(Const.Colors.accent());

        t1 = new JLabel("GameName");
        t1.setForeground(Const.Colors.elements_light());
        t1.setFont(new Font("Noto Sans", Font.PLAIN, 16));
        JLabel t3 = new JLabel("HiScore");
        t3.setForeground(Const.Colors.elements());
        t3.setFont(new Font("Noto Sans", Font.PLAIN, 16));

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Const.Colors.accent());
        textPanel.setLayout(new BorderLayout());
        textPanel.add(t1, BorderLayout.LINE_START);
        textPanel.add(t3, BorderLayout.LINE_END);

        RButton bt1 = new RButton(Const.Strings.start);
        bt1.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RetroMain.con.printlnInfo("Start");
                startGame();
            }
        });
        bt1.setForeground(Color.white);
        RButton bt2 = new RButton(Const.Strings.back);
        bt2.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RetroMain.con.printlnInfo("Back");
                main.startGame(Const.Games.MAIN_MENU);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(bt1, BorderLayout.EAST);
        buttonPanel.add(bt2, BorderLayout.WEST);

        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.setMaximumSize(new Dimension(Const.Numbers.width - xPadding, 100));
        buttonPanel.setMaximumSize(new Dimension(Const.Numbers.width - xPadding, 100));

        bt1.setPreferredSize(new Dimension(250, 50));
        bt2.setPreferredSize(new Dimension(250, 50));

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0, yPadding / 2)));
        add(textPanel);
        add(Box.createRigidArea(new Dimension(0, yPadding / 2)));
        add(scrollPanel);
        add(Box.createRigidArea(new Dimension(0, yPadding)));
        add(buttonPanel);
        add(Box.createRigidArea(new Dimension(0, yPadding)));
    }

    private void startGame() {
        RetroMain.con.println("ext starting " + Const.Games.names[position] + " @ " + position);
        RetroMain.extStartGame(position);

    }

    @Override
    protected void key_W(boolean pressed) {

    }

    @Override
    protected void key_A(boolean pressed) {
        scrollPanel.left();
    }

    @Override
    protected void key_S(boolean pressed) {
        scrollPanel.right();
    }

    @Override
    protected void key_D(boolean pressed) {

    }

    @Override
    protected void key_SPACE(boolean pressed) {
        startGame();
    }

    @Override
    protected void key_ESC(boolean pressed) {

    }

    @Override
    protected void kill() {

    }

    @Override
    protected void pause() {

    }

    @Override
    protected void gameOver(Graphics g) {

    }
}
