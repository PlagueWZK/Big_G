package com.big_g.main.buff;

import com.big_g.main.objects.G;

/**
 * @author PlagueWZK
 * description: SpeedUp
 * date: 2024/9/14 17:18
 */

public class SpeedUp extends Buff {
    public SpeedUp(long duration, double power) {
        super("速度提升", "获得速度提升效果,速度 * "+ power, duration, power);
    }
    @Override
    public void giveBuff(G g) {
        g.moveFactor = (long) (g.moveFactor / power);
    }

    @Override
    public void finish(G g) {
        g.moveFactor = (long) (g.moveFactor * power);
    }
}
