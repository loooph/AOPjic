package minimizer;

import java.awt.Component;

public class KarnaughMap extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Werte des Karnaugh-Plans, erster Index entspricht Zeilen, zweiter Index entspricht Spalten
	 */
	private KMAPVAL[][] data;
	
	public KarnaughMap(int dimension) {
		data = new KMAPVAL[dimension][dimension];
	}
	
	public KarnaughMap(KMAPVAL[][] data) {
		this.data = data;
	}
	
	public KMAPVAL[][] getData() {
		return data;
	}
	
	public KMAPVAL getVal(int row, int col) {
		return data[row][col];
	}
	
	public KMAPVAL setVal(KMAPVAL val, int row, int col) {
		return data[row][col] = val;
	}
	
	// TODO Zeichnen
}
