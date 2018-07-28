package minimizer;

import java.awt.Font;

// TODO Eckfeld
public class KarnaughMap extends Map {
	
	public KarnaughMap(int vars, Font font) {
		super(vars, font);
		KMapHeader colHead = new KMapHeader(KMapHeader.HORIZONTAL, getGrid().getCellwidth(), getGrid().getCols(), font);
		KMapHeader rowHead = new KMapHeader(KMapHeader.VERTICAL, getGrid().getCellheight(), getGrid().getLines(), font);
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
