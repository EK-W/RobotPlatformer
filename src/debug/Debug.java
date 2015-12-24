package debug;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Debug {
	private static boolean visible = false;
	
	private static int width = 750;
	private static ArrayList<Debuggable> debugItems = new ArrayList<Debuggable>();
	
	public static void setVisible(boolean visible){
		Debug.visible = visible;
	}
	
	/**
	 * Toggles the debug screen's visibility
	 * @return the screen's new visibility
	 */
	public static boolean toggleVisible(){
		visible = !visible;
		return visible;
	}
	
	public static boolean getVisibility(){
		return visible;
	}
	
	public static void add(Debuggable d){
		if(!debugItems.contains(d)){
			debugItems.add(d);
		}
	}
	public static void remove(Debuggable d){
		debugItems.remove(d);
	}
	
	public static void paint(Graphics2D g){
		if(!visible)return;
		ArrayList<String> info = new ArrayList<String>();
		int height = g.getFontMetrics().getHeight();
		for(Debuggable i: debugItems){
			i.addDebugInfo(info);
		}
		g.setColor(new Color(25, 25, 25, 240));
		g.fillRect(0, 0, width, info.size() * g.getFontMetrics().getHeight());
		g.setStroke(new BasicStroke(1));
		for(int i = 0; i < info.size(); i++){
			g.setColor(new Color(0, 0, 0, 240));
			g.drawRect(0, i * height, width, height);
			g.setColor(Color.white);
			g.drawString(info.get(i), 5, ((i + 1) * height) - g.getFontMetrics().getDescent());
		}
		
	}
}
