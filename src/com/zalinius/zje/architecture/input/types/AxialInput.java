package com.zalinius.zje.architecture.input.types;

import java.util.HashMap;
import java.util.Map;

import org.libsdl.SDL;

public enum AxialInput {

	LEFT_STICK_HORIZONTAL(SDL.SDL_CONTROLLER_AXIS_LEFTX),
	LEFT_STICK_VERTICAL(SDL.SDL_CONTROLLER_AXIS_LEFTY),
	RIGHT_STICK_HORIZONTAL(SDL.SDL_CONTROLLER_AXIS_RIGHTX),
	RIGHT_STICK_VERTICAL(SDL.SDL_CONTROLLER_AXIS_RIGHTY),
	LEFT_TRIGGER(SDL.SDL_CONTROLLER_AXIS_TRIGGERLEFT),
	RIGHT_TRIGGER(SDL.SDL_CONTROLLER_AXIS_TRIGGERRIGHT),
	MOUSE_X(4000),
	MOUSE_Y(4001)
	;

	private int code;

	private AxialInput(int code) {
		this.code = code;
	}

	public int getAxisCode() {
		return code;
	}


	private static Map<Integer, AxialInput> axiscodeToEnumMap;
	public static AxialInput getAxisFromControllerEvent(int axisCode) {
		if(axiscodeToEnumMap == null) {
			axiscodeToEnumMap = new HashMap<>();
			for (int i = 0; i < AxialInput.values().length; i++) {
				AxialInput input = AxialInput.values()[i];
				axiscodeToEnumMap.put(input.getAxisCode(), input);
			}
		}

		return axiscodeToEnumMap.get(axisCode);
	}
	
	public boolean mouseBased() {
		return code >= 4000;
	}

}
