package com.zalinius.architecture;

import com.zalinius.utilities.time.GameClock;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer{
	final long NS_IN_S = 1_000_000_000;

	long time;
	long lastFrameTime;
	long accumulator;

	int framesInLastSecond;

	Logical logic;
	GameStage renderer;

	public GameLoop(Logical logic, GameStage gs){
		time = 0;
		lastFrameTime = 0;
		framesInLastSecond = 0;
		accumulator = 0;
		this.logic = logic;
		this.renderer = gs;
	}

	public void handle(long now)
	{	

		long differential = now - lastFrameTime;
		if(lastFrameTime == 0) {
			differential = 0;
		}
		time += now;
		double delta = (double) differential / NS_IN_S ;
		lastFrameTime = now;

		++framesInLastSecond;
		accumulator += differential;
		//checkFramerate();

		// update the gameContainer logic
		update(delta);
		// draw everything
		render();

	}


	private void checkFramerate() {
		long secondsAccumulated = accumulator/NS_IN_S ;
		if(secondsAccumulated >0) {
			System.out.println(framesInLastSecond);
			//stage.setFPS(framesInLastSecond);
			framesInLastSecond = 0;
			accumulator -= NS_IN_S;
		}
	}

	/**
	 * Updates the gameContainer logic
	 * @param delta The ratio of the last frame time, with respect to a perfect 60 FPS
	 */
	public void update(double delta) {
		GameClock.update(delta);
		renderer.update(delta);
		logic.update(delta);
	}

	public void render() {
		renderer.render();
	}
}
