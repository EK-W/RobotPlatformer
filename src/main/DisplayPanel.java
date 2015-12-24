package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import debug.Debug;

public class DisplayPanel extends JPanel{
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Main.eh.paintEntities(g2);
		Debug.paint(g2);
	}

}
