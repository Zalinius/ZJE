package com.zalinius.architecture;

import com.zalinius.utilities.Position;

/**
 * For objects with colliders.
 */
public interface ICollidable {

    /**
     * @param point A point in 2D space.
     * @return Whether or not this object is overlapping the point.
     */
    public boolean isColliding(Position point);
}
