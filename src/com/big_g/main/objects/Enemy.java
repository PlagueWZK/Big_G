package com.big_g.main.objects;

import com.big_g.main.Main;
import com.big_g.main.UI.Info;
import com.big_g.main.clock.MilliTimerClock;
import com.big_g.main.clock.NanoTimerClock;
import com.big_g.main.element.WallLine;
import com.big_g.main.g_util.FontUtil;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.static_value.ImageData;

import java.awt.*;
import java.util.HashSet;


/**
 * @author PlagueWZK
 * description: Enemy
 * date: 2024/9/17 16:41
 */

public class Enemy implements Element, Interoperable {
    public static HashSet<Enemy> enemies = new HashSet<>();
    public static final Color C = new Color(202, 109, 109, 255);

    public String name;
    public double x;
    public double y;
    public double moveX;
    public double moveY;
    public int showX;
    public int showY;
    public int size;
    public int radius;
    public int seekDistance;
    public long moveFactor;
    public boolean aLive;
    public boolean isActive;
    public double health;
    public double maxHealth;
    public double attackValue;
    public long attackFactor;
    public G target;
    public int level = 1;


    public NanoTimerClock moveClock;
    public MilliTimerClock updateClock;
    public MilliTimerClock attackClock;
    public Info info;



    public Enemy(String name, double x, double y, int radius, long moveFactor,double maxHealth, double attackValue,long attackFactor) {
        this.name = name;
        this.x = this.moveX = x;
        this.y = this.moveY = y;
        this.showX = PosUtil.getScreenX(x);
        this.showY = PosUtil.getScreenY(y);
        this.radius = this.size = radius;
        this.seekDistance = radius * 10;
        this.moveFactor = moveFactor;
        this.aLive = true;
        this.isActive = false;
        this.maxHealth = this.health = maxHealth;
        this.attackValue = attackValue;
        this.target = null;

        this.moveClock = new NanoTimerClock(moveFactor);
        this.updateClock = new MilliTimerClock(10);
        this.attackClock = new MilliTimerClock(attackFactor);

        enemies.add(this);
        Main.elements.add(this);
        Main.interoperableSets.add(this);
        
        info = new Info() {
            @Override
            public void paint(Graphics2D g) {
                g.setColor(C);
                g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.fillRect(5,Main.height * 13 / 15 , Enemy.this.name.length() * 15, 70);
                if (level <= 1) {
                    g.setColor(Color.white);
                } else if (level == 2) {
                    g.setColor(Color.yellow);
                } else if (level == 3) {
                    g.setColor(Color.magenta);
                } else if (level == 4) {
                    g.setColor(Color.orange);
                } else {
                    g.setColor(Color.red);
                }
                g.drawRect(5,Main.height * 13 / 15 , Enemy.this.name.length() * 15, 70);
                g.setFont(FontUtil.getFont(20));
                g.drawString(name, 10, Main.height * 10 / 11);
                g.setColor(Color.cyan);
                g.setFont(FontUtil.getFont(16));
                g.drawString("LEVEL: "+ level, 12, Main.height * 17 / 18);
                g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.setColor(Color.RED);
                g.fillRect(Main.width * 2 / 5, Main.height * 19 / 20, (int) ((Main.width / 5) * health / maxHealth), 10);
                g.setColor(Color.BLACK);
                g.drawRect(Main.width * 2 / 5, Main.height * 19 / 20,Main.width / 5, 10);
            }
        };
    }

    @Override
    public void paint(Graphics2D g) {

        g.drawImage(ImageData.walkingDick, showX - radius, showY - radius, radius * 2, radius * 2, null);

        if (Main.DEVELOPMENT_MODE) {
            g.setColor(Color.RED);
            g.drawOval(PosUtil.getScreenX(this.x) - radius, PosUtil.getScreenY(this.y) - radius, radius * 2, radius * 2);
        }
    }

    @Override
    public void update() {
        if (aLive) {
            if (moveClock.isReady()) {
                if (isActive && PosUtil.getDistance(this.x, this.y, Main.G.x, Main.G.y) > Math.max(radius, target.radius)) {
                    double dx = PosUtil.getDx(this.x, this.y, target.x, target.y);
                    double dy = PosUtil.getDy(this.x, this.y, target.x, target.y);
                    if (!WallLine.wallLines.isEmpty()) {
                        for (WallLine l : WallLine.wallLines) {
                            if (l.isCollision(this.x + dx, this.y + dy, this.radius)) return;
                        }
                    }
                    this.x += dx;
                    this.y += dy;
                }
            }
            if  (updateClock.isReady()) {
                this.moveX += (this.x - this.moveX) * 0.2;
                this.moveY += (this.y - this.moveY) * 0.2;
                this.showX = PosUtil.getScreenX(this.moveX);
                this.showY = PosUtil.getScreenY(this.moveY);
                aLive = !(this.health <= 0);
                if (!aLive) {
                    info.isShowing = false;
                    enemies.remove(this);
                }
            }
        }
    }

    @Override
    public boolean isTriggered(double x, double y, int r) {
        return PosUtil.getDistance(this.x, this.y, x, y) < this.seekDistance;
    }

    @Override
    public void interaction(G G) {
        this.isActive = true;
        this.target = G;
        if (PosUtil.getDistance(this.x, this.y, G.x, G.y) < this.radius + G.radius) {
            if (attackClock.isReady()) {
                G.health -= this.attackValue;
            }
        }
    }

    @Override
    public void disInteraction(G G) {
        this.isActive = false;
    }

    @Override
    public boolean needDel() {
        return !aLive;
    }

    @Override
    public boolean isChecked(int x, int y) {
        return PosUtil.getDistance(this.showX, this.showY, x, y) < this.radius * 2.0 / 3.0;
    }

    @Override
    public void active() {
        if (!info.isShowing && aLive) {
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
    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
}
