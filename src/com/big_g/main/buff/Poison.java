package com.big_g.main.buff;

import com.big_g.main.objects.G;

import javax.swing.*;

/**
 * @author PlagueWZK
 * description: Poison
 * date: 2024/9/16 11:39
 */

public class Poison extends Buff{
    Timer timer;

    public Poison(long duration, double power) {
        super("POISON","中毒 * " + power , "生命值每秒减少总生命值的"+ power * 2 +"%", duration, power);
    }

    @Override
    public void giveBuff(G g) {
        timer = new Timer(1000, e -> {
            double h = g.health - g.maxHealth * 0.02 * power;
            if (h < 1) {
                g.health = 1;
            } else {
                g.health = h;
            }
        });
        timer.start();
    }

    @Override
    public void finish(G g) {
        timer.stop();
        timer = null;
    }
}
