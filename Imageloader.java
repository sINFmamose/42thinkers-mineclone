import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ${perdu} on ${16.06.16}.
 */
public class Imageloader {

    public static BufferedImage loadImage(String path){
        // Ein Bilderlader
        try{
            return ImageIO.read(Imageloader.class.getClassLoader().getResourceAsStream(path)); // um ein Bild zu laden
        }
        catch (IOException e){
            e.printStackTrace(); // um etwas vom stack zu returnen
        }
        return null;
    }
    public static BufferedImage scale(BufferedImage source,int width, int height){
        BufferedImage scaled = new BufferedImage(width,height,source.getType());// Scalierungsmethode
        Graphics g = scaled.getGraphics();
        g.drawImage(source,0,0, width,height,null);
        g.dispose();
        return scaled;
    }
}

