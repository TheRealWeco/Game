package weco.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyCheck implements KeyListener {
	
	private static boolean keys[] = new boolean[1000];
	
	public static boolean keysCheck(int keycode){
		if((keycode >= 0)  && (keycode < keys.length))return keys[keycode];
		else return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		if((KeyCode >= 0) && (KeyCode < keys.length))keys[KeyCode] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		if((KeyCode >= 0) && (KeyCode < keys.length))keys[KeyCode] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}

