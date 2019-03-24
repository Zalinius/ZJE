package com.zalinius.architecture.input;

/**
 * A Command-like pattern implementation, for connecting hardware key-input to game actions.
 * @author Zach
 */
public abstract class Inputtable {
	public abstract Button button();
	public void pressed() {}
	public void released() {}
	public void typed() {}
	public void held(double delta) {}
}
