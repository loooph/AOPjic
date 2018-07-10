package minimizer;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class KarnaughMap extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Werte des Karnaugh-Plans, erster Index entspricht Zeilen, zweiter Index entspricht Spalten
	 */
	private KMAPVAL[][] data;
	
	private List<Prime> primes;
	
	public KarnaughMap(int dimension) {
		primes = new ArrayList<>();
		data = new KMAPVAL[dimension][dimension];
	}
	
	public KarnaughMap(KMAPVAL[][] data) {
		primes = new ArrayList<>();
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
	
	public void addPrime(Prime prime) {
		primes.add(prime);
	}
	// TODO Zeichnen
}
