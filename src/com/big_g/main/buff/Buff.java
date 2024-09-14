package com.big_g.main.buff;

import com.big_g.main.clock.MilliTimerClock;
import com.big_g.main.objects.G;

/**
 * @author PlagueWZK
 * description: Buff
 * date: 2024/9/14 17:09
 */

public abstract class Buff {
    public static final int SPEED_UP = 1;

    public String name;
    public String description;
    public long duration;
    public double power;
    public boolean isActive;
    public MilliTimerClock clock;

    public Buff(String name, String description, long duration,double power) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.power = power;
        this.isActive = false;
    }

    public abstract void giveBuff(G g);
    public abstract void finish(G g);
    public boolean isOver() {
        return clock.check();
    }
}
