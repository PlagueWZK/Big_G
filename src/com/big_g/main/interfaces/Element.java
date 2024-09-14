package com.big_g.main.interfaces;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Element
 * date: 2024/9/14 17:01
 */

public interface Element {

    void paint(Graphics2D g);
    boolean isCollision(double x,double y,int r);
    boolean needDel();
}
