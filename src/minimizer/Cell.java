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

	public static int chooseMaxFontSize(String str, int initSize, int width, int height) {
		Cell cell = new Cell(str);
		cell.setFont(cell.getFont().deriveFont((float) initSize));
		while(cell.getPreferredSize().getWidth() > width 
				|| cell.getPreferredSize().getHeight() > height) {
			cell.setFont(cell.getFont().deriveFont(cell.getFont().getSize() - 1.0f));
		}
		return cell.getFont().getSize();
	}
}
