package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import debug.Debug;

public class InputHandler implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_ESCAPE: 
			System.exit(0);
			break;
		case KeyEvent.VK_I:
			Debug.toggleVisible();
			break;
		default:
			//Send it to the keyTracker
			KeyTracker.sendKey(e.getKeyCode(), true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyTracker.sendKey(e.getKeyCode(), false);
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
