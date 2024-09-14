package com.big_g.main.clock;

/**
 * @author PlagueWZK
 * description: Clock
 * date: 2024/9/13 21:33
 */

public abstract class Clock {
    public long cooldown;
    public long lastTime;
    public Clock(long cooldown) {
        this.cooldown = cooldown;
    }
    public Clock(long cooldown, long lastTime) {
        this(cooldown);
        this.lastTime = lastTime;
    }
    public abstract boolean isReady();
    public abstract boolean check();
    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}
