package com.darzalgames.zje.plugins;

import com.darzalgames.zje.architecture.GameLoop;
import com.darzalgames.zje.architecture.GameStage;

public interface AbstractPlugin {
	public void registerPlugin(GameLoop gameLoop, GameStage gameStage);
}
