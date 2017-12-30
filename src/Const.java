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
                Launcher.con.printlnWarning("loaded " + font.getFontName());
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);

            } catch (FontFormatException e) {
                Launcher.con.println(e.toString());
            } catch (IOException e) {
                Launcher.con.println(e.toString());
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
        private static final String accent_dark = "#16222a"; //16222a
        private static final String accent = "#a5c3d1"; //3a6073
        private static final String accent_light = "#3bb4ed"; //3a6073
        private static final String gray_light = "#d3d3d3"; //3a6073

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

        public static Color gray_light() {
            return Color.decode(gray_light);
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
        public static final String greeting = "Welcome,";
        public static final String menutitle = "Main Menu";
        public static final String github = "Visit our GitHub Repo!";
        public static final String github_link = "https://github.com/h45h74x/retro";
        public static final String website_h45 = "Visit Simons Website!";
        public static final String website_h45_link = "http://h45h74x.eu.org";

        public static final String[] fontpaths = {
                "res/fonts/NotoSans-Black.ttf",
                "res/fonts/NotoSans-Bold.ttf",
                "res/fonts/PixelVerdana.ttf",
                "res/fonts/VeraMono.ttf"
        };
        public static final String[] iconpaths = {
                "res/icons/retro.png",
                "res/icons/arrow_left.png",
                "res/icons/arrow_right.png",
                "res/icons/heart.png",
                "res/icons/pause.png"
        };


        private Strings() {
        }

        public static final String creditText = "Retro is a collection of recoded, old games.\n" +
                "It is entirely written in Java and combines a clean, modern UI with nostalgia. \n" +
                " \n" +
                "Retro was created by Selma Hasanovic (selmah1) and Simon Gruber (h45h74x)";

    }

    public static final class Menues {

        public static final String[] names = {
                "Main Menu",
                "Game Selector",
                "Credits"
        };

        public static final String[] icons = {
                "none",
                "none",
                "res/icons/options.png"
        };

        public static final int MAIN_MENU = 0;
        public static final int GAME_SELECTOR = 1;
        public static final int OPTIONS = 2;
        public static final int CREDITS = 3;
    }

    public static final class Games {
        public static final String[] names = {
                "Space Impact",
                "Test Game",
                "No Game"
        };

        public static final String[] icons = {
                "res/icons/spaceimpact.png",
                "res/icons/testgame.png",
                "res/icons/nogame.png",
        };

        public static final int SPACE_IMPACT = 0;
        public static final int TESTGAME = 1;
    }

    public static final class Numbers {
        public static int width = 900;
        public static int height = 600;
        public static int selectorPos = 1;
        public static int creditLength = 170;

        private Numbers() {
        }
    }

    public static final class SpaceImpact {
        private static final String background = "#78a57f";
        private static final String[] iconpaths = {
                "heart",
                "pause"
        };

        private SpaceImpact() {
        }

        public static Color background() {
            return Color.decode(background);
        }
    }
}
