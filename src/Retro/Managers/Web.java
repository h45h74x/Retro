package Retro.Managers;

import Retro.Launcher;

import java.awt.*;
import java.net.URL;

public class Web {
    public Web() {
    }

    public void openInBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            Launcher.con.printlnError(e.toString());
        }
    }
}
