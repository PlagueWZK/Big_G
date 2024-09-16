package com.big_g.main.UI;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: UI
 * date: 2024/9/15 13:31
 */

public abstract class UI implements com.big_g.main.interfaces.UI {
    public int x;
    public int y;
    public double alpha;

    @Override
    public void paint(Graphics2D g) {

    }

    @Override
    public boolean needDel() {
        return false;
    }
}
