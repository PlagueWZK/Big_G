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

    public double damage;


    public boolean alive;

    public NanoTimerClock bulletMoveClock;
    public MilliTimerClock updateClock;
    public Color color;


    public Bullet(double x, double y, double dx, double dy, int r, long speed, double damage,int offset) {
        this.x = this.moveX = x;
        this.y = this.moveY = y;
        this.showX = PosUtil.getScreenX(x);
        this.showY = PosUtil.getScreenY(y);
        this.r = r;
        this.speed = speed;
        this.damage = damage;
        this.color = new Color((int)(damage * 2.5), (int)(255 - damage * 2.5), 0, 255);
        double distance = PosUtil.getDistance(showX, showY, dx, dy);

        dx = (dx - showX) / distance;
        dy = (dy - showY) / distance;
        double radians = Math.toRadians(Main.random.nextDouble(-offset,offset)) ;
        this.dx = dx * Math.cos(radians) - dy * Math.sin(radians);
        this.dy = dy * Math.cos(radians) + dx * Math.sin(radians);
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

            }
            if (updateClock.isReady()) {
                moveX += (this.x - moveX) * 0.02;
                moveY += (this.y - moveY) * 0.02;
                showX = PosUtil.getScreenX(moveX);
                showY = PosUtil.getScreenY(moveY);
                dx *= 0.97;
                dy *= 0.97;
                damage *= 0.987;
                this.color = new Color((int)(damage * 2.5), (int)(255 - damage * 2.5), 0, (int)(Math.max(200 * Math.abs(dx), 200 * Math.abs(dy))));
            }
        }
    }

    @Override
    public boolean needDel() {
        if (Math.abs(dx * 1000000000 / speed) <= 2 && Math.abs(dy * 1000000000 / speed) <= 2) {
            alive = false;
            return true;
        }
        return false;
    }
}



