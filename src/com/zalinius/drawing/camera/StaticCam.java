package com.zalinius.drawing.camera;

import javafx.scene.transform.Affine;

public class StaticCam implements Camerable {

	public StaticCam() {
	}

	@Override
	public Affine getTransform() {
		return new Affine();
	}

}
