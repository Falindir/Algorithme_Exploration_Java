package tp.tools.visualisation;

import tp.tools.others.ColorTools;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class View extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int width; 
	private int height;

	private Controller controller;

	public View(int width, int height, Controller controller) {
		this.width = width;
		this.height = height;
		this.controller = controller;

		setPreferredSize(new Dimension(width, height));
		setBackground(ColorTools.BACKGROUND);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
