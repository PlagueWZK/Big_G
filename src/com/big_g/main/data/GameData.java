package com.big_g.main.data;

import com.big_g.main.Main;
import com.big_g.main.buff.Angry;
import com.big_g.main.buff.SpeedUp;
import com.big_g.main.element.BuffGetter;
import com.big_g.main.element.Door;
import com.big_g.main.element.SupplyBox;
import com.big_g.main.element.WallLine;
import com.big_g.main.interfaces.Element;
import com.big_g.main.objects.WalkingDuck;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author PlagueWZK
 * description: GameData
 * date: 2024/9/17 13:55
 */

public class GameData {
    public static HashMap<Integer, HashSet<Element>> data = new HashMap<>();

    public static int level;


    public static void add(int level, Element element) {
        if (!data.containsKey(level)) {
            data.put(level, new HashSet<>());
        }
        data.get(level).add(element);
    }

    public static void add(int level, Element[] element) {
        if (!data.containsKey(level)) {
            data.put(level, new HashSet<>());
        }
        for (Element e : element) {
            data.get(level).add(e);
        }
    }

    public static void initLevel(int level) {
        GameData.level = level;
        data.clear();
        switch (level) {
            case 1 -> {
                level1();
            }
        }

        Main.elements.addAll(data.get(level));
    }

    public static void level1() {
        Element[] elements = new Element[]{
                new WallLine(200,200,200,800),
                new WallLine(200,800,800,800),
                new WallLine(200,200,800,200),
                new WallLine(800,200,800,400),
                new WallLine(800,600,800,800),
                new WallLine(800,400,1500,400),
                new WallLine(800,600,1500,600),
                new WallLine(1500,0,1500,400),
                new WallLine(1500,600,1500,1000),
                new WallLine(1500,0,2000,0),
                new WallLine(1500,1000,2000,1000),
                new WallLine(2000,0,2000,400),
                new WallLine(2000,600,2000,1000),
                new Door(2000,400,2000,600),

                new SupplyBox(1800, 500,new WalkingDuck(-9000,-9000)),

                new BuffGetter(2500,400,new SpeedUp(200000,3)),
                new BuffGetter(2500,500,new Angry(200000,100)),

                new SupplyBox(2500,600,new WalkingDuck("Boss",-9000,-9000,80,10000000,100000,30,1000))
        };

        add(1, elements);
    }
}