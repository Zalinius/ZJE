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

	public static final Button ESCAPE = new Button(KeyCode.ESCAPE);
	public static final Button SPACE = new Button(KeyCode.SPACE);
	public static final Button END = new Button(KeyCode.END);

	public static final Button A = new Button(KeyCode.A);
	public static final Button B = new Button(KeyCode.B);
	public static final Button C = new Button(KeyCode.C);
	public static final Button D = new Button(KeyCode.D);
	public static final Button E = new Button(KeyCode.E);
	public static final Button F = new Button(KeyCode.F);
	public static final Button G = new Button(KeyCode.G);
	public static final Button H = new Button(KeyCode.H);
	public static final Button I = new Button(KeyCode.I);
	public static final Button J = new Button(KeyCode.J);
	public static final Button K = new Button(KeyCode.K);
	public static final Button L = new Button(KeyCode.L);
	public static final Button M = new Button(KeyCode.M);
	public static final Button N = new Button(KeyCode.N);
	public static final Button O = new Button(KeyCode.O);
	public static final Button P = new Button(KeyCode.P);
	public static final Button Q = new Button(KeyCode.Q);
	public static final Button R = new Button(KeyCode.R);
	public static final Button S = new Button(KeyCode.S);
	public static final Button T = new Button(KeyCode.T);
	public static final Button U = new Button(KeyCode.U);
	public static final Button V = new Button(KeyCode.V);
	public static final Button W = new Button(KeyCode.W);
	public static final Button X = new Button(KeyCode.X);
	public static final Button Y = new Button(KeyCode.Y);
	public static final Button Z = new Button(KeyCode.Z);
	

	public static final Button A_360 = new Button(Button360.A);
	public static final Button UP_360 = new Button(Button360.D_UP);
	public static final Button DOWN_360 = new Button(Button360.D_DOWN);
	public static final Button LEFT_360 = new Button(Button360.D_LEFT);
	public static final Button RIGHT_360 = new Button(Button360.D_RIGHT);
	
	
	
	

}
