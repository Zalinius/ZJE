package com.darzalgames.zje.physics;

public interface Locatable {	
	public Point position();
	public default double x() {return position().x;}
	public default double y() {return position().y;}

}
