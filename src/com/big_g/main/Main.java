package com.big_g.main;

import com.big_g.main.clock.NanoTimerClock;
import com.big_g.main.data.GameData;
import com.big_g.main.interfaces.Element;
import com.big_g.main.interfaces.Interoperable;
import com.big_g.main.objects.G;
import com.big_g.main.panel.MainPanel;
import com.big_g.main.static_value.ImageData;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Random;

/**
 * @author PlagueWZK
 * description: Main
 * date: 2024/9/3 19:21
 */

public class Main {
    public static final int Bound = 1000;
    public static final boolean DEVELOPMENT_MODE = false;
    public static final long FREQUENCY = 10000;


    public static JFrame frame;
    public static int width;
    public static int height;
    public static Insets frameInsets;


    public static MainPanel mainPanel;
    public static NanoTimerClock runClock;
    public static G G;
    public static Random random;

    public static HashSet<Interoperable> interoperableSets;
    public static HashSet<Element> elements;



    public static void main(String[] args) {
        init();
        while (true) {
            if (runClock.isReady()) {
                update();
                mainPanel.repaint();
            }
        }
    }

    public static void init() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Big G");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameInsets = frame.getInsets();
        width = frame.getWidth() * 8 / 10;
        height = (frame.getHeight() - frameInsets.top) * 8 / 10;



        runClock = new NanoTimerClock(FREQUENCY);
        mainPanel = new MainPanel();
        G = new G("G");
        random = new Random();

        frame.add(mainPanel);

        interoperableSets = new HashSet<>();
        elements = new HashSet<>();
        ImageData.init();

        GameData.initLevel(1);

    }

    public static void update() {
        frameInsets = frame.getInsets();
        width = frame.getWidth() * 8 / 10;
        height = (frame.getHeight() - frameInsets.top) * 8 / 10;

        for (Element element : elements) {
            element.update();
        }
        G.update();
        elements.removeIf(Element::needDel);
        interoperableSets.removeIf(Interoperable::needDel);
    }

    public static void paint(Graphics2D g) {

        try{
            for (Element element : elements) {
                element.paint(g);
            }
        } catch (ConcurrentModificationException ignored){}


        G.paint(g);
    }
}
