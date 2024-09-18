package com.big_g.main.objects;

import com.big_g.main.Main;
import com.big_g.main.UI.StateUI;
import com.big_g.main.buff.Buff;
import com.big_g.main.clock.MilliTimerClock;
import com.big_g.main.clock.NanoTimerClock;
import com.big_g.main.element.Bullet;
import com.big_g.main.element.WallLine;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.interfaces.UI;
import com.big_g.main.static_value.ImageData;

import java.awt.*;
import java.awt.event.*;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

/**
 * @author PlagueWZK
 * description: G
 * date: 2024/9/3 19:26
 */

public class G {

    public static final int STANDARD_MOVE_FACTOR = 5000000;
    public static final int STANDARD_RADIUS = 30;
    public static final double STANDARD_HEALTH = 100.0;
    public static final double STANDARD_NATURAL_REGENERATION = 2.0;
    public static final int STANDARD_ATTACK_FACTOR = 10;
    public static final double STANDARD_ATTACK_VALUE = 1.0;
    public static final int STANDARD_DIFFUSION_RADIUS = 10;

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
    public double maxHealth;
    public double showHealth;
    public double naturalRegeneration;
    public double health;
    public int attackFactor;
    public int radius;
    public int size;
    public long moveFactor;
    public boolean angry;
    public double attackValue;
    public int diffusionRadius;

    public NanoTimerClock moveClock;
    public MilliTimerClock flashStateClock;
    public MilliTimerClock updateClock;
    public MilliTimerClock naturalRegenerationClock;
    public MilliTimerClock attackClock;
    public HashSet<Integer> keySets;
    public HashSet<Integer> mouseSets;
    public HashSet<Buff> buffs;
    public HashSet<UI> UISets;
    public HashSet<Bullet> bullets;

    public KeyListener keyListener;
    public MouseListener mouseListener;
    public MouseMotionListener mouseMotionListener;

    public G(String name) {
        this.name = name;
        this.x = this.y = Main.Bound / 2.0;
        this.screenX = this.screenY = 0.0;
        this.viewX = this.viewY = 0.0;
        this.maxHealth = this.health = this.showHealth = STANDARD_HEALTH;
        this.naturalRegeneration = STANDARD_NATURAL_REGENERATION;
        this.attackFactor = STANDARD_ATTACK_FACTOR;
        this.radius = STANDARD_RADIUS;
        this.size = radius;
        this.angry = false;
        this.attackValue = STANDARD_ATTACK_VALUE;
        this.moveFactor = STANDARD_MOVE_FACTOR;
        this.diffusionRadius = STANDARD_DIFFUSION_RADIUS;
        this.moveClock = new NanoTimerClock(moveFactor);
        this.naturalRegenerationClock = new MilliTimerClock(1000);
        this.flashStateClock = new MilliTimerClock(moveFactor / 1000000 * 20);
        this.updateClock = new MilliTimerClock(10);
        this.attackClock = new MilliTimerClock(attackFactor);
        this.keySets = new HashSet<>();
        this.mouseSets = new HashSet<>();
        this.buffs = new HashSet<>();
        this.bullets = new HashSet<>();
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
                G.this.mouseX = e.getX() * 8 / 10 - 5;
                G.this.mouseY = (e.getY() - Main.frameInsets.top) * 8 / 10 - 1;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX() * 8 / 10 - 5;
                mouseY = (e.getY() - Main.frameInsets.top) * 8 / 10 - 1;
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

        this.UISets = new HashSet<>();
        UISets.add(new StateUI());
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

            attackClock.setCooldown(attackFactor);
            if (this.mouseSets.contains(MouseEvent.BUTTON1)) {
                if (attackClock.isReady()) {
                    for (int i = 0; i < 3; i++) {
                        bullets.add(new Bullet(this.x, this.y, mouseX, mouseY, this.radius / 3, moveFactor / 10, attackValue,diffusionRadius));
                    }
                }
            }
            if (naturalRegenerationClock.isReady()) {
                if (health < maxHealth) {
                    health += naturalRegeneration;
                    if (health > maxHealth) {
                        health = maxHealth;
                    }
                }
            }
            showHealth += (health - showHealth) * 0.05;
            if (!Main.interoperableSets.isEmpty()) {
                for (Interoperable i : Main.interoperableSets) {
                    if (i.isTriggered(this.x, this.y, this.radius)) {
                        i.interaction(this);
                    } else {
                        i.disInteraction(this);
                    }
                    if (i.isChecked(this.mouseX, this.mouseY)) {
                        i.active();
                    } else {
                        i.disActive();
                    }
                }
            }
            if (!buffs.isEmpty()) {
                for (Buff b : buffs) {
                    if (!b.isActive) {
                        b.clock = new MilliTimerClock(b.duration, System.currentTimeMillis());
                        b.isActive = true;
                        b.giveBuff(this);
                    } else if (b.isOver()) {
                        b.finish(this);
                    }
                }
                buffs.removeIf(Buff::isOver);
            }
            UISets.removeIf(UI::needDel);
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
        if (angry) {
            if (state >= 11) state = 6;
            else state++;
        } else {
            if (state >= 5) state = 0;
            else state++;
        }
    }

    public void paint(Graphics2D g) {

        if (ImageData.GImages.isEmpty()) return;
        g.drawImage(ImageData.GImages.get(state), (int) (this.screenX - size), (int) (this.screenY - size), size * 2, size * 2, null);

        for (UI u : UISets) {
            try {
                u.paint(g);
            } catch (ConcurrentModificationException ignored) {

            }

            if (Main.DEVELOPMENT_MODE) {
                g.setColor(Color.BLUE);
                g.drawOval((int) (this.screenX - radius), (int) (this.screenY - radius), radius * 2, radius * 2);
            }
        }
    }
}