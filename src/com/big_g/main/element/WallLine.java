package com.big_g.main.element;

import com.big_g.main.Main;
import com.big_g.main.g_util.PosUtil;
import com.big_g.main.interfaces.Element;

import java.awt.*;
import java.util.HashSet;

/*
 * @author PlagueWZK
 * description: WallLine
 * date: 2024/9/14 13:58
 */

public class WallLine implements Element {
    public static HashSet<WallLine> wallLines = new HashSet<>();

    public double x1;
    public double y1;
    public double x2;
    public double y2;
    public double length;
    public double angle;
    public double A;
    public double B;
    public double C;
    public Color color;
    public Stroke stroke;

    public WallLine(double x1, double y1, double x2, double y2) {
        this(x1, y1, x2, y2, Color.BLACK);
    }

    public WallLine(double x1, double y1, double x2, double y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        A = y2 - y1;
        B = -(x2 - x1);
        C = x2 * y1 - x1 * y2;

        length = PosUtil.getDistance(x1, y1, x2, y2);
        wallLines.add(this);
        Main.elements.add(this);
        this.color = color;
    }

    public double getDistance(double x, double y) {
        return Math.abs(A * x + B * y + C) / Math.sqrt(A * A + B * B);
    }

    public boolean isCollision(double x, double y, int r) {
        return PosUtil.getDistance(x, y, x1, y1) < length + r + 2 && PosUtil.getDistance(x, y, x2, y2) < length + r + 2 && getDistance(x, y) < r + 2;

    }

    @Override
    public boolean needDel() {
        return false;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(this.color);
        g.setStroke(new BasicStroke(5));
        g.drawLine(PosUtil.getScreenX(x1), PosUtil.getScreenY(y1), PosUtil.getScreenX(x2), PosUtil.getScreenY(y2));

    }

    public static void init() {
        int[][] wall = new int[][]{
                {200, 200, 800, 200},
                {200, 200, 200, 700},
                {200, 700, 800, 700},
                {800, 700, 800, 500},
                {800, 200, 800, 400}
        };
        for (int[] w : wall) {
            new WallLine(w[0], w[1], w[2], w[3]);
        }
        new WallLine(200, 200, 200, -100, Color.RED);
    }
}
