package minimizer;

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
}
