import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class Input_Arrow extends JButton {
    private String iconpath;
    private int width;

    public Input_Arrow(boolean left) {
        super();
        this.width = 50;
        setForeground(Const.Colors.elements_light());
        setBorderPainted(false);
        setFocusPainted(false);
        setFocusable(false);
        setBackground(Const.Colors.accent_dark());

        if (left) iconpath = Const.Strings.iconpaths[1];
        else iconpath = Const.Strings.iconpaths[2];

        try {
            Image img = ImageIO.read(new FileInputStream(iconpath));
            img = img.getScaledInstance(width, width, java.awt.Image.SCALE_SMOOTH);

            setIcon(new ImageIcon(img));
            Launcher.con.printlnWarning("Loaded " + iconpath);
        } catch (Exception ex) {
            Launcher.con.printlnError(ex.toString());
        }
    }
}
