package com.zalinius.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ZMathTest {

	@Test
	void clampTest() {
		assertEquals(10, ZMath.clamp(10, 5, 15));
	}

}
