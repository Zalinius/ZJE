package com.zalinius.zje.architecture.input.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BinaryInputTest {
	
	
	@Test
	public void gamepadButtons_areButtons_notKeys() {
		BinaryInput aButton = BinaryInput.BUTTON_A;
		BinaryInput startButton = BinaryInput.BUTTON_START;
		BinaryInput leftBumper = BinaryInput.BUTTON_LEFT_BUMPER;
		
		assertTrue(aButton.isGamepadButton());
		assertFalse(aButton.isKeyboardKey());
		assertTrue(startButton.isGamepadButton());
		assertFalse(startButton.isKeyboardKey());
		assertTrue(leftBumper.isGamepadButton());
		assertFalse(leftBumper.isKeyboardKey());
	}

	@Test
	public void keyboardKeys_areKeys_notButtons() {
		BinaryInput aKey = BinaryInput.KEY_A;
		BinaryInput windowsKey = BinaryInput.KEY_WINDOWS;
		BinaryInput upArrowKey = BinaryInput.KEY_UP;
		
		assertTrue(aKey.isKeyboardKey());
		assertFalse(aKey.isGamepadButton());
		assertTrue(windowsKey.isKeyboardKey());
		assertFalse(windowsKey.isGamepadButton());
		assertTrue(upArrowKey.isKeyboardKey());
		assertFalse(upArrowKey.isGamepadButton());
	}

	@Test
	public void getKeyboardCode_onKeyBinaryInput_returnsAWTKeyboardCode() {
		BinaryInput aKey = BinaryInput.KEY_A;
		
		int aKeyCode = aKey.getKeyboardCode();
		
		assertEquals(65, aKeyCode);
	}

	@Test
	public void getGamePadCode_onButtonBinaryInput_returnsSDLButtonCode() {
		BinaryInput aButton = BinaryInput.BUTTON_A;
		
		int aButtonCode = aButton.getGamePadCode();
		
		assertEquals(0, aButtonCode);
	}
	
	@Test
	public void getKeyboardCode_onButtonBinaryInput_throwsBinaryInputException() {
		BinaryInput aButton = BinaryInput.BUTTON_A;
		
		assertThrows(BinaryInputException.class, () -> aButton.getKeyboardCode());
	}

	@Test
	public void getGamePadCode_onKeyBinaryInput_throwsBinaryInputException() {
		BinaryInput aKey = BinaryInput.KEY_A;
		
		assertThrows(BinaryInputException.class, () -> aKey.getGamePadCode());
	}
	
	@Test
	public void getButtonValueFromGamePadEvent_on0_returnsAButton() {
		int buttonEvent = 0;
		
		BinaryInput button = BinaryInput.getButtonValueFromGamepadEvent(buttonEvent);
		
		assertEquals(BinaryInput.BUTTON_A, button);
	}

}
