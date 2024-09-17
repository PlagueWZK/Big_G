package com.big_g.main.data;

import com.big_g.main.Main;
import com.big_g.main.element.Door;
import com.big_g.main.interfaces.Element;

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
                new Door(500,200,700,300)
        };

        add(1, elements);
    }
}