package com.big_g.main.objects;

import com.big_g.main.clock.MilliTimerClock;
import com.big_g.main.clock.NanoTimerClock;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;
import com.big_g.main.interfaces.Interoperable;

import java.awt.*;
import java.util.HashSet;

/**
 * @author PlagueWZK
 * description: Enemy
 * date: 2024/9/17 16:41
 */

public class Enemy implements Element, Interoperable {
    public static HashSet<Enemy> enemies = new HashSet<>();

    public String name;
    public double x;
    public double y;
    public double moveX;
    public double moveY;
    public int showX;
    public int showY;
    public int size;
    public int radius;
    public long moveFactor;
    public boolean aLive;

    public NanoTimerClock moveClock;
    public MilliTimerClock updateClock;



    public Enemy(String name, double x, double y, int radius, long moveFactor) {
        this.name = name;
        this.x = this.moveX = x;
        this.y = this.moveY = y;
        this.showX = PosUtil.getScreenX(x);
        this.showY = PosUtil.getScreenY(y);
        this.radius = this.size = radius;
        this.moveFactor = moveFactor;
        this.aLive = true;

        this.moveClock = new NanoTimerClock(moveFactor);
        this.updateClock = new MilliTimerClock(10);
    }

    @Override
    public void paint(Graphics2D g) {

    }

    @Override
    public void update() {
        if (aLive) {
            if (moveClock.isReady()) {

            }

            if  (updateClock.isReady()) {

            }
        }
    }

    @Override
    public boolean isTriggered(double x, double y, int r) {
        return false;
    }

    @Override
    public void interaction(G G) {

    }

    @Override
    public void disInteraction(G G) {

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
}
