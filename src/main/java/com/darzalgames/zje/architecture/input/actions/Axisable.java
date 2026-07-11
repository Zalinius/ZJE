package com.darzalgames.zje.architecture.input.actions;

import com.darzalgames.zje.architecture.input.types.AxialInput;

public interface Axisable {
	public AxialInput axialInput();
	public void axisMoved(float value);
}
