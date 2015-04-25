package tp.tools.visualisation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrameGeometric extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane tabbedPane;	
	
	public FrameGeometric (String name) {
		super(name);
		this.tabbedPane = new JTabbedPane(); 
		this.add(tabbedPane);
	}
	
	public void setFrame() {
		this.add(this.tabbedPane);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addView(View v1, String name) {
		JPanel panel = new JPanel();
		panel.add(v1);
		tabbedPane.add(name, panel);
	}

}
