package minimizer;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Cell extends JLabel {
	@Override
	public boolean isOpaque() {
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cell() {
		super();
		setVerticalAlignment(CENTER);
		setHorizontalAlignment(CENTER);
		// TODO dynamisch
		setBorder(new EmptyBorder(0, 10, 0, 10));
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
