package com.zalinius.drawing.camera;

import com.zalinius.physics.Point2D;

import javafx.scene.transform.Affine;

public class StaticCam implements Camerable {

	public StaticCam() {
	}

	@Override
	public Affine getTransform(Point2D canvasSize) {
		return new Affine();
	}

}
