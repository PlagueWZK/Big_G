package com.big_g.main.static_value;


import com.big_g.main.buff.Buff;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author PlagueWZK
 * description: ImageData
 * date: 2024/9/4 00:49
 */

public class ImageData {
    public static String parentPath;

    public static URL Path;


    public static BufferedImage G_ico;
    public static ArrayList<BufferedImage> GImages = new ArrayList<>();
    public static HashMap<String, BufferedImage> buffImages = new HashMap<>();
    public static Image walkingDick;


    public static void init() {
        parentPath = "/images/";
        try {
            for (int i = 1; i <= 6; i++) {
                Path = ImageData.class.getResource(parentPath + "G/G" + i + ".png");
                if (Path != null) GImages.add(ImageIO.read(Path));
            }
            for (int i = 1; i <= 6; i++) {
                Path = ImageData.class.getResource(parentPath + "G/G" + i + "_angry.png");
                if (Path != null) GImages.add(ImageIO.read(Path));
            }
            Path = ImageData.class.getResource(parentPath + "G/G_ico.png");
            if (Path != null) G_ico = ImageIO.read(Path);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            String[] buffs = Buff.buffs;
            for (String buff : buffs) {
                Path = ImageData.class.getResource(parentPath + "buff/" + buff + ".png");
                if (Path != null) buffImages.put(buff, ImageIO.read(Path));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Path = ImageData.class.getResource(parentPath + "walkingDuck.gif");
            if (Path != null) walkingDick = Toolkit.getDefaultToolkit().getImage(Path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
