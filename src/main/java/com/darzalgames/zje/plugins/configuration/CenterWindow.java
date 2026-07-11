package com.darzalgames.zje.plugins.configuration;

import com.darzalgames.zje.architecture.DoubleBufferedFrame;
import com.darzalgames.zje.plugins.RenderSettings;

public class CenterWindow extends RenderSettings{

	@Override
	public void applySettings(DoubleBufferedFrame frame) {
		frame.setLocationRelativeTo(null); //A null argument centers the screen
	}
}
