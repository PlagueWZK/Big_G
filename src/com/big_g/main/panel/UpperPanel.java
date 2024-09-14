package com.big_g.main.panel;

import javax.swing.*;
import java.awt.*;

/**
 * @author PlagueWZK
 * description: UpperButton
 * date: 2024/9/3 19:30
 */

public class UpperPanel extends JPanel{
    public UpperPanel(){
        this.setLayout(new GridLayout());
        this.add(new JButton("Reset"));
        this.add(new JButton("Next Part"));
    }
}
