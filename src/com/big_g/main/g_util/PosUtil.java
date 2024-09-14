package com.big_g.main.g_util;

import com.big_g.main.Main;

/**
 * @author PlagueWZK
 * description: PosUtil
 * date: 2024/9/13 21:22
 */

public abstract class PosUtil {
    public static int getScreenX(double x) {
        return (int)(x - Main.G.viewX);
    }
    public static int getScreenY(double y) {
        return (int)(y - Main.G.viewY);
    }
    public static double getDistance(double x1,double y1,double x2,double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
