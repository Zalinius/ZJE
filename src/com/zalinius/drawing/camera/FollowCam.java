package com.zalinius.drawing.camera;

import com.zalinius.architecture.GameStage;
import com.zalinius.architecture.Locatable;

import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

public class FollowCam implements Camerable{

	private Locatable loc;
	public FollowCam(Locatable loc) {
		this.loc = loc;
	}

	@Override
	public Affine getTransform() {
		Affine af = new Affine();
		//Transform.translate(-loc.center().x + GameStage.GAME_WIDTH/2, -loc.center().y + GameStage.GAME_HEIGHT/2);
		return af;
	}

}
