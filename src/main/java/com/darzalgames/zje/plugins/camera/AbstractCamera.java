package com.darzalgames.zje.plugins.camera;

import java.awt.Dimension;
import java.util.function.Supplier;

import com.darzalgames.zje.architecture.GameStage;
import com.darzalgames.zje.physics.Locatable;
import com.darzalgames.zje.physics.Point;
import com.darzalgames.zje.physics.Vector;
import com.darzalgames.zje.plugins.RuntimePlugin;
import com.darzalgames.zje.plugins.configuration.ScreenStrategy;

public abstract class AbstractCamera extends RuntimePlugin implements Locatable {
	private Locatable target;
	private ScreenStrategy screenStrategy;
	private Supplier<Dimension> currentScreenSize;

	public AbstractCamera(Locatable target, ScreenStrategy screenStrategy) {
		this.target = target;
		this.screenStrategy = screenStrategy;
	}
	
	@Override
	public final void renderInitialize(GameStage gameStage) {
		this.currentScreenSize = gameStage::getSize;
	}

	
	protected final Point target() {
		return target.position();
	}
	
	protected final Point getGameScreenCenter() {
		return new Vector(screenStrategy.getGameSize(currentScreenSize.get())).scale(0.5).toPoint();
	}
}
