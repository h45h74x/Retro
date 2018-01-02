import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({"Duplicates", "ConstantConditions"})
abstract class Menu extends JPanel {
    private final KeyAdapter menuKeys = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) key_W(true);
            else if (e.getKeyCode() == KeyEvent.VK_A) key_A(true);
            else if (e.getKeyCode() == KeyEvent.VK_S) key_S(true);
            else if (e.getKeyCode() == KeyEvent.VK_D) key_D(true);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) key_SPACE(true);
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) key_ESC(true);
            else Launcher.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) key_W(false);
            else if (e.getKeyCode() == KeyEvent.VK_A) key_A(false);
            else if (e.getKeyCode() == KeyEvent.VK_S) key_S(false);
            else if (e.getKeyCode() == KeyEvent.VK_D) key_D(false);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) key_SPACE(false);
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) key_ESC(false);
            else Launcher.con.printInfo(KeyEvent.getKeyText(e.getKeyCode()));
        }
    };

    protected Color black = Const.Colors.elements();
    UI_Frame frame;
    Launcher main;
    String activegame;
    private boolean enableGradient = true;
    private Color c1 = Const.Colors.accent();
    private Color c2 = Const.Colors.accent_dark();
    private Point initialClick;

    Menu(Launcher main) {
        super();
        this.main = main;
        this.frame = main.getMainFrame();
        create();
    }

    Menu(Launcher main, String activegame) {
        super();
        this.main = main;
        this.frame = main.getMainFrame();
        this.activegame = activegame;
        create();
    }

    private void create() {
        setBackground(Const.Colors.accent_dark());
        setup();
        setOpaque(true);
        setVisible(true);

        Launcher.con.spacer("-");
        Launcher.con.println("Created Menu");

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;

                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
                Launcher.con.setLocation(X + frame.getWidth(), Y);
            }
        });

    }

    KeyAdapter getMenuKeys() {
        return menuKeys;
    }

    void back() {
        Launcher.con.printlnInfo("Back");
        Launcher.extStartMenu(Const.Menues.MAIN_MENU);
    }

    void disableGradient() {
        enableGradient = false;
    }

    void redGradient() {
        c1 = Const.Colors.red();
        c2 = Const.Colors.gray_dark();
        black = Const.Colors.accent_dark();
    }

    protected abstract void setup();

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!enableGradient) return;

        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        Paint gradient = new GradientPaint(
                0, 0, c1,
                0, height, c2,
                true
        );
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
    }

    protected abstract void key_W(boolean pressed);

    protected abstract void key_A(boolean pressed);

    protected abstract void key_S(boolean pressed);

    protected abstract void key_D(boolean pressed);

    protected abstract void key_SPACE(boolean pressed);

    protected abstract void key_ESC(boolean pressed);

}
