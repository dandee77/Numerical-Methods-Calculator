package src;
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.RED;
import static com.raylib.Jaylib.GREEN;
import static com.raylib.Jaylib.BLUE;
import static com.raylib.Jaylib.YELLOW;
import static com.raylib.Jaylib.ORANGE;
import static com.raylib.Jaylib.PINK;
import static com.raylib.Jaylib.PURPLE;
import static com.raylib.Jaylib.SKYBLUE;
import static com.raylib.Jaylib.LIME;
import static com.raylib.Jaylib.GOLD;
import static com.raylib.Jaylib.VIOLET;
import static com.raylib.Jaylib.DARKGRAY;
import static com.raylib.Jaylib.LIGHTGRAY;
import static com.raylib.Jaylib.BROWN;
import static com.raylib.Jaylib.MAROON;
import static com.raylib.Raylib.Vector2;
import static com.raylib.Raylib.Rectangle;
import static com.raylib.Raylib.Color;

public class Utils {
    public static final Color white = RAYWHITE;
    public static final Color black = BLACK;
    public static final Color red = RED;
    public static final Color green = GREEN;
    public static final Color blue = BLUE;
    public static final Color yellow = YELLOW;
    public static final Color orange = ORANGE;
    public static final Color pink = PINK;
    public static final Color purple = PURPLE;
    public static final Color skyblue = SKYBLUE;
    public static final Color lime = LIME;
    public static final Color gold = GOLD;
    public static final Color violet = VIOLET;
    public static final Color darkgray = DARKGRAY;
    public static final Color lightgray = LIGHTGRAY;
    public static final Color brown = BROWN;
    public static final Color maroon = MAROON;


    @SuppressWarnings("resource") // just get ts stfu
    public static Vector2 vec2(float x, float y) {
        return new Vector2().x(x).y(y); // closing ts was causing memory corruption
    }

    @SuppressWarnings("resource")
    public static Rectangle rect(float x, float y, float w, float h) {
        return new Rectangle().x(x).y(y).width(w).height(h); // same for ts
    }
}