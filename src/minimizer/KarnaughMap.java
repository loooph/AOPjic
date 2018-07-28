package minimizer;

import java.awt.Font;

import javax.swing.JScrollPane;

// TODO Eckfeld
public class KarnaughMap extends Map {
	
	public KarnaughMap(int vars, Font font) {
		super(vars, font);
		KMapHeader colHead = new KMapHeader(KMapHeader.HORIZONTAL, getGrid().getCellwidth(), getGrid().getCols(), font);
		KMapHeader rowHead = new KMapHeader(KMapHeader.VERTICAL, getGrid().getCellheight(), getGrid().getLines(), font);
		KMapCorner corner = new KMapCorner(vars);
		colHead.setCellheight((int) corner.getPreferredSize().getHeight());
		rowHead.setCellwidth((int) corner.getPreferredSize().getWidth());
		setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
		setColumnHeaderView(colHead);
		setRowHeaderView(rowHead);
		setCellheight(rowHead.getCellheight());
		setCellwidth(colHead.getCellwidth());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
