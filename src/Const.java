import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class Const {

    private Const() {
    }

    public static void LoadFonts() {

        for (int i = 0; i < Strings.fontpaths.length; i++) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File(Strings.fontpaths[i]));
                RetroMain.con.printlnWarning("loaded " + font.getFontName());
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);

            } catch (FontFormatException e) {
                RetroMain.con.println(e.toString());
            } catch (IOException e) {
                RetroMain.con.println(e.toString());
            }
        }
    }

    public static class Variables {
        public static String username = "Player";
    }

    public static final class Bools {
        public static final boolean debug = true;
    }

    public static final class Colors {
        private static final String background = "#000000";
        private static final String elements = "#444444";
        private static final String elements_light = "#b7b7b7";
        private static final String accent = "#16222a"; //16222a
        private static final String accent_dark = "#a5c3d1"; //3a6073
        private static final String accent_light = "#3bb4ed"; //3a6073

        private Colors() {
        }

        public static Color transparent() {
            return new Color(0, 0, 0, 0);
        }

        public static Color transparentWhite() {
            return new Color(255, 255, 255, 30);
        }

        public static Color background() {
            return Color.decode(background);
        }

        public static Color elements_light() {
            return Color.decode(elements_light);
        }

        public static Color elements() {
            return Color.decode(elements);
        }

        public static Color accent() {
            return Color.decode(accent);
        }

        public static Color accent_dark() {
            return Color.decode(accent_dark);
        }

        public static Color accent_light() {
            return Color.decode(accent_light);
        }
    }

    public static final class Strings {
        public static final String start = "Start";
        public static final String choose_game = "Choose Game";
        public static final String back = "Back";
        public static final String name = "Retro";
        public static final String credits = "Credits";
        public static final String title = "Welcome,";
        public static final String menutitle = "Main Menu";
        public static final String[] fontpaths = {
                "res/fonts/NotoSans-Black.ttf",
                "res/fonts/NotoSans-Bold.ttf",
                "res/fonts/PixelVerdana.ttf",
                "res/fonts/VeraMono.ttf"
        };
        public static final String[] iconpaths = {
                "res/icons/arrow_left.png",
                "res/icons/arrow_right.png"
        };

        private Strings() {
        }

    }

    public static final class Games {
        public static final String[] names = {
                "Main Menu",
                "Game Selector",
                "Options",
                "Space Impact",
                "TestGame"
        };

        public static final String[] icons = {
                "res/icons/options.png",
                "res/icons/play_games.png",
                "res/icons/pacman.png"
        };

        public static final int MAIN_MENU = 0;
        public static final int GAME_SELECTOR = 1;
        public static final int SPACE_IMPACT = 2;
        public static final int TESTGAME = 3;
    }

    public static final class Numbers {
        public static int width = 900;
        public static int height = 600;

        private Numbers() {
        }
    }
}
