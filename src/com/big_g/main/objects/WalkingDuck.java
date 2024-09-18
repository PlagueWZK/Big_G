package com.big_g.main.objects;

/**
 * @author PlagueWZK
 * description: WalkingDuck
 * date: 2024/9/18 23:18
 */

public class WalkingDuck extends Enemy{
    public WalkingDuck(double x, double y) {
        super("Walking Duck", x, y, 40, 8000000, 1000, 10, 1000);
    }

    public WalkingDuck(String name, double x, double y, int radius, long moveFactor, double maxHealth, double attackValue, long attackFactor) {
        super(name, x, y, radius, moveFactor, maxHealth, attackValue, attackFactor);
    }
}
