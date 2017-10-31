package org.dreambot.articron.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class SpriteLoader {

    private static Map<String,BufferedImage> imageCache;

    static {
        imageCache = new HashMap<>();
    }

    public static BufferedImage readFromToken(String token) {
        return imageCache.get(token);
    }

    public static BufferedImage readFromURL(String token, String... url) {
        if (!imageCache.containsKey(token)) {
            try {
                imageCache.put(token, ImageIO.read(new URL(url[0])));
            } catch (IOException e) {
                System.out.println("error reading image at URL: " + url[0]);
                return null;
            }
        }
        return readFromToken(token);
    }
}
