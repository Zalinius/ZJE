package com.zalinius.drawing.camera;

import com.zalinius.architecture.Locatable;
import com.zalinius.physics.Point2D;

import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

public class FollowCam implements Camerable{

	private Locatable loc;
	public FollowCam(Locatable loc) {
		this.loc = loc;
	}

	@Override
	public Affine getTransform(Point2D canvasSize) {
		Affine af =	new Affine();
		af.append(Transform.translate(-loc.center().x + canvasSize.x/2, -loc.center().y + canvasSize.y/2));
		return af;
	}

}
