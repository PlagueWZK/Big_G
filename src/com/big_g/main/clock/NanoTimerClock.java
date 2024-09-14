package com.big_g.main.clock;

/**
 * @author PlagueWZK
 * description: NonoTimerClock
 * date: 2024/9/13 21:09
 */

public class NanoTimerClock extends Clock{

    public boolean isReady() {
        long currentTime = System.nanoTime();
        if (currentTime - lastTime >= cooldown) {
            lastTime = currentTime;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean check() {
        return System.nanoTime() - lastTime >= cooldown;
    }

    public NanoTimerClock(long cooldown,long lastTime) {
        super(cooldown, lastTime);
    }
    public NanoTimerClock(long cooldown) {
        super(cooldown);
    }
}
