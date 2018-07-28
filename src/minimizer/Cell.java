package minimizer;

import java.awt.Dimension;

import javax.swing.JLabel;

public class Cell extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cell() {
		super();
		setVerticalAlignment(CENTER);
		setHorizontalAlignment(CENTER);
	}
	
	public Cell(String str) {
		this();
		setText(str);
	}
	
	public Cell(KMAPVAL val) {
		this();
		setText(val.toString());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (super.getPreferredSize().getWidth() * 1.2), (int) super.getPreferredSize().getHeight());
	}
}
