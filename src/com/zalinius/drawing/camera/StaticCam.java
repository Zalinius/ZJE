package com.zalinius.drawing.camera;

import java.awt.geom.AffineTransform;

public class StaticCam implements Camerable {

	public StaticCam() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AffineTransform getTransform() {
		// TODO Auto-generated method stub
		return new AffineTransform();
	}

}
