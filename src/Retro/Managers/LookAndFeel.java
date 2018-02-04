package Retro.Managers;

import Retro.Const;
import Retro.Debug.Console;
import Retro.Launcher;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public final class LookAndFeel {

    private static AudioClip bgPlayer;
    private static AudioClip soundPlayer;
    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    private static JFXPanel jfx = null;

    private static void initJFX() {
        if (jfx != null) return;
        jfx = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Launcher.con.printInfo("JFX Summoned");
            }
        });
    }

    public static Image getImage(String p) {
        try {
            return ImageIO.read(classLoader.getResourceAsStream(p));
        } catch (IOException e) {
            Console.staticPrintlnError(e);
        }
        return null;
    }

    public static void LoadFonts() {
        for (int i = 0; i < Const.Strings.fontpaths.length; i++) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, classLoader.getResourceAsStream(Const.Strings.fontpaths[i]));
                //Font font = Font.createFont(Font.TRUETYPE_FONT, new File(Strings.fontpaths[i]));
                Launcher.con.printlnWarning("loaded " + font.getFontName());
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);

            } catch (FontFormatException | IOException e) {
                Launcher.con.printlnError(e.toString());
            }
        }
    }

    public static void playBgSound(String path) {
        initJFX();
        stopBgSound();
        bgPlayer = new AudioClip(classLoader.getResource(path).toString());
        bgPlayer.setCycleCount(AudioClip.INDEFINITE);

        bgPlayer.play();
    }

    public static void playSound(String path) {
        initJFX();
        stopSound();
        soundPlayer = new AudioClip(classLoader.getResource(path).toString());
        soundPlayer.play();
    }

    public static void stopSound() {
        if (soundPlayer != null) soundPlayer.stop();
    }

    public static void stopBgSound() {
        if (bgPlayer != null) bgPlayer.stop();
    }
}
