package com.darzalgames.zje.architecture;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.*;

import com.darzalgames.zje.architecture.input.InputListener;
import com.darzalgames.zje.architecture.input.actions.Axisable;
import com.darzalgames.zje.architecture.input.actions.Clickable;
import com.darzalgames.zje.architecture.input.actions.Inputtable;
import com.darzalgames.zje.physics.Locatable;
import com.darzalgames.zje.physics.Point;
import com.darzalgames.zje.plugins.RuntimePlugin;

public class GameStage extends DoubleBufferedFrame {
	private static final long serialVersionUID = 1L;
	private final Graphical graphics;
	private InputListener input;

	private final List<RuntimePlugin> plugins;

	private final int originalWidth;
	private final int originalHeight;

	private double scale;

	public GameStage(Graphical graphics, String windowText, int width, int height) {
		super(windowText);
		this.graphics = graphics;
		setResizable(true);
		setSize(width, height);
		originalWidth = width;
		originalHeight = height;
		setLocationRelativeTo(null);
		setPreferredSize(getSize());
		scale = 1;
		addWindowListener(defaultCloseAction());
		input = new InputListener(screenSize());
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		plugins = new ArrayList<>();
	}

	@Override
	public void paintBuffer(Graphics2D g) {
		updateScale();
		AffineTransform scaling = new AffineTransform();
		scaling.setToScale(scale, scale);
		g.setTransform(scaling);
		plugins.forEach(plugin -> plugin.renderBefore(g));
		graphics.render(g);
		plugins.forEach(plugin -> plugin.renderAfter(g));
	}

	public void addKeys(Collection<Inputtable> keys, Collection<Clickable> clicks, Collection<Axisable> axes) {
		for (Iterator<Clickable> it = clicks.iterator(); it.hasNext();) {
			Clickable clickable = it.next();
			input.addInput(clickable);

		}
		for (Iterator<Inputtable> it = keys.iterator(); it.hasNext();) {
			Inputtable inputtable = it.next();
			input.addInput(inputtable);
		}
		for (Iterator<Axisable> it = axes.iterator(); it.hasNext();) {
			Axisable axisable = it.next();
			input.addInput(axisable);
		}
	}

	public void resetClickables(Collection<Clickable> newClickables) {
		input.resetClickables(newClickables);
	}

	public void moveWindow(int x, int y) {
		setBounds(x, y, getWidth(), getHeight());
	}

	public WindowAdapter defaultCloseAction() {
		return new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		};
	}

	public void addInput(Inputtable keyInput) {
		if (input == null) {
			input = new InputListener(screenSize());
		}
		input.addInput(keyInput);
	}

	public void addInput(Clickable mouseInput) {
		if (input == null) {
			input = new InputListener(screenSize());
		}
		input.addInput(mouseInput);
	}

	public Locatable screenSize() {
		return () -> {
			Dimension d = getSize();
			return new Point(d.getWidth(), d.getHeight());
		};
	}

	public void accept(RuntimePlugin runtimePlugin) {
		plugins.add(runtimePlugin);
	}

	public Logical getInputPolling() {
		return input;
	}

	private void updateScale() {
		double originalRatio = originalWidth / (double) originalHeight;
		Dimension size = getSize();
		double currentRatio = size.getWidth() / size.getHeight();
		if (originalRatio > currentRatio) {
			scale = size.getWidth() / originalWidth;
		} else {
			scale = size.getHeight() / originalHeight;
		}

		input.setScale(scale);

	}

}
