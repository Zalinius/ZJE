package com.zalinius.zje.plugins;

import com.zalinius.zje.architecture.GameLoop;
import com.zalinius.zje.architecture.GameStage;

public interface AbstractPlugin {
	public void registerPlugin(GameLoop gameLoop, GameStage gameStage);
}
