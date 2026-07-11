package com.darzalgames.zje.plugins;

import com.darzalgames.zje.architecture.DoubleBufferedFrame;
import com.darzalgames.zje.architecture.GameLoop;
import com.darzalgames.zje.architecture.GameStage;

public abstract class RenderSettings implements AbstractPlugin {
	
	public abstract void applySettings(DoubleBufferedFrame frame);

	@Override
	public final void registerPlugin(GameLoop gameLoop, GameStage gameStage) {
		gameStage.accept(this);
	}
}
