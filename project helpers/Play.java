package edu.mccc.cos210.regex;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;

import java.util.*;

import javax.swing.*;


public class Play extends JFrame {
    private void setGui() {
	try {
	    setLocation(0, 100);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container cp = getContentPane();
	    URL url = getClass().getResource("/edu/mccc/cos210/regex/image.jpg");
	    BufferedImage img1 = ImageIO.read(url);
	    cp.add(new JLabel(new ImageIcon(img1)));
		    } catch (Exception e) {
			e.printStackTrace();
		    }
    }

    public static void main(String[] args) {
	try {
	    SwingUtilities.invokeAndWait(new Runnable() {
		public void run() {
		    Play f = new Play();
		    f.setGui();
		    f.setSize(200, 200);
		    f.setVisible(true);
		}
	    });
	} catch (Throwable e) {
	    e.printStackTrace();
	}
    }
}
