package com.zalinius.zje.architecture.input.actions;

import java.awt.Shape;

import com.zalinius.zje.architecture.input.types.MouseInput;


/**
 * A Command-like pattern implementation, for connecting hardware mouse-input to game actions.
 * @author Zalinius
 */
public interface Clickable {
	
	
	/**
	 * The clickable area of this clickable object.
	 * @return A shape. If the mouse event was within the shape, the action will be called.
	 */
	public Shape clickArea();
	
	/**
	 * 
	 * @return The numeric code for the mouse button. See MouseEvent.
	 */
	public MouseInput mouseButton();
	
	/**
	 * Called when the main mouse button is pressed down and then released, i.e. a full click.
	 */
	public void mouseClicked();
	
	/**
	 * Called when the main mouse button is pressed down.
	 */
	public void mousePressed();
	
	/**
	 * Called when the main mouse button is released.
	 */
	public void mouseReleased();
}
