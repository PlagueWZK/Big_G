package com.big_g.main.element;

import com.big_g.main.Main;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.objects.G;

/**
 * @author PlagueWZK
 * description: BasicInteroperable
 * date: 2024/9/16 13:25
 */

public abstract class BasicInteroperable implements Interoperable {
    public double x;
    public double y;
    public int radius;

    public BasicInteroperable(double x, double y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        Main.interoperableSets.add(this);
    }
    @Override
    public boolean isTriggered(double x, double y, int r) {
        return PosUtil.getDistance(this.x, this.y, x, y) < r + radius;
    }

    @Override
    public boolean needDel() {
        return false;
    }

    @Override
    public boolean isChecked(int x, int y) {
        return false;
    }

    @Override
    public void interaction(G G) {

    }


    @Override
    public void active() {

    }
}
