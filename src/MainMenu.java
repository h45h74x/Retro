import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private String name;
    private GridBagConstraints c;

    public MainMenu(String name) {
        this.name = name;
        this.c = c;

        setTitle(name);

        getContentPane().setBackground(Const.Colors.background());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(Const.Numbers.width, Const.Numbers.height);
        setLocationRelativeTo(null);
        if (Const.Bools.debug) setLocation(getX() - 200, getY());

        setResizable(false);
        setVisible(true);
        requestFocus();
    }
}
