package com.big_g.main.buff;

import com.big_g.main.objects.G;

/**
 * @author PlagueWZK
 * description: Angry
 * date: 2024/9/15 00:35
 */

public class Angry extends Buff{
    public Angry(long duration, double power) {
        super("ANGRY","愤怒 *" + power, "增加攻击力,攻击力 *" + power, duration, power);
    }


    @Override
    public void giveBuff(G g) {
        g.angry = true;
        g.attackValue *= power;
    }

    @Override
    public void finish(G g) {
        g.angry = false;
        g.attackValue /= power;
    }
}
