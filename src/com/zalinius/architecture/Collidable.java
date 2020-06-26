package com.zalinius.architecture;

import com.zalinius.geometry.Shape;

/**
 * For objects with colliders.
 */
public interface Collidable {

    /**
     * @param point A point in 2D space.
     * @return Whether or not this object is overlapping the point.
     */
    public Shape getCollisionBox();
}
