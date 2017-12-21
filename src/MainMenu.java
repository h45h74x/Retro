import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private String name;
    private GridBagConstraints c;

    public MainMenu(String name) {
        this.name = name;
        this.c = c;

        setTitle(name);
        setSize(Const.Numbers.width, Const.Numbers.height);
        getContentPane().setBackground(Const.Colors.background());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        if (Const.Bools.debug) setLocation(getX() - 200, getY());
        setResizable(false);
    }


    public void display() {
        setVisible(true);
    }

    public void dispose() {
        setVisible(false);
    }

}
