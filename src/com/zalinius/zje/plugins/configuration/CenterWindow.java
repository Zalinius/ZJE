package com.zalinius.zje.plugins.configuration;

import com.zalinius.zje.architecture.DoubleBufferedFrame;
import com.zalinius.zje.plugins.RenderSettings;

public class CenterWindow extends RenderSettings{

	@Override
	public void applySettings(DoubleBufferedFrame frame) {
		frame.setLocationRelativeTo(null); //A null argument centers the screen
	}
}
