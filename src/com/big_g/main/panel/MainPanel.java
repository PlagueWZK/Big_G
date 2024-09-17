package com.big_g.main.panel;

import com.big_g.main.Main;
import com.big_g.main.clock.MilliTimerClock;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: MainPanel
 * date: 2024/9/3 19:25
 */

public class MainPanel extends JPanel{

    public MilliTimerClock paintClock;


    public MainPanel() {
        init();
    }

    public void init() {
        this.setBackground(Color.GRAY);
        paintClock = new MilliTimerClock(1L);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (paintClock.isReady()){
            super.paintComponent(g);
            BufferedImage image = new BufferedImage(Main.frame.getWidth() * 8 / 10, Main.frame.getHeight() * 8 / 10, 7);
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            Main.paint(g2d);
            g.drawImage(image,0,0,Main.frame.getWidth(),Main.frame.getHeight(),null);
        }
    }
}
