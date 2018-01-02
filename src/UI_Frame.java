import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

class UI_Frame extends JFrame {

    public UI_Frame(String name) {
        setTitle(name);
        try {
            setIconImage(ImageIO.read(getClass().getResourceAsStream(Const.Strings.iconpaths[0])));
            //setIconImage(ImageIO.read(new FileInputStream(Const.Strings.iconpaths[0])));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        getContentPane().setBackground(Const.Colors.background());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(Const.Numbers.width, Const.Numbers.height);
        setLocationRelativeTo(null);
        if (Const.Bools.debug) setLocation(getX() - 200, getY());

        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        requestFocus();
    }
}
