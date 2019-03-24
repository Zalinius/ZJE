package com.zalinius.architecture.input;

import com.zalinius.architecture.input.gamePad.XBox360Controller.Button360;

import javafx.scene.input.KeyCode;

public class Button {
	
	private enum Type {BUTTON, KEY}
	
	private final Button360 button;
	private final KeyCode key;
	private final Type type;

	private Button(KeyCode code) {
		this.key = code;
		this.button = null;
		type = Type.KEY;
	}
	private Button(Button360 button) {
		this.button = button;
		this.key = null;
		type = Type.BUTTON;
	}

	public boolean isKey() {
		return type == Type.KEY;
	}
	public boolean is360Button() {
		return type == Type.BUTTON;
	}
	
	public Button360 button360() {
		return button;
	}
	public KeyCode keyCode() {
		return key;
	}
	
	public static final Button UP = new Button(KeyCode.UP);
	public static final Button DOWN = new Button(KeyCode.DOWN);
	public static final Button LEFT = new Button(KeyCode.LEFT);
	public static final Button RIGHT = new Button(KeyCode.RIGHT);
	public static final Button END = new Button(KeyCode.END);

	public static final Button A_360 = new Button(Button360.A);
	public static final Button UP_360 = new Button(Button360.D_UP);
	public static final Button DOWN_360 = new Button(Button360.D_DOWN);
	public static final Button LEFT_360 = new Button(Button360.D_LEFT);
	public static final Button RIGHT_360 = new Button(Button360.D_RIGHT);
	
	

}
