package com.zalinius.drawing.camera;

import java.awt.geom.AffineTransform;

import com.zalinius.architecture.GameStage;
import com.zalinius.architecture.Locatable;

public class FollowCam implements Camerable{

	Locatable loc;
	public FollowCam(Locatable loc) {
		this.loc = loc;
	}

	@Override
	public AffineTransform getTransform() {
		AffineTransform af = new AffineTransform();
		af.translate(-loc.center().x() + GameStage.GAME_WIDTH/2, -loc.center().y() + GameStage.GAME_HEIGHT/2);
		return af;
	}

}
