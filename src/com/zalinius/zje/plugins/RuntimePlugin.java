package com.zalinius.zje.plugins;

import java.awt.Graphics2D;

import com.zalinius.zje.architecture.GameLoop;
import com.zalinius.zje.architecture.GameStage;

public abstract class RuntimePlugin implements AbstractPlugin{

	/**
	 * Called before the game gets updated
	 * @param delta
	 */
	public void updateBefore(double delta) {}

	/**
	 * Called after the game gets updated
	 * @param delta
	 */
	public void updateAfter(double delta) {}


	/**
	 * Rendered before (or under) the game
	 * @param g
	 */
	public void renderBefore(Graphics2D g) {}

	/**
	 * Rendered after (or over) the game
	 * @param g
	 */
	public void renderAfter(Graphics2D g) {}
	
	
	@Override
	public final void registerPlugin(GameLoop gameLoop, GameStage gameStage) {
		gameLoop.accept(this);
		gameStage.accept(this);
		renderInitialize(gameStage);
	}

	/**
	 * Called at application start before any rendering
	 * @param gameStage 
	 */
	public void renderInitialize(GameStage gameStage) {}

}
