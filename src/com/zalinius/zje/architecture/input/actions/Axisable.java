package com.zalinius.zje.architecture.input.actions;

import com.zalinius.zje.architecture.input.types.AxialInput;

public interface Axisable {
	public AxialInput axialInput();
	public void axisMoved(float value);
}
