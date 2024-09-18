package com.big_g.main.element;

import com.big_g.main.Main;
import com.big_g.main.UI.Info;
import com.big_g.main.buff.Buff;
import com.big_g.main.g_util.FontUtil;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.objects.G;
import com.big_g.main.static_value.ImageData;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: BuffGetter
 * date: 2024/9/14 17:00
 */

public class BuffGetter implements Element, Interoperable {

    public static final Color C = new Color(47, 43, 43);

    public double x;
    public double y;
    public int radius;
    public Buff buff;
    private boolean deFunction;
    public static final int RADIUS = 15;
    public Info info;

    public BuffGetter(double x, double y, Buff buff) {
        this.x = x;
        this.y = y;
        this.radius = RADIUS;
        this.buff = buff;
        this.deFunction = false;
        info = new Info() {
            @Override
            public void paint(Graphics2D g) {
                g.setColor(C);
                g.fillRect(5,Main.height * 13 / 15 , buff.description.length() * 15, 70);
                g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                if (buff.power <= 2) {
                    g.setColor(Color.white);
                } else if (buff.power <= 4) {
                    g.setColor(Color.yellow);
                } else if (buff.power <= 6) {
                    g.setColor(Color.magenta);
                } else if (buff.power <= 8) {
                    g.setColor(Color.orange);
                } else {
                    g.setColor(Color.red);
                }
                g.drawRect(5,Main.height * 13 / 15 , buff.description.length() * 15, 70);
                g.setFont(FontUtil.getFont(20));
                g.drawString(buff.name, 10, Main.height * 10 / 11);
                g.setColor(Color.cyan);
                g.setFont(FontUtil.getFont(16));
                g.drawString(buff.description, 12, Main.height * 17 / 18);

                if (Main.DEVELOPMENT_MODE) {
                    g.drawString(String.valueOf(buff.description.length()), 100, Main.height * 13 / 15);
                }
            }
        };
        Main.elements.add(this);
        Main.interoperableSets.add(this);
    }


    @Override
    public void paint(Graphics2D g) {
        g.drawImage(ImageData.buffImages.get(buff.ID),PosUtil.getScreenX(this.x - RADIUS),PosUtil.getScreenY(this.y - RADIUS), RADIUS * 2, RADIUS * 2, null);
    }

    @Override
    public void update() {

    }


    @Override
    public void interaction(G G) {
        G.buffs.add(buff);
        deFunction = true;
        info.isShowing = false;
    }

    @Override
    public void disInteraction(G G) {

    }

    @Override
    public boolean needDel() {
        return deFunction;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean isTriggered(double x, double y, int r) {
        return PosUtil.getDistance(this.x, this.y, x, y) < r;
    }

    @Override
    public boolean isChecked(int x, int y) {
        return PosUtil.getDistance(PosUtil.getScreenX(this.x), PosUtil.getScreenY(this.y), x, y) < RADIUS;
    }

    public void active() {
        if (!info.isShowing && !deFunction) {
            Main.G.UISets.add(info);
            info.isShowing = true;
        }
    }

    @Override
    public void disActive() {
        if (info.isShowing) {
            info.isShowing = false;
        }
    }
}
