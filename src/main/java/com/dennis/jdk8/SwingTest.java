package com.dennis.jdk8;

import javax.swing.*;
import java.awt.*;

/**
* 描述：
* @author   Dennis
* @date 2020/1/5 20:17
* @version  1.0
*/
public class SwingTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JButton jButton = new JButton();

        jButton.addActionListener(event->{
            System.out.println("button pressed!");
            System.out.println("hello world");
            System.out.println("execute......");
        });

        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
