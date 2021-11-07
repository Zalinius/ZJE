package com.zalinius.zje.architecture.input;

import java.util.EnumMap;
import java.util.Map;

import org.libsdl.SDL_Error;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.zalinius.zje.architecture.Logical;
import com.zalinius.zje.architecture.input.actions.Axisable;
import com.zalinius.zje.architecture.input.actions.Inputtable;
import com.zalinius.zje.architecture.input.types.AxialInput;
import com.zalinius.zje.architecture.input.types.BinaryInput;

import uk.co.electronstudio.sdl2gdx.SDL2Controller;
import uk.co.electronstudio.sdl2gdx.SDL2ControllerManager;

public class GamepadManager implements Logical{
	
	private static final float AXIS_DEAD_ZONE = 0.2f;

	private SDL2Controller mainController;
	private SDL2ControllerManager controllerManager;
	
	private Map<AxialInput, Axisable> axisInputs;
	private Map<BinaryInput, Inputtable> buttonInputs;
	private Map<BinaryInput, Boolean> buttonInputStates;
	
	public SDL2Controller mainController() {
		return mainController;
	}

	public GamepadManager(){ 
		this.axisInputs = new EnumMap<>(AxialInput.class);
		this.buttonInputs = new EnumMap<>(BinaryInput.class);
		this.buttonInputStates = new EnumMap<>(BinaryInput.class);
		
		this.controllerManager = new SDL2ControllerManager();
		controllerManager.addListenerAndRunForConnectedControllers(new ControllerListener() {

			@Override
			public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
				return false;
			}

			@Override
			public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
				return false;
			}

			@Override
			public boolean povMoved(Controller controller, int povCode, PovDirection value) {
				return false;
			}

			@Override
			public void disconnected(Controller controller) {
				SDL2Controller sdl2Controller = (SDL2Controller) controller;
				if(sdl2Controller.getPlayerIndex() == 0) {
					mainController = null;
				}
			}

			@Override
			public void connected(Controller controller) {
				SDL2Controller sdl2Controller = (SDL2Controller) controller;
				if(sdl2Controller.getPlayerIndex() == 0) {
					mainController = sdl2Controller;
				}
			}

			@Override
			public boolean buttonUp(Controller controller, int buttonCode) {
				BinaryInput button = BinaryInput.getButtonValueFromGamepadEvent(buttonCode);
				buttonPressSwitchBoard(button, false);
				return false;
			}

			@Override
			public boolean buttonDown(Controller controller, int buttonCode) {
				BinaryInput button = BinaryInput.getButtonValueFromGamepadEvent(buttonCode);
				buttonPressSwitchBoard(button, true);
				return false;
			}

			@Override
			public boolean axisMoved(Controller controller, int axisCode, float value) {
				AxialInput axis = AxialInput.getAxisFromControllerEvent(axisCode);
				if(Math.abs(value) < AXIS_DEAD_ZONE) {
					value = 0f;
				}
				
				Axisable axisable = axisInputs.get(axis);
				axisable.axisMoved(value);

				return false;
			}

			@Override
			public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
				return false;
			}
		});
	}

	@Override
	public void update(double delta) {
		try {
			controllerManager.pollState();
		} catch (SDL_Error e) {
			e.printStackTrace();
		}		
	}

	public void addAxisInput(Axisable axis) {
		axisInputs.put(axis.axialInput(), axis);		
	}

	public void addButtonInput(Inputtable buttonInput) {
		buttonInputs.put(buttonInput.binaryInput(), buttonInput);
		buttonInputStates.put(buttonInput.binaryInput(), false);
	}
	
	private void buttonPressSwitchBoard(BinaryInput button, boolean press){
		if(buttonInputs.containsKey(button)) {
			if(press) {
				if(Boolean.FALSE.equals(buttonInputStates.get(button))) {
					Inputtable input = buttonInputs.get(button);
					input.pressed();
					buttonInputStates.put(button, true);
				}
			}
			else {
				Inputtable input = buttonInputs.get(button);
				input.released();
				buttonInputStates.put(button, false);
			}
		}
	}



}
