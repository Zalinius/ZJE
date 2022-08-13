package com.zalinius.zje.architecture;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.zalinius.zje.architecture.input.InputListener;
import com.zalinius.zje.architecture.input.RumbleListener;
import com.zalinius.zje.architecture.input.actions.Axisable;
import com.zalinius.zje.architecture.input.actions.Clickable;
import com.zalinius.zje.architecture.input.actions.Inputtable;
import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.physics.Point;
import com.zalinius.zje.plugins.RuntimePlugin;

public class GameStage extends DoubleBufferedFrame{
	private static final long serialVersionUID = 1L;
	private Graphical graphics;
	private InputListener input;

	private List<RuntimePlugin> plugins;
	
	private double scale;

	public GameStage(Graphical graphics, String windowText, int width, int height) {
		super(windowText);
		this.graphics = graphics;
		setResizable(true);
		setSize(width, height);
		setPreferredSize(getSize());
		scale = 1;
		addWindowListener(defaultCloseAction());
		this.input = new InputListener(screenSize());
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		this.plugins = new ArrayList<>();
	}

	public void paintBuffer(Graphics2D g){
		g.setTransform(new AffineTransform());
		plugins.forEach( plugin -> plugin.renderBefore(g));	
		graphics.render(g);
		plugins.forEach( plugin -> plugin.renderAfter(g));		
	}

	public void addKeys(Collection<Inputtable> keys, Collection<Clickable> clicks, Collection<Axisable> axes){
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
		if(input == null) {
			input = new InputListener(screenSize());
		}
		input.addInput(keyInput);
	}
	public void addInput(Clickable mouseInput) {
		if(input == null) {
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
	
	public RumbleListener rumbleListener() {
		return input;
	}
	
	public Logical getInputPolling() {
		return input;
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}
	public double getScale() {
		return scale;
	}
}
