package minimizer;

import java.awt.Point;
import java.util.List;

public class Prime {
	private List<Point> cells;
	
	public Prime(List<Point> cells) {
		this.cells = cells;
	}
	
	private List<Point> getCells() {
		return cells;
	}
}
