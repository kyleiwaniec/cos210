package edu.mccc.cos210.bugworld;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;


public class BugWorldKeyEventDispatcher implements KeyEventDispatcher {

	private BugGameView bugGameView;
	public BugWorldKeyEventDispatcher(BugGameView view) {
		this.bugGameView = view;
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent ke) {
		if (ke.getID() == KeyEvent.KEY_PRESSED) {
			int key = ke.getKeyCode();
			if (
				key == KeyEvent.VK_W ||
				key == KeyEvent.VK_A ||
				key == KeyEvent.VK_S ||
				key == KeyEvent.VK_D ||
				key == KeyEvent.VK_UP ||
				key == KeyEvent.VK_LEFT ||
				key == KeyEvent.VK_DOWN ||
				key == KeyEvent.VK_RIGHT
			) {
				KeyboardFocusManager.
					getCurrentKeyboardFocusManager().
					redispatchEvent(this.bugGameView, ke)
				;
				return true;
			}
		}
		return false;
	}
}
