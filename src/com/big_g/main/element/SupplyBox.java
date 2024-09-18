package com.big_g.main.element;

import com.big_g.main.Main;
import com.big_g.main.g_util.FontUtil;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.objects.G;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: SupplyBox
 * date: 2024/9/18 23:31
 */

public class SupplyBox implements Element, Interoperable {
    public double x;
    public double y;
    public int radius;
    private boolean deFunction;
    public static final int RADIUS = 20;
    public Element e;

    public SupplyBox(double x, double y,Element e) {
        this.x = x;
        this.y = y;
        this.radius = RADIUS;
        this.deFunction = false;
        this.e = e;

        Main.elements.add(this);
        Main.interoperableSets.add(this);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(PosUtil.getScreenX(this.x) - radius, PosUtil.getScreenY(this.y) - radius, radius * 2, radius * 2 );
        g.setFont(FontUtil.getFont(radius * 2));
        g.setColor(Color.YELLOW);
        g.drawString("?", PosUtil.getScreenX(this.x) - radius, PosUtil.getScreenY(this.y) + radius);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isTriggered(double x, double y, int r) {
        return PosUtil.getDistance(this.x, this.y, x, y) < r;
    }

    @Override
    public void interaction(G G) {
        e.setX(x);
        e.setY(y);
        deFunction = true;
    }

    @Override
    public void disInteraction(G G) {

    }

    @Override
    public boolean needDel() {
        return deFunction;
    }

    @Override
    public boolean isChecked(int x, int y) {
        return false;
    }

    @Override
    public void active() {

    }

    @Override
    public void disActive() {

    }
    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
}
