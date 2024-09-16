package com.big_g.main.interfaces;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: InformationUI
 * date: 2024/9/16 14:39
 */

public interface InformationUI extends UI{
    @Override
    void paint(Graphics2D g);

    @Override
    boolean needDel();
}
