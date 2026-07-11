package com.darzalgames.zje.architecture.input.types;

import java.util.HashMap;
import java.util.Map;

public enum AxialInput {
	MOUSE_X(4000),
	MOUSE_Y(4001);

	private final int code;

	AxialInput(int code) {
		this.code = code;
	}

	public int getAxisCode() {
		return code;
	}

	private static Map<Integer, AxialInput> axiscodeToEnumMap;

	public static AxialInput getAxisFromControllerEvent(int axisCode) {
		if (axiscodeToEnumMap == null) {
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
