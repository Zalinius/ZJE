package com.darzalgames.zje.physics;


public interface Mobile extends Locatable{
	public Vector velocity();
	public default double speed() { return velocity().length(); }
}
