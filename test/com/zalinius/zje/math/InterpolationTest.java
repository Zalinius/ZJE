package com.zalinius.zje.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InterpolationTest {
	
	public final double ERROR_MARGIN = 1E-12;
	
	@Test
	void linearInterpolation_0interpolant_returnsStartValue() {
		double start = 5.2;
		double end   = 4.5;
		double interpolantZero = 0;
		
		double interpolatedResult = Interpolation.linearInterpolation(start, end, interpolantZero);
		
		assertEquals(start, interpolatedResult, ERROR_MARGIN);	
	}
	@Test
	void linearInterpolation_1interpolant_returnsEndValue() {
		double start = -.2;
		double end   = 7.5;
		double interpolantOne = 1;
		
		double interpolatedResult = Interpolation.linearInterpolation(start, end, interpolantOne);
		
		assertEquals(end, interpolatedResult, ERROR_MARGIN);	
	}
	@Test
	void linearInterpolation_halfInterpolant_returnsAverage() {
		double start = -.2;
		double end   = 7.5;
		double interpolant = 0.5;
		
		double expected = (start + end) / 2;	
		double interpolatedResult = Interpolation.linearInterpolation(start, end, interpolant);
		
		assertEquals(expected, interpolatedResult, ERROR_MARGIN);	
	}
	
	
	@Test
	void linearInterpolant_startValue_returns0() {
		double start = 5.2;
		double end   = 4.5;
		
		double expectedInterpolant = 0;
		double resultInterpolant = Interpolation.linearInterpolant(start, end, start);
		
		assertEquals(expectedInterpolant, resultInterpolant, ERROR_MARGIN);	
	}
	@Test
	void linearInterpolant_endValue_returns1() {
		double start = 5.2;
		double end   = 4.5;
		
		double expectedInterpolant = 1;
		double resultInterpolant = Interpolation.linearInterpolant(start, end, end);
		
		assertEquals(expectedInterpolant, resultInterpolant, ERROR_MARGIN);	
	}
	@Test
	void linearInterpolation_startEndAverage_returnsHalf() {
		double start = -.2;
		double end   = 7.5;
		double value = (start + end) / 2;

		double expectedInterpolant = 0.5;
		double resultInterpolant = Interpolation.linearInterpolant(start, end, value);
		
		assertEquals(expectedInterpolant, resultInterpolant, ERROR_MARGIN);	
	}


	@Test
	void linearMapping_valueStart_resturnResultStart() {
		double valueStart = -4.2;
		double valueEnd   = 6.8;
		double resultStart = -4.2;
		double resultEnd   = 6.8;
		
		double expectedMapping = resultStart;
		double resultMapping = Interpolation.linearMapping(valueStart, valueEnd, valueStart, resultStart, resultEnd);
		
		assertEquals(expectedMapping, resultMapping, ERROR_MARGIN);	
	}
	@Test
	void linearMapping_valueEnd_resturnResultEnd() {
		double valueStart = -4.2;
		double valueEnd   = 6.8;
		double resultStart = -4.2;
		double resultEnd   = 6.8;
		
		double expectedMapping = resultEnd;
		double resultMapping = Interpolation.linearMapping(valueStart, valueEnd, valueEnd, resultStart, resultEnd);
		
		assertEquals(expectedMapping, resultMapping, ERROR_MARGIN);	
	}
	@Test
	void linearMapping_valueMidpoint_resturnResultMidpoint() {
		double valueStart = -4.2;
		double valueEnd   = 6.8;
		double resultStart = -4.2;
		double resultEnd   = 6.8;
		double value = (valueStart+valueEnd)/2;
		
		double expectedMapping = (resultStart+resultEnd)/2;
		double resultMapping = Interpolation.linearMapping(valueStart, valueEnd, value, resultStart, resultEnd);
		
		assertEquals(expectedMapping, resultMapping, ERROR_MARGIN);	
	}


	@Test
	void cosineInterpolation_0Interpolant_returnsStart() {
		double start = 5.2;
		double end   = 4.5;
		double interpolantZero = 0;
		
		double interpolatedResult = Interpolation.cosineInterpolation(start, end, interpolantZero);
		
		assertEquals(start, interpolatedResult, ERROR_MARGIN);	
	}
	@Test
	void cosineInterpolation_1Interpolant_returnsEnd() {
		double start = 5.2;
		double end   = 4.5;
		double interpolantOne = 1;
		
		double interpolatedResult = Interpolation.cosineInterpolation(start, end, interpolantOne);
		
		assertEquals(end, interpolatedResult, ERROR_MARGIN);	
	}
	@Test
	void cosineInterpolation_halfInterpolant_returnsAverage() {
		double start = 5.2;
		double end   = 4.5;
		double interpolantHalf = 0.5;
		
		double interpolatedExpected = (start + end)/2;
		double interpolatedResult = Interpolation.cosineInterpolation(start, end, interpolantHalf);
		
		assertEquals(interpolatedExpected, interpolatedResult, ERROR_MARGIN);	
	}
	@Test
	void cosineInterpolation_quarterInterpolant_returnsCorrectValue() {
		double start = 0;
		double end   = 2;
		double interpolant = 0.25;
		
		double interpolatedExpected = 1 + Math.cos(Math.PI*3/4);
		double interpolatedResult = Interpolation.cosineInterpolation(start, end, interpolant);
		
		assertEquals(interpolatedExpected, interpolatedResult, ERROR_MARGIN);	
	}

}
