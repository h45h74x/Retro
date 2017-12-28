import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class ArrowButton extends JButton {
    private String iconpath;
    private int width;

    public ArrowButton(boolean left) {
        super();
        this.width = 50;
        setForeground(Const.Colors.elements_light());
        setBorderPainted(false);
        setFocusPainted(false);
        setFocusable(false);
        setBackground(Const.Colors.accent());

        if (left) iconpath = Const.Strings.iconpaths[0];
        else iconpath = Const.Strings.iconpaths[1];

        try {
            Image img = ImageIO.read(new FileInputStream(iconpath));
            img = img.getScaledInstance(width, width, java.awt.Image.SCALE_SMOOTH);

            setIcon(new ImageIcon(img));
            RetroMain.con.printlnWarning("Loaded " + iconpath);
        } catch (Exception ex) {
            RetroMain.con.printlnError(ex.toString());
        }
    }
}
