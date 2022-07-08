package com.zalinius.zje.plugins.configuration;

import java.awt.Dimension;
import java.awt.event.ComponentListener;

import com.zalinius.zje.physics.Point;
import com.zalinius.zje.plugins.RuntimePlugin;

public abstract class ScreenStrategy extends RuntimePlugin implements ComponentListener {

	public abstract Point getGameSize(Dimension currentScreenSize);
}
