package com.darzalgames.zje.architecture.input;

import java.awt.event.*;
import java.util.*;

import com.darzalgames.zje.architecture.Logical;
import com.darzalgames.zje.architecture.input.actions.Axisable;
import com.darzalgames.zje.architecture.input.actions.Clickable;
import com.darzalgames.zje.architecture.input.actions.Inputtable;
import com.darzalgames.zje.architecture.input.types.AxialInput;
import com.darzalgames.zje.architecture.input.types.BinaryInput;
import com.darzalgames.zje.architecture.input.types.MouseInput;
import com.darzalgames.zje.physics.Locatable;
import com.darzalgames.zje.physics.Point;

public class InputListener extends KeyAdapter implements MouseListener, MouseMotionListener, Locatable, Logical {

	private final Map<BinaryInput, Inputtable> binaryInputs;
	private final Map<BinaryInput, Boolean> binaryInputStates; // pressed or not

	private final Map<MouseInput, Collection<Clickable>> mouseInputs;
	private final Map<Clickable, Boolean> mouseAreasStatus;

	private final Map<AxialInput, Collection<Axisable>> mouseAxesInputs;

	private int mouseX;
	private int mouseY;
	private double scale;

	private final Locatable screenSize;

	public InputListener(Locatable screenSize) {
		this.screenSize = screenSize;
		binaryInputs = new EnumMap<>(BinaryInput.class);
		binaryInputStates = new EnumMap<>(BinaryInput.class);

		mouseInputs = new EnumMap<>(MouseInput.class);
		mouseAreasStatus = new HashMap<>();
		mouseAxesInputs = new EnumMap<>(AxialInput.class);
		mouseAxesInputs.put(AxialInput.MOUSE_X, new ArrayList<>());
		mouseAxesInputs.put(AxialInput.MOUSE_Y, new ArrayList<>());

		mouseX = mouseY = 0;
		scale = 1.0;
	}

	public void addInput(Axisable axis) {
		if (!mouseAxesInputs.containsKey(axis.axialInput())) {
			mouseAxesInputs.put(axis.axialInput(), new ArrayList<>());
		}

		mouseAxesInputs.get(axis.axialInput()).add(axis);
	}

	public void addInput(Inputtable binaryInput) {
		binaryInputs.put(binaryInput.binaryInput(), binaryInput);
		binaryInputStates.put(binaryInput.binaryInput(), false);
	}

	public void addInput(Clickable click) {
		if (!mouseInputs.containsKey(click.mouseButton())) {
			mouseInputs.put(click.mouseButton(), new ArrayList<>());
		}
		mouseInputs.get(click.mouseButton()).add(click);

		mouseAreasStatus.put(click, click.clickArea().contains(mouseX, mouseY));
	}

	public void resetClickables(Collection<Clickable> newClickables) {
		mouseInputs.clear();
		mouseAreasStatus.clear();

		newClickables.forEach(this::addInput);
	}

	private void keyPressSwitchBoard(int keycode, boolean press) {
		BinaryInput keyboardInput = BinaryInput.getKeyboardValueFromKeyEvent(keycode);
		if (binaryInputs.containsKey(keyboardInput)) {
			if (press) {
				if (Boolean.FALSE.equals(binaryInputStates.get(keyboardInput))) {
					Inputtable input = binaryInputs.get(keyboardInput);
					input.pressed();
					binaryInputStates.put(keyboardInput, true);
				}
			} else if (Boolean.TRUE.equals(binaryInputStates.get(keyboardInput))) {
				Inputtable input = binaryInputs.get(keyboardInput);
				input.released();
				binaryInputStates.put(keyboardInput, false);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyPressSwitchBoard(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressSwitchBoard(e.getKeyCode(), false);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		MouseInput input = MouseInput.getMouseInput(event.getButton());
		if (mouseInputs.containsKey(input)) {
			Point mouse = new Point(event.getPoint());
			mouseX = (int) (mouse.x / scale);
			mouseY = (int) (mouse.y / scale);

			Collection<Clickable> copy = new ArrayList<>(mouseInputs.get(input));

			for (Clickable clickable : copy) {
				if (clickable.clickArea().contains(mouseX, mouseY)) {
					clickable.mouseClicked();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) { /* Purposefully empty, as no swing components */}

	@Override
	public void mouseExited(MouseEvent arg0) { /* Purposefully empty, as no swing components */ }

	@Override
	public void mousePressed(MouseEvent event) {
		MouseInput input = MouseInput.getMouseInput(event.getButton());

		if (mouseInputs.containsKey(input)) {
			Point mouse = new Point(event.getPoint());
			mouseX = (int) (mouse.x / scale);
			mouseY = (int) (mouse.y / scale);
			for (Clickable clickable : mouseInputs.get(input)) {
				if (clickable.clickArea().contains(mouseX, mouseY)) {
					clickable.mousePressed();
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		MouseInput input = MouseInput.getMouseInput(event.getButton());

		if (mouseInputs.containsKey(input)) {
			for (Clickable clickable : mouseInputs.get(input)) {
				clickable.mouseReleased();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point mouse = new Point(e.getPoint());
		mouseX = (int) (mouse.x / scale);
		mouseY = (int) (mouse.y / scale);

		mouseAxesInputs.get(AxialInput.MOUSE_X).forEach(mouseXInput -> mouseXInput.axisMoved((float) (mouse.x / screenSize.x())));
		mouseAxesInputs.get(AxialInput.MOUSE_Y).forEach(mouseYInput -> mouseYInput.axisMoved((float) (mouse.y / screenSize.y())));

		for (Iterator<Clickable> it = mouseAreasStatus.keySet().iterator(); it.hasNext();) {
			Clickable clickable = it.next();
			if (Boolean.TRUE.equals(mouseAreasStatus.get(clickable))) {
				// Area was already entered
				if (!clickable.clickArea().contains(mouseX, mouseY)) {
					mouseAreasStatus.put(clickable, false);
					clickable.mouseLeft();
				}
			} else if (clickable.clickArea().contains(mouseX, mouseY)) {
				mouseAreasStatus.put(clickable, true);
				clickable.mouseEntered();
			}
		}
	}

	@Override
	public Point position() {
		return new Point(mouseX / scale, mouseY / scale);
	}

	@Override
	public void update(double delta) {}

	public void setScale(double scale) {
		this.scale = scale;
	}

}
