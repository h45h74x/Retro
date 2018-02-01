

import java.awt.*;
import java.io.IOException;

@SuppressWarnings("ALL")
final class Const {

    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();


    private Const() {
    }

    public static void LoadFonts() {
        for (int i = 0; i < Strings.fontpaths.length; i++) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, classLoader.getResourceAsStream(Strings.fontpaths[i]));
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

    }

    public static void playSound(String path) {

    }

    public static void stopSound() {
        //AudioPlayer.player.stop(audioStream);
    }

    public static void stopBgSound() {
        //AudioPlayer.player.stop(audioStreamBg);
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static final class Bools {
        public static final boolean debug = true;
        public static final boolean sounds = true;
        public static final boolean music = true;
    }

    public static final class Colors {
        private static final String background = "#000000";
        private static final String lcd = "#b0cab0";
        private static final String gray_dark = "#242424";
        private static final String red = "#c10000";
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

        public static Color gray_dark() {
            return Color.decode(gray_dark);
        }

        public static Color lcd() {
            return Color.decode(lcd);
        }

        public static Color red() {
            return Color.decode(red);
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
        public static final String version = "v1.0";

        public static String username = "Player";

        public static final String start = "Start";
        public static final String pause = "Pause";
        public static final String return_to = "Return to";
        public static final String game_over = "RIP";
        public static final String retry = "Retry";
        public static final String back_to_mm = "Back to Menu";
        public static final String exit = "Exit";
        public static final String choose_game = "Choose Game";
        public static final String back = "Back";
        public static final String name = "Retro";
        public static final String credits = "Credits";
        public static final String greeting = "Welcome,";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String menutitle = "Main Menu";
        public static final String github = "Visit our GitHub Repo!";
        public static final String github_link = "https://github.com/h45h74x/retro";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String website_h45 = "Visit Simons Website!";
        public static final String website_h45_link = "http://h45h74x.eu.org";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String[] iconpaths = {
                "icons/retro.png",
                "icons/arrow_left.png",
                "icons/arrow_right.png",
                "icons/heart.png",
                "icons/pause.png"
        };
        @SuppressWarnings("SpellCheckingInspection")
        public static final String creditText = "Retro is a collection of recoded, old games.\n" +
                "It is entirely written in Java and combines a clean, modern UI with nostalgia. \n" +
                " \n" +
                "Retro was created by Selma Hasanovic (selmah1) and Simon Gruber (h45h74x)";


        private Strings() {
        }

        @SuppressWarnings("SpellCheckingInspection")
        static final String[] fontpaths = {
                "fonts/NotoSans-Black.ttf",
                "fonts/NotoSans-Bold.ttf",
                "fonts/PixelVerdana.ttf",
                "fonts/VeraMono.ttf"
        };

    }

    @SuppressWarnings("SpellCheckingInspection")
    public static final class Menues {
        public static final String[] names = {
                "Main Menu",
                "Game Selector",
                "Credits",
                "Pause"
        };

        public static final String[] icons = {
                "none",
                "none",
                "res/icons/options.png",
                "none"
        };

        public static final int MAIN_MENU = 0;
        public static final int GAME_SELECTOR = 1;
        public static final int OPTIONS = 2;
        public static final int CREDITS = 3;
        public static final int PAUSE = 4;
        public static final int GAME_OVER = 5;
    }

    public static final class Games {
        public static final String[] names = {
                "Space Impact",
                "Snake",
                "SpaceDodge"
        };

        @SuppressWarnings("SpellCheckingInspection")
        public static final String[] icons = {
                "icons/spaceimpact.png",
                "icons/testgame.png",
                "icons/spacedodge.png",
        };

        public static final int SPACE_IMPACT = 0;
        @SuppressWarnings("SpellCheckingInspection")
        public static final int SNAKE = 1;
        public static final int SPACE_DODGE = 2;
    }

    public static final class Numbers {
        public static final int width = 900;
        public static final int con_width = 200;
        public static final int height = 600;
        public static final int selectorPos = 1;
        public static final int creditLength = 170;

        private Numbers() {
        }
    }

    public static final class SpaceImpact {
        @SuppressWarnings("SpellCheckingInspection")
        public static final String version = "v0.1 beta";

        public static final String bg = "icons/SpaceImpact/bg.jpg";

        public static final String[] iconpaths = {
                "icons/SpaceImpact/ship.png",
                "icons/SpaceImpact/enemy_01.png"
        };

        public static final String[] soundpaths = {
                "sounds/SpaceImpact/bg.wav",
                "sounds/SpaceImpact/Beep.wav",
                "sounds/SpaceImpact/GameOver.wav"
        };

        private SpaceImpact() {
        }
    }

    public static final class SpaceDodge {
        @SuppressWarnings("SpellCheckingInspection")
        public static final String version = "v0.1 beta";

        public static final String bg = "icons/SpaceDodge/bg.jpg";

        public static final String[] iconpaths = {
                "icons/SpaceDodge/ship.png"
        };

        public static final String[] soundpaths = {
                "sounds/SpaceDodge/bg.wav",
                "sounds/SpaceDodge/Beep.wav",
                "sounds/SpaceDodge/GameOver.wav"
        };

        private SpaceDodge() {
        }
    }

    public static final class Snake {
        @SuppressWarnings("SpellCheckingInspection")
        public static final String version = "v0.1 beta";

        public static final String[] iconpaths = {
                "icons/Snake/SnakeBody.png",
                "icons/Snake/SnakeLeft.png",
                "icons/Snake/SnakeRight.png",
                "icons/Snake/SnakeUp.png",
                "icons/Snake/SnakeDown.png",
                "icons/Snake/SnakeApple.png"
        };

        public static final String[] soundpaths = {
                "sounds/SpaceDodge/bg.wav",
                "sounds/SpaceDodge/Beep.wav",
                "sounds/SpaceDodge/GameOver.wav"
        };

        private Snake() {
        }
    }
}
