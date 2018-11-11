package com.zalinius.architecture;

import java.awt.*;

public interface GameObject extends Graphical, Logical{

    /**
     * Called every frame. Game logic goes here.
     * @param delta The time increment since the last frame, in seconds.
     */
    public void update(double delta);

    /**
     * Called every frame. Anything drawn to the screen goes here.
     * @param g The "screen".
     */
    public void render(Graphics2D g);
}
