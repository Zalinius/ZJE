package com.zalinius.zje.architecture;

import com.zalinius.zje.utilities.time.GameClock;

public class GameLoop {
    final int TARGET_FPS = 60;
    final long NS_IN_S = 1000000000;
    
    long totalTime;
    int framesInLastSecond;

    GameStage stage;
    Logical logic;

    public GameLoop(GameStage stage, Logical logic){
    	totalTime = 0;
    	framesInLastSecond = 0;
        this.stage = stage;
        this.logic = logic;
    }
    
    public void start() {
    	gameLoop();
    }

    private void gameLoop()
    {
        long lastLoopTime = System.nanoTime();
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; //1 Second / 60 fps

        // keep looping round till the gameContainer ends
        boolean gameRunning = true;
        while (gameRunning)
        {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            totalTime += updateLength;
            ++framesInLastSecond;
            checkFramerate(updateLength);
            lastLoopTime = now;
            double delta = updateLength / 1E9;

            //System.out.print((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 + "\n");

            //System.out.print("FPS: " + 1/delta + "\n");

            // update the gameContainer logic
            update(delta);
            // draw everything
            render();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try{
            	long current = System.nanoTime();
            	long sleepTime = (lastLoopTime-current + OPTIMAL_TIME)/1000000;
            	if(sleepTime < 0) {
            		sleepTime = 0;
            	}
                Thread.sleep(sleepTime);
            }
            catch (IllegalArgumentException | InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    private void checkFramerate(long lastFrameLength) {
    	long secondIndex = totalTime/NS_IN_S ;
    	long lastSecondIndex = (totalTime-lastFrameLength)/NS_IN_S;
    	if(lastSecondIndex < secondIndex) {
    		stage.setFPS(framesInLastSecond);
    		framesInLastSecond = 0;
    	}
	}

	/**
     * Updates the gameContainer logic
     * @param delta The ratio of the last frame time, with respect to a perfect 60 FPS
     */
    public void update(double delta) {
    	GameClock.update(delta);
    	logic.update(delta);
    }

    public void render() {
        stage.repaint();
    }
}
