package com.zalinius.zje.architecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zalinius.zje.architecture.input.Clickable;
import com.zalinius.zje.architecture.input.Inputtable;
import com.zalinius.zje.plugins.Plugin;

public abstract class GameContainer implements Graphical, Logical {
	private GameLoop loop;
	private GameStage stage;

	public GameContainer(String windowText, int width, int height) {
		List<Plugin> plugins = getPlugins();
		stage = new GameStage(this, windowText, width, height, plugins);
		loop = new GameLoop(stage, this, plugins);
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
		stage.setVisible(true);
		loop.start();
	}

	public void exit() {
		stage.dispose();
		System.exit(0);
	}
	
	public void moveWindow(int x, int y) {
        stage.moveWindow(x, y);
	}

	
	public Locatable mouseLocator() {
		return stage.mouseLocator();
	}
	
	public List<Plugin> getPlugins(){return new ArrayList<>();};
	

}