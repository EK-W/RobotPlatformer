package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import entities.Entity;
import entities.EntityHandler;
import entities.EntityProperties;
import entities.Player;
import geometry.Polygon2D;
import geometry.Vector2D;

public class Main extends JFrame implements ActionListener{
	
	private static DisplayPanel panel = new DisplayPanel();
	
	//Contrary to what the same suggests, this is the size of the level, with the screen just also happening to be that size.
	public static Dimension screenSize = new Dimension(1600, 900);
	
	Timer animate = new Timer(15, this);
	
	InputHandler inputHandler = new InputHandler();
	
	static EntityHandler eh = new EntityHandler();
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	private Main(){
		this.setFocusable(true);
		this.add(panel);
		panel.setPreferredSize(screenSize);
		this.addKeyListener(inputHandler);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		eh.addEntity(new Player(new Polygon2D(new Vector2D(200, 200), 25, 4), EntityProperties.MOVABLE_OBJECT));
		animate.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		eh.update();
		repaint();
	}
}
