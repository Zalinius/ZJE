package com.darzalgames.zje.architecture.input.types;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.darzalgames.zje.architecture.input.actions.Inputtable;

public enum BinaryInput {
	KEY_A(KeyEvent.VK_A),
	KEY_B(KeyEvent.VK_B),
	KEY_C(KeyEvent.VK_C),
	KEY_D(KeyEvent.VK_D),
	KEY_E(KeyEvent.VK_E),
	KEY_F(KeyEvent.VK_F),
	KEY_G(KeyEvent.VK_G),
	KEY_H(KeyEvent.VK_H),
	KEY_I(KeyEvent.VK_I),
	KEY_J(KeyEvent.VK_J),
	KEY_K(KeyEvent.VK_K),
	KEY_L(KeyEvent.VK_L),
	KEY_M(KeyEvent.VK_M),
	KEY_N(KeyEvent.VK_N),
	KEY_O(KeyEvent.VK_O),
	KEY_P(KeyEvent.VK_P),
	KEY_Q(KeyEvent.VK_Q),
	KEY_R(KeyEvent.VK_R),
	KEY_S(KeyEvent.VK_S),
	KEY_T(KeyEvent.VK_T),
	KEY_U(KeyEvent.VK_U),
	KEY_V(KeyEvent.VK_V),
	KEY_W(KeyEvent.VK_W),
	KEY_X(KeyEvent.VK_X),
	KEY_Y(KeyEvent.VK_Y),
	KEY_Z(KeyEvent.VK_Z),

	KEY_0(KeyEvent.VK_0),
	KEY_1(KeyEvent.VK_1),
	KEY_2(KeyEvent.VK_2),
	KEY_3(KeyEvent.VK_3),
	KEY_4(KeyEvent.VK_4),
	KEY_5(KeyEvent.VK_5),
	KEY_6(KeyEvent.VK_6),
	KEY_7(KeyEvent.VK_7),
	KEY_8(KeyEvent.VK_8),
	KEY_9(KeyEvent.VK_9),

	KEY_COMMA(KeyEvent.VK_COMMA),
	KEY_PERIOD(KeyEvent.VK_PERIOD),
	KEY_SLASH(KeyEvent.VK_SLASH),
	KEY_SEMICOLON(KeyEvent.VK_SEMICOLON),
	KEY_QUOTE(KeyEvent.VK_QUOTE),
	KEY_BACKSLASH(KeyEvent.VK_BACK_SLASH),
	KEY_OPENBRACKET(KeyEvent.VK_OPEN_BRACKET),
	KEY_CLOSEBRACKET(KeyEvent.VK_CLOSE_BRACKET),
	KEY_MINUS(KeyEvent.VK_MINUS),
	KEY_EQUALS(KeyEvent.VK_EQUALS),
	KEY_BACKTICK(KeyEvent.VK_BACK_QUOTE),

	KEY_F1(KeyEvent.VK_F1),
	KEY_F2(KeyEvent.VK_F2),
	KEY_F3(KeyEvent.VK_F3),
	KEY_F4(KeyEvent.VK_F4),
	KEY_F5(KeyEvent.VK_F5),
	KEY_F6(KeyEvent.VK_F6),
	KEY_F7(KeyEvent.VK_F7),
	KEY_F8(KeyEvent.VK_F8),
	KEY_F9(KeyEvent.VK_F9),
	KEY_F10(KeyEvent.VK_F10),
	KEY_F11(KeyEvent.VK_F11),
	KEY_F12(KeyEvent.VK_F12),

	KEY_ESCAPE(KeyEvent.VK_ESCAPE),
	KEY_TAB(KeyEvent.VK_TAB),
	KEY_ENTER(KeyEvent.VK_ENTER),
	KEY_SPACE(KeyEvent.VK_SPACE),
	KEY_BACKSPACE(KeyEvent.VK_BACK_SPACE),

	KEY_SHIFT(KeyEvent.VK_SHIFT),
	KEY_CONTROL(KeyEvent.VK_CONTROL),
	KEY_ALT(KeyEvent.VK_ALT),

	KEY_UP(KeyEvent.VK_UP),
	KEY_DOWN(KeyEvent.VK_DOWN),
	KEY_LEFT(KeyEvent.VK_LEFT),
	KEY_RIGHT(KeyEvent.VK_RIGHT),

	KEY_WINDOWS(KeyEvent.VK_WINDOWS),

	;

	private final int code;

	BinaryInput(int code) {
		this.code = code;
	}

	public boolean isKeyboardKey() {
		return code < Inputtable.BUTTON_OFFSET;
	}

	public int getKeyboardCode() {
		if (isKeyboardKey()) {
			return code;
		} else {
			throw new BinaryInputException(name() + " is not a keyboard key");
		}
	}

	private static Map<Integer, BinaryInput> keyboardToEnumMap;

	public static BinaryInput getKeyboardValueFromKeyEvent(int keycode) {
		if (keyboardToEnumMap == null) {
			keyboardToEnumMap = new HashMap<>();
			for (int i = 0; i < BinaryInput.values().length; i++) {
				BinaryInput input = BinaryInput.values()[i];
				if (input.isKeyboardKey()) {
					keyboardToEnumMap.put(input.getKeyboardCode(), input);
				}
			}
		}

		return keyboardToEnumMap.get(keycode);
	}

}
