package com.zalinius.architecture.input;

/**
 * A Command-like pattern implementation, for connecting hardware key-input to game actions.
 * @author Zach
 */
public interface Inputtable {
	public int keyCode();
	public void pressed();
	public void released();
}
