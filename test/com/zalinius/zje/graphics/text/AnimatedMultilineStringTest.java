package com.zalinius.zje.graphics.text;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import com.zalinius.darzalcommon.functional.Do;
import com.zalinius.zje.physics.Point;

public class AnimatedMultilineStringTest {
	
	@Test
	void multiLineString_whenFinishedAnimating_callsCallbackRunnable() throws Exception {
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		Runnable runnable = () -> atomicBoolean.set(true);
		AnimatedMultilineString animatedMultilineString = new AnimatedMultilineString(new Point(), "test\nl2", 0.2, runnable);
		
		Do.xTimes(70, () -> animatedMultilineString.update(0.07));
		
		assertTrue(atomicBoolean.get());
	}

	@Test
	void multiLineString_whenFinishedAnimating_callsCallbackRunnableASingleTime() throws Exception {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		Runnable runnable = () -> atomicInteger.incrementAndGet();
		AnimatedMultilineString animatedMultilineString = new AnimatedMultilineString(new Point(), "test\nl2", 0.2, runnable);
		
		Do.xTimes(70, () -> animatedMultilineString.update(0.07));
		
		assertEquals(1, atomicInteger.get());
	}

}
