package com.darzalgames.zje.plugins.camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.darzalgames.zje.math.Interpolation;
import com.darzalgames.zje.physics.Locatable;
import com.darzalgames.zje.physics.Point;
import com.darzalgames.zje.physics.Vector;
import com.darzalgames.zje.plugins.configuration.ScreenStrategy;

/**
 * A graphics plugin that aligns the camera with an object, which follows behind based on speed
 */
public class GentleFollowCam extends AbstractCamera{
	
	private Point cameraInterpolation;
	
	public GentleFollowCam(Locatable target, ScreenStrategy screenStrategy) {
		super(target, screenStrategy);
		cameraInterpolation = target.position();
	}
	
	@Override
	public void updateBefore(double delta) {
		double cameraSpeed = 2;
		cameraInterpolation = Interpolation.linearInterpolation(cameraInterpolation, target(), delta * cameraSpeed);
	}


	@Override
	public void renderBefore(Graphics2D g) {
		Point cameraPosition = Point.subtract(reflectCameraOffTarget(),getGameScreenCenter());
		AffineTransform trans = new AffineTransform(1, 0, 0, 1, -cameraPosition.x, - cameraPosition.y);
		g.transform(trans);
	}

	private Point reflectCameraOffTarget() {
		Vector cameraToTarget = new Vector(cameraInterpolation, target());
		return target().add(cameraToTarget);
	}

	@Override
	public Point position() {
		return reflectCameraOffTarget();
	}
}
