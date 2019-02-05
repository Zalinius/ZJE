package com.zalinius.architecture.input;

import javafx.scene.input.KeyCode;

/**
 * A Command-like pattern implementation, for connecting hardware key-input to game actions.
 * @author Zach
 */
public abstract class Inputtable {
	public abstract KeyCode keyCode();
	public void pressed() {}
	public void released() {}
	public void typed() {}
	public void held() {}
}
