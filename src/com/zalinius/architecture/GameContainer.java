package com.zalinius.architecture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.architecture.input.gamePad.XBox360Controller;
import com.zalinius.physics.Point2D;

import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.EventQueue;

public abstract class GameContainer extends Application {
	private GameLoop loop;
	private GameStage gameStage;
	//TODO window exit action?
	//TODO adding controls from where?


	public void addControls(Collection<Inputtable> keyControls, Collection<Clickable> mouseControls) {
		if(keyControls == null) {
			keyControls = new ArrayList<>();
		}
		if(mouseControls == null) {
			mouseControls = new ArrayList<>();
		}
		gameStage.addKeys(keyControls, mouseControls);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		gameStage = new GameStage(primaryStage, gameGraphics(), windowSize(), windowTitle());
		loop = new GameLoop(gameLogic(), gameStage);
		loop.start();
		primaryStage.show();
	}

	public GameStage gameStage() {
		return gameStage;
	}

	public abstract Logical gameLogic();	
	public abstract Graphical gameGraphics();
	public abstract Point2D windowSize();
	public abstract String windowTitle();

	public static void main(String[] args) {
		Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
		ArrayList<XBox360Controller> gamePads = new ArrayList<>();
		for(int i =0;i<ca.length;i++){
			if(ca[i].getType().equals(Type.GAMEPAD)) {
				gamePads.add(new XBox360Controller(ca[i]));
				
			}
		}
		//List<Component> inputs = Arrays.asList(gamePad.getComponents());
		while(true) {
			for (Iterator<XBox360Controller> iterator = gamePads.iterator(); iterator.hasNext();) {
				XBox360Controller xBox360Controller = iterator.next();
				xBox360Controller.update(0);
			}
			
//			gamePad.poll();
//			for (Iterator<Component> iterator = inputs.iterator(); iterator.hasNext();) {
//				Component component = iterator.next();	
//				float poll = Math.abs(component.getPollData());
//				if(poll > 0.1) {
//					System.out.println(component.getIdentifier() + " " + component.getIdentifier().getClass());
//
////
////					if (component.isRelative()) {
////						System.out.print(" Relative");
////					} else {
////						System.out.print(" Absolute");
////					}
////					if (component.isAnalog()) {
////						System.out.print(" Analog");
////					} else {
////						System.out.print(" Digital");
////					}	
//				}
//
//			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}


}