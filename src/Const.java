import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class Const {

    private Const() {
    }

    public static void LoadFonts() {

        for (int i = 0; i < Strings.paths.length; i++) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File(Strings.paths[i]));
                RetroMain.con.println(font.getFontName());
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
        private static final String elements = "#242424";
        private static final String elements_light = "#b7b7b7";
        private static final String accent = "#16222a"; //16222a
        private static final String accent_dark = "#a5c3d1"; //3a6073

        private Colors() {
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
    }

    public static final class Strings {
        public static final String name = "Space Invaders";
        public static final String start = "Start";
        public static final String options = "Options";
        public static final String title = "Welcome,";
        public static final String[] paths = {
                "res/NotoSans-Black.ttf",
                "res/NotoSans-Bold.ttf",
                "res/PixelVerdana.ttf",
                "res/VeraMono.ttf"
        };

        private Strings() {
        }

    }

    public static final class Numbers {
        public static int width = 900;
        public static int height = 600;

        private Numbers() {
        }
    }
}
