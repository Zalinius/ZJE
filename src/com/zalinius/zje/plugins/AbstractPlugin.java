package com.zalinius.zje.plugins;

import com.zalinius.zje.architecture.DoubleBufferedFrame;
import com.zalinius.zje.architecture.GameLoop;
import com.zalinius.zje.architecture.GameStage;

public abstract class AbstractPlugin {

	public abstract void registerPlugin(DoubleBufferedFrame frame, GameLoop gameLoop, GameStage gameStage);
}
