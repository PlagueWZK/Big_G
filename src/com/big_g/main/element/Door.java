package com.big_g.main.element;

import com.big_g.main.Main;
import com.big_g.main.clock.MilliTimerClock;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.objects.G;

import java.awt.*;
import java.util.HashSet;

/**
 * @author PlagueWZK
 * description: Door
 * date: 2024/9/17 14:18
 */

public class Door extends WallLine implements Element, Interoperable {
    public static HashSet<Door> doors = new HashSet<>();

    public double x;
    public double y;
    public Color color;
    public boolean canPass;
    public boolean isOpen;
    public int alpha;

    public MilliTimerClock updateClock;

    public Door(double x, double y, double x2, double y2) {
        super(x, y, x2, y2);
        this.color = new Color(225, 46, 46, 255);
        this.canPass = false;
        this.isOpen = false;
        this.updateClock = new MilliTimerClock(100);
        this.alpha = 255;
        Main.interoperableSets.add(this);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(5));
        g.drawLine(PosUtil.getScreenX(x1), PosUtil.getScreenY(y1), PosUtil.getScreenX(x2), PosUtil.getScreenY(y2));
    }

    @Override
    public void update() {
        if (updateClock.isReady()) {
            if (canPass) {
                color = new Color(119, 232, 97, alpha);
            } else {
                color = new Color(225, 46, 46, alpha);
            }
            if (isOpen) {
                System.out.println(alpha);
                alpha += (int) ((-alpha) * 0.5f);
            } else {
                alpha += (int) ((255 - alpha) * 0.5f);
            }
        }
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
    public void active() {

    }

    @Override
    public void disActive() {

    }

    @Override
    public boolean isCollision(double x, double y, int r) {
        return super.isCollision(x, y, r) && !canPass;
    }

    @Override
    public boolean isTriggered(double x, double y, int r) {
        return super.isCollision(x, y, r+100);
    }

    @Override
    public void interaction(G G) {

        if (canPass && !isOpen) {
            isOpen = true;
        }
    }

    @Override
    public void disInteraction(G G) {
        if (isOpen) {
            isOpen = false;
        }
    }

}
