package com.zalinius.zje.architecture.input.actions;

import com.zalinius.zje.architecture.input.types.BinaryInput;

/**
 * A Command-like pattern implementation, for connecting hardware key-input to game actions.
 * @author Zalinius
 */
public interface Inputtable {
	public static final int BUTTON_OFFSET = 1000;
	
	public BinaryInput binaryInput();
	public void pressed();
	public void released();
}
