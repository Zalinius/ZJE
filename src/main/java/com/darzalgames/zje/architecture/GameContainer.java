package com.darzalgames.zje.architecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.darzalgames.zje.architecture.input.actions.Axisable;
import com.darzalgames.zje.architecture.input.actions.Clickable;
import com.darzalgames.zje.architecture.input.actions.Inputtable;
import com.darzalgames.zje.plugins.AbstractPlugin;

public abstract class GameContainer implements Graphical, Logical {
	private final GameLoop loop;
	private final GameStage stage;

	protected GameContainer(String windowText, int width, int height) {
		stage = new GameStage(this, windowText, width, height);
		loop = new GameLoop(stage, this);
		Runtime.getRuntime().addShutdownHook(new Thread(this::shutdownActions));
	}

	private void registerPlugins(List<AbstractPlugin> plugins) {
		plugins.forEach(plugin -> plugin.registerPlugin(loop, stage));
	}

	public void addControls(Collection<Inputtable> keyControls, Collection<Clickable> mouseControls, Collection<Axisable> axisControls) {
		if (keyControls == null) {
			keyControls = new ArrayList<>();
		}
		if (mouseControls == null) {
			mouseControls = new ArrayList<>();
		}
		if (axisControls == null) {
			axisControls = new ArrayList<>();
		}
		stage.addKeys(keyControls, mouseControls, axisControls);
	}

	public void resetClickables(Collection<Clickable> newClickables) {
		if (newClickables == null) {
			newClickables = new ArrayList<>();
		}
		stage.resetClickables(newClickables);
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

	public List<AbstractPlugin> getPlugins() {
		return new ArrayList<>();
	}

}