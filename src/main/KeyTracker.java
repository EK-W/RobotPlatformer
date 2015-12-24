package main;

import java.awt.event.KeyEvent;

public class KeyTracker {
	private static final int LEFT_BUTTON = KeyEvent.VK_LEFT;
	private static final int RIGHT_BUTTON = KeyEvent.VK_RIGHT;
	private static final int JUMP_BUTTON = KeyEvent.VK_SPACE;
	
	public static boolean LEFT;
	public static boolean RIGHT;
	public static boolean JUMP;
	
	protected static void sendKey(int keyCode, boolean down){
		switch(keyCode){
		case LEFT_BUTTON: LEFT = down; break;
		case RIGHT_BUTTON: RIGHT = down; break;
		case JUMP_BUTTON: JUMP = down; break;
		}
	}
}
