package Retro.Managers;

import Retro.Const;
import Retro.Launcher;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pacman_Map {

    private int[][] walls;
    private int[][] pills;
    private int[][] fruits;
    private BufferedImage map;
    private BufferedImage dummy;
    private String path;

    public Pacman_Map(String map) {
        path = map;
        loadMap();
    }

    public void loadMap() {
        try {
            this.map = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            Launcher.con.printlnError(e);
        }
        if (map.getWidth() > Const.Numbers.width || map.getHeight() > Const.Numbers.height)
            Launcher.con.printlnError("Map too big!" + map.getWidth() + " " + map.getHeight());
    }

    public BufferedImage getMapImage() {
        return map;
    }

    public BufferedImage getDummy() {
        return dummy;
    }

    public int[][] getWalls() {
        return walls;
    }

    public int[][] getPills() {
        return pills;
    }

    public int[][] getFruits() {
        return fruits;
    }

}
