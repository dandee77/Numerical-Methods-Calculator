package src;

import java.util.LinkedList;
import java.util.Queue;
import static com.raylib.Raylib.*;

public class ResourceManager {

    private static String[] textureFilePaths = {
        "assets/board.png",
        "assets/cursor.png",
        "assets/white.png",
        "assets/black.png",
        "assets/white.png",
        "assets/black.png",
        "assets/white.png",
        "assets/black.png",
        "assets/white.png",
        "assets/black.png"
    };

    private static String[] fontsFilePaths = {
        "assets/sound1.wav",
        "assets/sound2.wav",
        "assets/sound3.wav",
        "assets/sound4.wav",
        "assets/sound5.wav"
    };
    private static Queue<Texture> texture = new LinkedList<>();
    private static Queue<Font> fonts = new LinkedList<>();

    public void loadResources() {
        for (String file : textureFilePaths) 
            texture.add(LoadTexture(file));

        for (String file : fontsFilePaths) {
            java.nio.IntBuffer fontChars = null;  // java bruh
            fonts.add(LoadFontEx(file, 126, fontChars, 0));
        }
    }

    public Queue<Texture> getTextures() {
        return texture;
    }

    public void unloadResources() {
        for (Texture tex : texture) 
            UnloadTexture(tex);

        for (Font font : fonts) 
            UnloadFont(font);
    }
}
