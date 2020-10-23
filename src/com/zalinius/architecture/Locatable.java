package com.zalinius.architecture;

import com.zalinius.physics.Point;

public interface Locatable {
	
	public Point center();
	
	public boolean facingRight();
	
	public double width();
	public double height();
}
