package com.big_g.main.g_util;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: FontUtil
 * date: 2024/9/13 21:21
 */

public abstract class FontUtil {
    public static Font getFont(int size) {
        return new Font("宋体", Font.PLAIN, size);
    }
}
