package com.zalinius.zje.architecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zalinius.zje.architecture.input.Clickable;
import com.zalinius.zje.architecture.input.Inputtable;
import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.plugins.AbstractPlugin;

public abstract class GameContainer implements Graphical, Logical {
	private GameLoop loop;
	private GameStage stage;

	public GameContainer(String windowText, int width, int height) {
		stage = new GameStage(this, windowText, width, height);
		loop = new GameLoop(stage, this);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdownActions()));
	}
	
	private void registerPlugins(List<AbstractPlugin> plugins) {
		plugins.forEach((plugin)-> plugin.registerPlugin(stage, loop, stage));
	}
	
	public void addControls(Collection<Inputtable> keyControls, Collection<Clickable> mouseControls) {
		if(keyControls == null) {
			keyControls = new ArrayList<>();
		}
		if(mouseControls == null) {
			mouseControls = new ArrayList<>();
		}
		stage.addKeys(keyControls, mouseControls);
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
	
	public void shutdownActions() {};
	
	public void moveWindow(int x, int y) {
        stage.moveWindow(x, y);
	}

	
	public Locatable mouseLocator() {
		return stage.mouseLocator();
	}
	
	public List<AbstractPlugin> getPlugins(){return new ArrayList<>();};
	

}