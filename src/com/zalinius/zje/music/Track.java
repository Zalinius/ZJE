package com.zalinius.zje.music;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.Bead;
import net.beadsproject.beads.ugens.Clock;
import net.beadsproject.beads.ugens.Static;

//A note provider that combines rhythm and pitch
public abstract class Track extends Bead{
	
	protected final AudioContext ac;
	protected final Clock clock;
	
	protected Track(double initialBeatLength) {
		ac = AudioContext.getDefaultContext();
		clock = new Clock(ac, (float) initialBeatLength);
		clock.addMessageListener(this);
		ac.out.addDependent(clock);
		ac.start();
	}
	
	@Override
	public void start() {
		clock.start();
		super.start();
	}
	
	@Override
	public void pause(boolean paused) {
		clock.pause(paused);
		super.pause(paused);
	}
	
	@Override
	public void kill() {
		clock.kill();
		super.kill();
	}

	
	@Override
	protected void messageReceived(Bead message) {
		Clock c = (Clock)message;
		c.setIntervalEnvelope(new Static((float) getBeatLength()));
		update(c.getBeatCount(), c.getCount());
	}
	
	protected abstract void update(int beats, long ticks);
	
	
	public abstract double getBeatLength();
}
