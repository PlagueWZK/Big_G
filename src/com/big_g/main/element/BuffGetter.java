package com.big_g.main.element;

import com.big_g.main.Main;
import com.big_g.main.buff.Buff;
import com.big_g.main.buff.Grow;
import com.big_g.main.buff.SpeedUp;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.objects.G;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: BuffGetter
 * date: 2024/9/14 17:00
 */

public class BuffGetter implements Element,Interoperable {

    public double x;
    public double y;
    public Buff buff;
    private boolean deFunction;

    public BuffGetter(double x, double y,Buff buff) {
        this.x = x;
        this.y = y;
        this.buff = buff;
        this.deFunction = false;
        Main.interoperableSets.add(this);
        Main.elements.add(this);
    }


    @Override
    public void paint(Graphics2D g) {
        g.fillOval(PosUtil.getScreenX(x) - 5, PosUtil.getScreenY(y) - 5, 10,10);
    }

    @Override
    public boolean isCollision(double x, double y, int r) {
        return false;
    }

    public static void init() {
        new BuffGetter(500,600,new SpeedUp(20000,2));
        new BuffGetter(500,300,new Grow(10000,5));
    }

    @Override
    public boolean isTriggered(double x, double y, int r) {
        return PosUtil.getDistance(this.x, this.y, x, y) < r;
    }

    @Override
    public void interaction(G G) {
        G.buffs.add(buff);
        deFunction = true;
        if (Main.DEVELOPMENT_MODE) {
            System.out.println("获得buff");
        }
    }

    @Override
    public boolean needDel() {
        return deFunction;
    }
}
