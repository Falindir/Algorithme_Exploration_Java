package tp.tools;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class View extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int width; 
	private int height;
	
	
	public View(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		setBackground(ColorTools.BACKGROUND);
	}
	
	

}
