package com.zalinius.zje.plugins;

import com.zalinius.zje.architecture.DoubleBufferedFrame;
import com.zalinius.zje.architecture.GameLoop;
import com.zalinius.zje.architecture.GameStage;

public abstract class RenderSettings implements AbstractPlugin {
	
	public abstract void applySettings(DoubleBufferedFrame frame);

	@Override
	public final void registerPlugin(DoubleBufferedFrame frame, GameLoop gameLoop, GameStage gameStage) {
		frame.accept(this);
	}
}
