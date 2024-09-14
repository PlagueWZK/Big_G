package com.big_g.main.buff;

import com.big_g.main.objects.G;

/**
 * @author PlagueWZK
 * description: Giantness
 * date: 2024/9/14 21:42
 */

public class Grow extends Buff {


    public Grow(long duration, double power) {
        super("巨人化", "可使身体变大", duration, power);
    }

    @Override
    public void giveBuff(G g) {
        g.radius = (int) (g.radius * power);
    }

    @Override
    public void finish(G g) {
        g.radius = (int) (g.radius / power);
    }
}
