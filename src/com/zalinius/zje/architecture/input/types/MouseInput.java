package com.zalinius.zje.architecture.input.types;

import java.awt.event.MouseEvent;

public enum MouseInput{
	MOUSE_LEFT(MouseEvent.BUTTON1),
	MOUSE_MIDDLE(MouseEvent.BUTTON2),
	MOUSE_RIGHT(MouseEvent.BUTTON3),
	;
	
	private int code;
	
	private MouseInput(int code) {
		this.code = code;
	}
	
	public int getAwtMouseEventCode() {
		return code;
	}
	
	public static MouseInput getMouseInput(int awtEventCode) {
		switch (awtEventCode) {
		case MouseEvent.BUTTON1:
			return MOUSE_LEFT;
		case MouseEvent.BUTTON2:
			return MOUSE_MIDDLE;
		case MouseEvent.BUTTON3:
			return MOUSE_RIGHT;

		default:
			return null;
		}
	}

}
