package com.zalinius.zje.music.pitch;

import java.util.Iterator;

public class Melody implements Iterator<AbsolutePitch> {

	private int root;
	private EightPitchScale scale;
	private IndexStrategy strategy;
	
	
	public Melody(AbsolutePitch root, EightPitchScale scale, IndexStrategy strategy) {
		this.root = root.midiPitch();
		this.scale = scale;
		this.strategy = strategy;
	}
	
	public void setScale(EightPitchScale newScale) {
		this.scale = newScale;
	}
	
	public void setRoot(int root) {
		this.root = root;
	}
	
	
	@Override
	public boolean hasNext() {
		return true;
	}
	@Override
	public AbsolutePitch next() {
		return scale.nth(strategy.nextIndex()).absoluteNote(root);
	}
	
}
