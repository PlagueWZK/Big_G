package com.big_g.main.element;

import com.big_g.main.Main;
import com.big_g.main.clock.MilliTimerClock;
import com.big_g.main.clock.NanoTimerClock;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Bullet
 * date: 2024/9/16 18:39
 */

public class Bullet implements Element {

    public double x;
    public double y;
    public double moveX;
    public double moveY;
    public int showX;
    public int showY;
    public int r;

    public double dx;
    public double dy;
    public long speed;

    public int damage;

    public float show;

    public boolean alive;

    public NanoTimerClock bulletMoveClock;
    public MilliTimerClock updateClock;
    public Color color;


    public Bullet(double x, double y, double dx, double dy, int r, long speed, int damage) {
        this.x = this.moveX = x;
        this.y = this.moveY = y;
        this.showX = PosUtil.getScreenX(x);
        this.showY = PosUtil.getScreenY(y);
        this.r = r;
        this.speed = speed;
        this.damage = damage;
        this.color = new Color((int) Math.round(damage * 2.5), 0, 0, 255);
        double distance = PosUtil.getDistance(showX, showY, dx, dy);
        this.dx = (dx - showX) / distance;
        this.dy = (dy - showY) / distance;
        System.out.println("showX: " + showX + "   showY: " + showY);
        System.out.println("dx: " + this.dx + "  dy:" + this.dy);
        this.show = 1f;
        this.bulletMoveClock = new NanoTimerClock(speed);
        this.updateClock = new MilliTimerClock(10);
        this.alive = true;

        Main.elements.add(this);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(showX - r, showY - r, r * 2, r * 2);

        if (Main.DEVELOPMENT_MODE) {
//            g.drawString("showX: " + this.showX + "   showY: " + this.showY, (int) showX, (int) (showY - 20));
//            g.drawString("x: " + this.x + "   y: " + this.y, (int) showX, (int) (showY - 30));
//            g.drawString("dx: " + this.dx + "   dy: " + this.dy, (int) showX, (int) (showY - 40));
//            g.drawString("damage: " + this.damage, (int) showX, (int) (showY - 50));
//            g.drawString("show: " + this.show, (int) showX, (int) (showY - 60));

        }
    }

    @Override
    public void update() {
        if (alive) {
            if (bulletMoveClock.isReady()) {
                x += dx;
                y += dy;
                show *= 0.9f;

            }
            if (updateClock.isReady()) {
                moveX += (this.x - moveX) * 0.02;
                moveY += (this.y - moveY) * 0.02;
                showX = PosUtil.getScreenX(moveX);
                showY = PosUtil.getScreenY(moveY);
                dx *= 0.97;
                dy *= 0.97;
            }
        }
    }

    @Override
    public boolean needDel() {
        return Math.abs(dx * 1000000000 / speed) <= 2 && Math.abs(dy * 1000000000 / speed) <= 2;
    }
}



