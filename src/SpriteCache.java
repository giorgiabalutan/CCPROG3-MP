import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SpriteCache {
    private static final HashMap<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage getImage(String filepath)
    {
        if(cache.containsKey(filepath))
        {
            return cache.get(filepath);
        }

        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            try {
                sprite = ImageIO.read(new File("assets/itemSprites/None.png"));
            } catch (IOException f) {
                // f.printStackTrace();
                System.out.println(filepath);
            }
            // e.printStackTrace();
            System.out.println(filepath);
        }
        cache.put(filepath, sprite);
        return sprite;
    }
}
