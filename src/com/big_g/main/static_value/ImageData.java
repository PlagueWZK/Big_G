package com.big_g.main.static_value;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 * @author PlagueWZK
 * description: ImageData
 * date: 2024/9/4 00:49
 */

public class ImageData {
    public static String parentPath;

    public static URL Path;


    public static ArrayList<BufferedImage> GImages = new ArrayList<>();
    public static ArrayList<BufferedImage> attackImages = new ArrayList<>();


    public static void init() {
        parentPath = "/images/";
        try {
            for (int i = 1; i <= 6; i++) {
                Path = ImageData.class.getResource(parentPath + "G/G" + i + ".png");
                if (Path != null) GImages.add(ImageIO.read(Path));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
