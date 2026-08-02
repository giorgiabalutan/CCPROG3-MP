import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SpriteCache {
    /**
     * HashMap Relating a list of String Filepaths to a BufferedImage object.
     * Static so SpriteCache can be called from anywhere.
     */
    private static final HashMap<String, BufferedImage> cache = new HashMap<>();

    /**
     * Gets an image using a filepath.
     * If an image has been loaded from this previously, it refers to the hashmap.
     * Otherwise, it adds the new filepath - image relationship to the hashmep.
     * 
     * @param filepath directory where the image is located.
     * @return the BufferedImage with the sprite needed.
     */
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
