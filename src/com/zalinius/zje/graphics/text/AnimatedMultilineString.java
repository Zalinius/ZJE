package com.zalinius.zje.graphics.text;

import java.awt.Graphics2D;

import com.zalinius.darzalcommon.functional.Runnables;
import com.zalinius.zje.architecture.GameObject;
import com.zalinius.zje.physics.Point;

public class AnimatedMultilineString implements GameObject{

	private final String text;
	private final MultilineString textDisplay;
	private final double characterPeriod;
	private final Runnable animationCompleteRunnable;
	
	private double time;
	private int charCount;

	public AnimatedMultilineString(Point center, String text, double characterPeriod) {
		this(center, text, characterPeriod, Runnables.nullRunnable());
	}
	public AnimatedMultilineString(Point center, String text, double characterPeriod, Runnable animationCompleteRunnable) {
		this.text = text;
		this.textDisplay = new MultilineString(center, "");
		this.characterPeriod = characterPeriod;
		this.animationCompleteRunnable = animationCompleteRunnable;
		
		this.time = 0;
		this.charCount = 0;
	}
	
	@Override
	public void update(double delta) {
		time += delta;
		
		if(time >= characterPeriod && charCount < text.length()) {
			time -= characterPeriod;
			
			charCount++;
			
			textDisplay.setTextString(text.substring(0, charCount));
			
			if(allTextDisplayed()) {
				animationCompleteRunnable.run();
			}
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		textDisplay.render(g);
	}
	
	
	public boolean allTextDisplayed() {
		return charCount == text.length();
	}
	

}
