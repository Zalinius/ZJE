package com.zalinius.drawing.camera;

import com.zalinius.physics.Point2D;

import javafx.scene.transform.Affine;

public interface Camerable {
	
	public Affine getTransform(Point2D canvasSize);

}
