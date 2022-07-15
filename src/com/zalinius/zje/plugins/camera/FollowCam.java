package com.zalinius.zje.plugins.camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.physics.Point;
import com.zalinius.zje.plugins.configuration.ScreenStrategy;

/**
 * A Simple graphics plugin that aligns the camera with an object
 */
public class FollowCam extends AbstractCamera{
		
	public FollowCam(Locatable target, ScreenStrategy screenStrategy) {
		super(target, screenStrategy);
	}

	@Override
	public void renderBefore(Graphics2D g) {
		Point cameraPosition = position();
		AffineTransform trans = AffineTransform.getTranslateInstance(-cameraPosition.x, - cameraPosition.y);
		g.transform(trans);
	}

	@Override
	public Point position() {
		return Point.subtract(target(), getGameScreenCenter());
	}

}
