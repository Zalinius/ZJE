package com.zalinius.zje.architecture;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.zalinius.zje.plugins.RuntimePlugin;
import com.zalinius.zje.utilities.time.GameClock;

public class GameLoop {
	private static final  int TARGET_FPS = 60;
	private static final Duration SECOND = Duration.ofSeconds(1);
	private static final Duration MILLISECOND = Duration.ofMillis(1);

	private GameStage stage;
	private Logical logic;
	private Logical inputPolling;

	private List<RuntimePlugin> plugins;

	private final AtomicBoolean gameRunning;

	public GameLoop(GameStage stage, Logical logic){
		this.stage = stage;
		this.inputPolling = stage.getInputPolling();
		this.logic = logic;
		this.plugins = new ArrayList<>();
		this.gameRunning = new AtomicBoolean(false);
	}

	public void start() {
		if(!gameRunning.get()) {
			gameRunning.set(true);
			gameLoop();
		}
	}

	private void gameLoop()
	{
		Duration lastUpdateLength = Duration.ofNanos(System.nanoTime());
		final Duration optimalFrameTime = SECOND.dividedBy(TARGET_FPS);

		// keep looping round till the gameContainer ends
		while (gameRunning.get())
		{
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			Duration updateStartTime = Duration.ofNanos(System.nanoTime());
			Duration updateLength = updateStartTime.minus(lastUpdateLength);
			lastUpdateLength = updateStartTime;
			double delta = updateLength.toNanos() / 1.0E9;

			// update the gameContainer logic
			update(delta);
			// draw everything
			render();

			// we want each frame to take optimalFrameTime, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give
			// us our final value to wait for
			// remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
			try{
				Duration current = Duration.ofNanos(System.nanoTime());
				Duration sleepTime = lastUpdateLength.minus(current).plus(optimalFrameTime);
				if(sleepTime.isNegative()) {
					sleepTime = Duration.ZERO;
				}
				long sleepMilliseconds = sleepTime.toMillis();
				int sleepNanosecondsPart = sleepTime.toNanosPart() % MILLISECOND.toNanosPart();

				Thread.sleep(sleepMilliseconds, sleepNanosecondsPart);
			}
			catch (IllegalArgumentException | InterruptedException e){
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}

		}
	}

	/**
	 * Updates the gameContainer logic
	 * @param delta The ratio of the last frame time, with respect to a perfect 60 FPS
	 */
	public void update(double delta) {
		plugins.forEach( plugin -> plugin.updateBefore(delta));

		GameClock.update(delta);
		inputPolling.update(delta);
		logic.update(delta);

		plugins.forEach( plugin -> plugin.updateAfter(delta));
	}

	public void render() {
		stage.repaint();
	}

	public void accept(RuntimePlugin runtimePlugin) {
		plugins.add(runtimePlugin);
	}
}
