package minimizer;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Cell extends JLabel {
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

//	@Override
//	public Dimension getPreferredSize() {
//		return new Dimension((int) (super.getPreferredSize().getWidth() * 1.2), (int) super.getPreferredSize().getHeight());
//	}
}
