package minimizer;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

public class KarnaughMap extends Map {
	
	public KarnaughMap(int vars, Font font) {
		super(vars, font);
		KMapHeader colHead = new KMapHeader(KMapHeader.HORIZONTAL, getGrid().getCellwidth(), getGrid().getCols(), font);
		KMapHeader rowHead = new KMapHeader(KMapHeader.VERTICAL, getGrid().getCellheight(), getGrid().getLines(), font);
		KMapCorner corner = new KMapCorner(vars, font);
		colHead.setCellheight((int) corner.getPreferredSize().getHeight());
		rowHead.setCellwidth((int) corner.getPreferredSize().getWidth());
		setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
		setColumnHeaderView(colHead);
		setRowHeaderView(rowHead);
		setCellheight(rowHead.getCellheight());
		setCellwidth(colHead.getCellwidth());
		getHorizontalScrollBar().setUnitIncrement(colHead.getCellwidth());
		getVerticalScrollBar().setUnitIncrement(rowHead.getCellheight());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void highlight(KMAPVAL[] primeterm, Color color) {
		if(primeterm.length != getVars()) {
			throw new IllegalArgumentException("Wrong number of variables!");
		}
		List<Integer> cells = new ArrayList<>();
		
		// TODO Zellen berechnen
		
		cells.parallelStream().map(this::GrayToBinary);
		cells.forEach(e -> highlightCell(e, color));
	}
	
	// https://en.wikipedia.org/wiki/Gray_code#Converting_to_and_from_Gray_code
	private int GrayToBinary(int gray)
	{
		int mask = gray >> 1;
	    while (mask != 0)
	    {
	        gray = gray ^ mask;
	        mask = mask >> 1;
	    }
	    return gray;
	}
}
