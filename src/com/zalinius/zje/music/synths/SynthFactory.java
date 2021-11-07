package com.zalinius.zje.music.synths;

import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.data.BufferFactory;

public class SynthFactory {
	private SynthFactory() {}

	public static final int SMALL_EVEN_POWER = 2;
	public static final int SMALL_ODD_POWER  = 3;
	public static final int LARGE_EVEN_POWER = 20;

	public static final Buffer WIND_SOFT = Buffer.SINE;
	public static final Buffer WIND_GLASS = makeBuffer(sinePower(SMALL_ODD_POWER),"Glass");

	public static final Buffer SAW_SOFT = makeBuffer(absoluteSine(),"Soft Saw");
	public static final Buffer SAW_STRONG = makeBuffer(halfSquare(),"Half Saw");
	public static final Buffer SAW_SHARP = makeBuffer(halfSaw(),"Half Square");

	public static final Buffer RESONANT = makeBuffer(sinePower(SMALL_EVEN_POWER),"Resonant");
	public static final Buffer RESONANT_STRONG = makeBuffer(sinePower(LARGE_EVEN_POWER),"Resonant Ultra");
	public static final Buffer RESONANT_SHARP = Buffer.TRIANGLE;

	public static final Buffer STRING_SOFT = makeBuffer(normalizer(sinWobble()),"Soft String");
	public static final Buffer STRING_STRONG = makeBuffer(normalizer(sinEnvelope()),"Deep String");
	public static final Buffer STRING_SHARP = makeBuffer(squareSine(),"Sharp String");

	public static Buffer makeBuffer(WaveFunction function, String name) {
		BufferFactory factory = new BufferFactory() {

			@Override
			public String getName() {
				return name;
			}

			@Override
			public Buffer generateBuffer(int bufferSize) {
				Buffer b = new Buffer(bufferSize);
				for(int i = 0; i < bufferSize; i++) {
					b.buf[i] = function.value(i, bufferSize);
				}
				return b;
			}
		};

		return factory.getDefault();
	}


	private static WaveFunctionFractional sinEnvelope() {
		return fraction -> Math.sin(fraction/2)*Math.pow(Math.cos(2*fraction),7);
	}

	private static WaveFunctionFractional sinWobble() {
		return fraction -> Math.sin(fraction/2) * Math.sin(2*fraction);
	}

	private static WaveFunction squareSine() {
		return (i, max) -> Buffer.SINE.getValueIndex(3*i) *Buffer.SQUARE.getValueIndex(i);
	}

	public static WaveFunction absoluteSine() {
		return (i, max) -> (float)Math.sin(Math.PI*i / max);
	}

	public static WaveFunction halfSaw() {
		return (i, max) -> Buffer.SAW.getValueIndex(i)/4;
	}
	public static WaveFunction halfSquare() {
		return (i, max) -> Buffer.SQUARE.getValueIndex(i)/4;
	}

	public static WaveFunction sinePower(double power) {
		if(power < 1) {
			power = 1;
		}
		final double trueLevel=power;

		return (i, max) ->{
			double sineValue = Buffer.SINE.getValueIndex(i);
			if(sineValue > 0d) {
				return (float) Math.pow(sineValue,trueLevel);
			}
			else {
				return (float) -Math.pow(Math.abs(sineValue),trueLevel);
			}
		};

	}

	public static WaveFunction normalizer(WaveFunctionFractional other) {
		return (i, max) -> (float) other.value(Math.PI*2.0*i / max);
	}

}
