package com.zalinius.zje.architecture;

import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.zalinius.zje.architecture.input.Clickable;
import com.zalinius.zje.architecture.input.Inputtable;
import com.zalinius.zje.plugins.Plugin;

public class GameStage extends DoubleBufferedFrame{
	private static final long serialVersionUID = 1L;
	private Graphical graphics;
	private static InputListener input;

	private List<Plugin> plugins;

	public GameStage(Graphical graphics, String windowText, int width, int height, List<Plugin> plugins) {
		super(windowText);
		this.graphics = graphics;
		setResizable(false);
		setSize(width, height);
		addWindowListener(defaultCloseAction());
		addKeyListener(getInput());
		addMouseListener(getInput());
		addMouseMotionListener(getInput());
		this.plugins = plugins;
	}

	public void paintBuffer(Graphics2D g){
		plugins.forEach((plugin) -> plugin.renderBefore(g));		
		graphics.render(g);
		plugins.forEach((plugin) -> plugin.renderAfter(g));		
	}

	public void addKeys(Collection<Inputtable> keys, Collection<Clickable> clicks){
		for (Iterator<Clickable> it = clicks.iterator(); it.hasNext();) {
			Clickable clickable = it.next();
			input.addInput(clickable);

		}
		for (Iterator<Inputtable> it = keys.iterator(); it.hasNext();) {
			Inputtable inputtable = it.next();
			input.addInput(inputtable);
		}
	}

	public void moveWindow(int x, int y) {
		setBounds(x, y, getWidth(), getHeight());
	}

	public WindowAdapter defaultCloseAction() {
		return new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		};
	}


	public static boolean isHeld(int keyCode) {
		return input.isHeldDown(keyCode);
	}


	public static void addInput(Inputtable keyInput) {
		if(input == null) {
			input = new InputListener();
		}
		input.addInput(keyInput);
	}
	public static void addInput(Clickable mouseInput) {
		if(input == null) {
			input = new InputListener();
		}
		input.addInput(mouseInput);
	}

	private static InputListener getInput() {
		if(input == null) {
			input = new InputListener();
		}

		return input;
	}

	public Locatable mouseLocator() {
		return input;
	}
}
