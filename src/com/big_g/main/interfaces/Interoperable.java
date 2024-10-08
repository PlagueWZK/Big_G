package com.big_g.main.interfaces;

import com.big_g.main.objects.G;

/**
 * @author PlagueWZK
 * description: Cinteroperable
 * date: 2024/9/14 20:45
 */

public interface Interoperable {

    boolean isTriggered(double x,double y,int r);
    void interaction(G G);
    void disInteraction(G G);
    boolean needDel();

    boolean isChecked(int x, int y);
    void active();
    void disActive();
}
