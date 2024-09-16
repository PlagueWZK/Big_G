package com.big_g.main.UI;

import com.big_g.main.interfaces.InformationUI;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Info
 * date: 2024/9/16 15:19
 */

public class Info implements InformationUI {
    public boolean isShowing = false;
    @Override
    public void paint(Graphics2D g) {

    }

    @Override
    public boolean needDel() {
        return !isShowing;
    }
}
