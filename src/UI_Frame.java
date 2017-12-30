import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class UI_Frame extends JFrame {
    private String name;
    private GridBagConstraints c;

    public UI_Frame(String name) {
        this.name = name;
        this.c = c;

        setTitle(name);


        try {
            setIconImage(ImageIO.read(new FileInputStream(Const.Strings.iconpaths[0])));
        } catch (IOException e) {
            Launcher.con.printlnError(e.toString());
        }
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
