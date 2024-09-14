package com.big_g.main.objects;

import com.big_g.main.Main;
import com.big_g.main.buff.Buff;
import com.big_g.main.clock.MilliTimerClock;
import com.big_g.main.clock.NanoTimerClock;
import com.big_g.main.element.WallLine;
import com.big_g.main.g_util.FontUtil;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.static_value.ImageData;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

/**
 * @author PlagueWZK
 * description: G
 * date: 2024/9/3 19:26
 */

public class G {

    public static final int STANDARD_MOVE_FACTOR = 5000000;
    public static final int STANDARD_RADIUS = 20;

    public String name;
    public double x;
    public double y;
    public double screenX;
    public double screenY;
    public double viewX;
    public double viewY;
    public int mouseX;
    public int mouseY;
    public int state;
    public double health;
    public int radius;
    public int size;
    public long moveFactor;

    public NanoTimerClock moveClock;
    public MilliTimerClock flashStateClock;
    public MilliTimerClock updateClock;
    public HashSet<Integer> keySets;
    public HashSet<Integer> mouseSets;
    public HashSet<Buff> buffs;

    public KeyListener keyListener;
    public MouseListener mouseListener;
    public MouseMotionListener mouseMotionListener;

    public G(String name) {
        this.name = name;
        this.x = this.y = Main.Bound / 2.0;
        this.screenX = this.screenY = 0.0;
        this.viewX = this.viewY = 0.0;
        this.radius = STANDARD_RADIUS;
        this.size = radius;
        this.moveFactor = STANDARD_MOVE_FACTOR;
        this.moveClock = new NanoTimerClock(moveFactor);
        this.flashStateClock = new MilliTimerClock(moveFactor / 1000000 * 20);
        this.updateClock = new MilliTimerClock(10);
        this.keySets = new HashSet<>();
        this.mouseSets = new HashSet<>();
        this.buffs = new HashSet<>();
        this.keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                keySets.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keySets.remove(e.getKeyCode());
            }
        };
        this.mouseMotionListener = new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                G.this.mouseX = e.getX() * 8 / 10;
                G.this.mouseY = (e.getY() - Main.frameInsets.top)  * 8 / 10;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX() * 8 / 10;
                mouseY = (e.getY() - Main.frameInsets.top) * 8 / 10;
            }
        };
        this.mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseSets.add(e.getButton());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseSets.remove(e.getButton());
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        Main.frame.addKeyListener(keyListener);
        Main.frame.addMouseListener(mouseListener);
        Main.frame.addMouseMotionListener(mouseMotionListener);
    }

    public void update() {

        if (moveClock.isReady()) {
            this.moveClock.setCooldown(moveFactor);
            this.flashStateClock.setCooldown(moveFactor / 1000000 * 20);

            if (keySets.contains(KeyEvent.VK_W)) {
                move(0, -1);
            }
            if (keySets.contains(KeyEvent.VK_S)) {
                move(0, 1);
            }
            if (keySets.contains(KeyEvent.VK_A)) {
                move(-1, 0);
            }
            if (keySets.contains(KeyEvent.VK_D)) {
                move(1, 0);
            }
            this.viewX += ((this.x - Main.width / 2.0) - this.viewX) * 0.008;
            this.viewY += ((this.y - Main.height / 2.0) - this.viewY) * 0.008;
            this.screenX += ((this.x - this.viewX) - this.screenX) * 0.5;
            this.screenY += ((this.y - this.viewY) - this.screenY) * 0.8;
        }
        if (updateClock.isReady()) {
            if (radius > size) {
                this.size = (int) Math.min(radius, size + 0.01 * radius);
            } else {
                this.size = (int) Math.max(radius, size - 0.01 * radius);
            }
            if (!Main.interoperableSets.isEmpty()) {
                for (Interoperable i : Main.interoperableSets) {
                    if  (i.isTriggered(this.x, this.y, this.radius)) {
                        i.interaction(this);
                    }
                }
            }
            if (!buffs.isEmpty()) {
                for (Buff b : buffs) {
                    if (!b.isActive) {
                        b.clock = new MilliTimerClock(b.duration, System.currentTimeMillis());
                        b.isActive = true;
                        b.giveBuff(this);
                        if (Main.DEVELOPMENT_MODE) {
                            System.out.println("Buff " + b.name + " is triggered");
                        }
                    } else if (b.isOver()) {
                        b.finish(this);
                        if (Main.DEVELOPMENT_MODE) {
                            System.out.println("Buff " + b.name + " is over");
                        }
                    }
                }
                buffs.removeIf(Buff::isOver);
            }
        }

    }

    public void move(int dx, int dy) {
        if (!WallLine.wallLines.isEmpty()) {
            for (WallLine l : WallLine.wallLines) {
                if (l.isCollision(this.x + dx, this.y + dy, this.radius)) return;
            }
        }
        this.x += dx;
        this.y += dy;
        if (flashStateClock.isReady()) {
            flashState();
        }
    }

    public void flashState() {
        if (state == 5) state = 0;
        else state++;
    }

    public void paint(Graphics2D g) {
        if (ImageData.GImages.isEmpty()) return;
        g.drawImage(ImageData.GImages.get(state), (int) (this.screenX - size), (int) (this.screenY - size), size * 2, size * 2, null);

        if (Main.DEVELOPMENT_MODE) {
            g.setColor(Color.GREEN);
            g.fillRect(PosUtil.getScreenX(100), PosUtil.getScreenY(100), 60, 30);

            g.setColor(Color.blue);
            g.drawOval((int) (Math.round(this.screenX) - radius), (int) (Math.round(this.screenY) - radius), radius * 2, radius * 2);

            g.setFont(FontUtil.getFont(20));
            g.setColor(Color.red);
            g.drawString("x:" + this.x + " y:" + this.y, 10, 30);
            g.drawString("viewX:" + this.viewX + " viewY:" + this.viewY, 10, 50);
            g.drawString("screenX:" + this.screenX + " screenY:" + this.screenY, 10, 70);
            g.drawString("moveFactor:" + this.moveFactor, 10, 90);
            g.drawString("size:" + this.size + " radius:" + this.radius, 10, 110);

            g.fillOval(this.mouseX - 5, this.mouseY - 5,10,10);
        }
    }

    public String getName() {
        return name;
    }

    public int getRadius() {
        return radius;
    }
}
