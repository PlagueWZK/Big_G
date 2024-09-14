package com.big_g.main.clock;

/**
 * @author PlagueWZK
 * description: milliTimerClock
 * date: 2024/9/13 19:13
 */

public class MilliTimerClock extends Clock{

    public MilliTimerClock(long cooldown) {
        super(cooldown);
        this.lastTime = 0;
    }
    public MilliTimerClock(long cooldown,long lastTime) {
        super(cooldown,lastTime);
    }

    public boolean isReady() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= cooldown) {
            lastTime = currentTime;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean check() {
        return System.currentTimeMillis() - lastTime >= cooldown;
    }
}
