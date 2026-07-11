package com.darzalgames.zje.plugins.configuration;

import java.awt.Dimension;
import java.awt.event.ComponentListener;

import com.darzalgames.zje.physics.Point;
import com.darzalgames.zje.plugins.RuntimePlugin;

public abstract class ScreenStrategy extends RuntimePlugin implements ComponentListener {

	public abstract Point getGameSize(Dimension currentScreenSize);
}
