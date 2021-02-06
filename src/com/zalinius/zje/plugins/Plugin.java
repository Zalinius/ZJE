package com.zalinius.zje.plugins;

import java.awt.Graphics2D;

public abstract class Plugin {

	/**
	 * Called before the game gets updated
	 * @param delta
	 */
	public void updateBefore(double delta) {};

	/**
	 * Called after the game gets updated
	 * @param delta
	 */
	public void updateAfter(double delta) {};


	/**
	 * Rendered before (or under) the game
	 * @param g
	 */
	public void renderBefore(Graphics2D g) {};

	/**
	 * Rendered after (or over) the game
	 * @param g
	 */
	public void renderAfter(Graphics2D g) {};

}
