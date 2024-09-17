package com.big_g.main.UI;

import com.big_g.main.Main;
import com.big_g.main.g_util.FontUtil;
import com.big_g.main.static_value.ImageData;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: StateUI
 * date: 2024/9/15 13:35
 */

public class StateUI extends UI {
    @Override
    public void paint(Graphics2D g) {
        g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(new Color((int) (200 - (200 * Main.G.showHealth / Main.G.maxHealth)), (int) (255 * Main.G.showHealth / Main.G.maxHealth), (int) (255 * Main.G.health / Main.G.maxHealth)));
        g.fillRect(10, 30, 200, 50);
        g.setColor(Color.black);
        g.drawRect(10, 30, 200, 50);
        g.drawLine(60, 30, 60, 80);
        g.drawLine(60, 55, 210, 55);
        g.drawImage(ImageData.G_ico, 12, 32, 45, 45, null);
        g.setColor(Color.red);
        g.setFont(FontUtil.getFont(12));
        g.drawString("HP:" + "\t" + (int) Main.G.health + "/" + (int) Main.G.maxHealth, 100, 52);
        g.fillRect(85, 35, (int) (100 * Main.G.showHealth / Main.G.maxHealth), 5);
        g.setColor(Color.black);
        g.drawRect(85, 35, 100, 5);
    }
}
