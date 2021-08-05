package com.zalinius.zje.architecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zalinius.zje.architecture.input.RumbleListener;
import com.zalinius.zje.architecture.input.actions.Axisable;
import com.zalinius.zje.architecture.input.actions.Clickable;
import com.zalinius.zje.architecture.input.actions.Inputtable;
import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.plugins.AbstractPlugin;

public abstract class GameContainer implements Graphical, Logical {
	private GameLoop loop;
	private GameStage stage;

	protected GameContainer(String windowText, int width, int height) {
		stage = new GameStage(this, windowText, width, height);
		loop = new GameLoop(stage, this);
		Runtime.getRuntime().addShutdownHook(new Thread(this::shutdownActions));
	}

	private void registerPlugins(List<AbstractPlugin> plugins) {
		plugins.forEach( plugin -> plugin.registerPlugin(stage, loop, stage));
	}

	public void addControls(Collection<Inputtable> keyControls, Collection<Clickable> mouseControls, Collection<Axisable> axisControls) {
		if(keyControls == null) {
			keyControls = new ArrayList<>();
		}
		if(mouseControls == null) {
			mouseControls = new ArrayList<>();
		}
		if(axisControls == null) {
			axisControls = new ArrayList<>();
		}
		stage.addKeys(keyControls, mouseControls, axisControls);
	}

	public RumbleListener getRumbleListener() {
		return stage.rumbleListener();
	}

	public void startGame() {
		registerPlugins(getPlugins());
		stage.setVisible(true);
		loop.start();
	}

	public final void exit() {
		stage.dispose();
		System.exit(0);
	}

	public void shutdownActions() {}

	public void moveWindow(int x, int y) {
		stage.moveWindow(x, y);
	}


	public Locatable mouseLocator() {
		return stage.mouseLocator();
	}

	public List<AbstractPlugin> getPlugins(){return new ArrayList<>();}


}