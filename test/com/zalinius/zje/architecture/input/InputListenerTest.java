package com.zalinius.zje.architecture.input;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zalinius.zje.architecture.input.actions.Inputtable;
import com.zalinius.zje.architecture.input.types.BinaryInput;
import com.zalinius.zje.physics.Point;

public class InputListenerTest {
	
	private InputListener inputListener;
	
	@BeforeEach
	void setup() {
		inputListener = new InputListener(() -> new Point());
	}
	
	@Test
	void keyPressed_whenPressed_activatesPressedCallbackFunction() throws Exception {
		InputSpy inputSpyA = new InputSpy(BinaryInput.KEY_A);
		inputListener.addInput(inputSpyA);
		
		KeyEvent keyEventA = makeDummyKeyEvent(BinaryInput.KEY_A);
		inputListener.keyPressed(keyEventA);
		
		assertTrue(inputSpyA.wasPressed());
		assertFalse(inputSpyA.wasReleased());
	}

	@Test
	void keyPressAndRelease_whenPressedAndReleased_activatesPressedAndReleasedCallbackFunctions() throws Exception {
		InputSpy inputSpyA = new InputSpy(BinaryInput.KEY_A);
		inputListener.addInput(inputSpyA);
		
		KeyEvent keyEventA = makeDummyKeyEvent(BinaryInput.KEY_A);
		inputListener.keyPressed(keyEventA);
		inputListener.keyReleased(keyEventA);
		
		assertTrue(inputSpyA.wasPressed());
		assertTrue(inputSpyA.wasReleased());
	}

	@Test
	void keyRelease_whenKeyOnlyReleased_doesNotActivateReleaseCallbackFunctions() throws Exception {
		InputSpy inputSpyA = new InputSpy(BinaryInput.KEY_A);
		inputListener.addInput(inputSpyA);
		
		KeyEvent keyEventA = makeDummyKeyEvent(BinaryInput.KEY_A);
		inputListener.keyReleased(keyEventA);
		
		assertFalse(inputSpyA.wasReleased());
	}

	@Test
	void keyRelease_whenKeyReleasedThenPressedThenReleased_activatesPressedAndReleasedCallbackFunctionsOnceEach() throws Exception {
		InputSpy inputSpyA = new InputSpy(BinaryInput.KEY_A);
		inputListener.addInput(inputSpyA);
		
		KeyEvent keyEventA = makeDummyKeyEvent(BinaryInput.KEY_A);
		inputListener.keyReleased(keyEventA);
		inputListener.keyPressed(keyEventA);
		inputListener.keyReleased(keyEventA);
		
		assertEquals(1, inputSpyA.getPresses());
		assertEquals(1, inputSpyA.getReleases());
	}

	

	
	private static KeyEvent makeDummyKeyEvent(BinaryInput key) {
		return new KeyEvent(new Canvas(), 0, 0L, 0, key.getKeyboardCode(),'0',0);
	}
	
	
	static class InputSpy implements Inputtable{
		
		private BinaryInput binaryInput;
		private int presses;
		private int releases;

		public InputSpy(BinaryInput binaryInput) {
			this.binaryInput = binaryInput;
			this.presses = 0;
			this.releases = 0;
		}
		
		
		@Override
		public void released() {
			this.releases++;
		}
		
		@Override
		public void pressed() {
			this.presses++;			
		}
		
		@Override
		public BinaryInput binaryInput() {
			return binaryInput;
		}
		
		
		public boolean wasPressed() {
			return presses > 0;
		}

		public boolean wasReleased() {
			return releases > 0;
		}

		public BinaryInput getBinaryInput() {
			return binaryInput;
		}


		public int getPresses() {
			return presses;
		}


		public int getReleases() {
			return releases;
		}
		
		

	}

}
