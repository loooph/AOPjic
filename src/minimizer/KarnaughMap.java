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
		rowHead.setCellwidth(Integer.max((int) corner.getPreferredSize().getWidth(), rowHead.getCellwidth()));
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
	public void highlight(KMAPVAL[] primeterm, Color color, boolean overwrite) {
		if(primeterm.length != getVars()) {
			throw new IllegalArgumentException("Wrong number of variables!");
		}
		List<String> cells = new ArrayList<>();
		cells.add("");
		for(int i = 0; i < primeterm.length; ++i) {
			switch(primeterm[i]) {
				case TRUE:
					cells.replaceAll(e -> "1" + e);
					break;
				case FALSE:
					cells.replaceAll(e -> "0" + e);
					break;
				case DONTCARE:
					List<String> newCells = new ArrayList<>(cells);
					newCells.replaceAll(e -> "1" + e);
					cells.replaceAll(e -> "0" + e);
					cells.addAll(newCells);
					break;
			}
		}
		cells.parallelStream().mapToInt(e -> Integer.parseInt(e, 2)).map(this::GrayToBinary).forEach(e -> highlightCell(e, color, overwrite));
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

	@Override
	public void setVal(String minterm, String val) {
		setVal(minterm, KMAPVAL.toKMAPVALArray(val)[0]);
	}

	@Override
	public void setVal(String minterm, KMAPVAL val) {
		setVal(val, GrayToBinary(Integer.parseInt(minterm, 2)));
	}
}
