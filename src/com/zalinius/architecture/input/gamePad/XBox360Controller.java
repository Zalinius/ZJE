package com.zalinius.architecture.input.gamePad;

import net.java.games.input.Component.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class XBox360Controller{
	
	public enum Button360{A, B, X, Y, LC, RC, LB, RB, ST, SL, D_UP, D_DOWN, D_LEFT, D_RIGHT}
	public enum Axis{L, R, DPAD, triggers}
	
	private static Identifier ID_A = Identifier.Button._0;
	private static Identifier ID_B = Identifier.Button._1;
	private static Identifier ID_X = Identifier.Button._2;
	private static Identifier ID_Y = Identifier.Button._3;
	
	private static Identifier ID_LX = Identifier.Axis.X;
	private static Identifier ID_LY = Identifier.Axis.Y;
	private static Identifier ID_RX = Identifier.Axis.RX;
	private static Identifier ID_RY = Identifier.Axis.RY;
	private static Identifier ID_LC = Identifier.Button._8;
	private static Identifier ID_RC = Identifier.Button._9;

	private static Identifier ID_START = Identifier.Button._7;
	private static Identifier ID_BACK = Identifier.Button._6;

	private static Identifier ID_TRIGGERS = Identifier.Axis.Z;
	private static Identifier ID_LB = Identifier.Button._4;
	private static Identifier ID_RB = Identifier.Button._5;
	private static Identifier ID_DPAD = Identifier.Axis.POV;

	public static Identifier getIdentifier(Button360 button) {
		switch (button) {
		case A: return ID_A;
		case B: return ID_B;
		case D_DOWN: return ID_DPAD;
		case D_LEFT: return ID_DPAD;
		case D_RIGHT: return ID_DPAD;
		case D_UP: return ID_DPAD;
		case LB: return ID_LB;
		case LC: return ID_LC;
		case RB: return ID_RB;
		case RC: return ID_RC;
		case SL: return ID_BACK;
		case ST: return ID_START;
		case X: return ID_X;
		case Y: return ID_Y;
		default:
			return null;
		}
	}

	public static Button360 getDpadDirection(float value) {
		if(value == 0.25) {
			return Button360.D_UP;
		}
		else if(value == 0.50){
			return Button360.D_RIGHT;
		}
		else if(value == 0.75){
			return Button360.D_DOWN;
		}
		else if(value == 1.0){
			return Button360.D_LEFT;
		}
		
		return null;
	}
	
	public static Iterator<Button360> dpadButtons(){
		Collection<Button360> butts = new ArrayList<>();
		butts.add(Button360.D_UP);
		butts.add(Button360.D_DOWN);
		butts.add(Button360.D_LEFT);
		butts.add(Button360.D_RIGHT);
		
		return butts.iterator();
	}
	

}
