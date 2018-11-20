package textureFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Medieval2Texture extends Texture {

    File fileToPath;
    Image image;

    public Medieval2Texture(String path) throws IOException{
        fileToPath = new File(path);
        image = ImageIO.read(fileToPath);
    }

    public Texture getTexture(){return this;}
}
