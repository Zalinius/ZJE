package com.darzalgames.zje.utilities.input;

import com.darzalgames.zje.physics.Vector;

public interface Directional {
	/**
	 * Get a vector representing a 2D direction, or no direction at all (zero vector)
	 * @return A vector whose length is within [0.0, 1.0]
	 */
	public Vector direction();
}
