package com.zalinius.architecture;

import java.util.Iterator;
import java.util.List;

import com.zalinius.plugins.Plugin;
import com.zalinius.utilities.time.GameClock;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer{
	private final long NS_IN_S = 1_000_000_000;

	private long lastFrameTime;
	private Logical logic;
	private GameStage renderer;
	private List<Plugin> plugins;

	public GameLoop(Logical logic, GameStage gs, List<Plugin> plugins){
		lastFrameTime = 0;
		this.logic = logic;
		this.renderer = gs;
		this.plugins = plugins;
	}

	public void handle(long now)
	{	

		long differential = now - lastFrameTime;
		if(lastFrameTime == 0) {
			differential = 0;
		}
		double delta = (double) differential / NS_IN_S ;
		lastFrameTime = now;

		// update the gameContainer logic
		update(delta);
		// draw everything
		render();

	}

	/**
	 * Updates the gameContainer logic
	 * @param delta The ratio of the last frame time, with respect to a perfect 60 FPS
	 */
	public void update(double delta) {
		GameClock.update(delta);
		renderer.update(delta);
		logic.update(delta);

		for (Iterator<Plugin> iterator = plugins.iterator(); iterator.hasNext();) {
			Plugin plugin = iterator.next();
			plugin.update(delta);
		}
	}

	public void render() {
		renderer.render();

	}
}
