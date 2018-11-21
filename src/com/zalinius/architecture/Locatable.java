package com.zalinius.architecture;

import com.zalinius.physics.Point2D;

public interface Locatable {
	
	public Point2D center();
	
	public boolean facingRight();
	
	public double width();
	public double height();
}
