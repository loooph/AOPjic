package minimizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JScrollPane;


public abstract class Map extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Stroke THIN = new BasicStroke(1.0f);
	public static final Stroke THICK = new BasicStroke(1.6f);
	
	private Grid grid;
	private int vars;

	public int getVars() {
		return vars;
	}

	public void setVars(int vars) {
		this.vars = vars;
	}
	
	public abstract void setVal(String minterm, String val);
	
	public abstract void setVal(String minterm, KMAPVAL val);
	
	public void setVal(KMAPVAL val, int pos) {
		grid.setVal(val.toString(), pos);
	}
	
	public Map(int vars, Font font) {
		super();
		this.vars = vars;
		grid = new Grid(1 << vars / 2, 1 << (vars + 1) / 2, font);
		setViewportView(grid);
		getHorizontalScrollBar().setUnitIncrement(grid.getCellwidth());
		getVerticalScrollBar().setUnitIncrement(grid.getCellheight());
	}
	
	@Override
	public void paint(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g);
	}

	public void setCellwidth(int cellwidth) {
		grid.setCellwidth(cellwidth);
	}
	
	public void setCellheight(int cellheight) {
		grid.setCellheight(cellheight);
	}

	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Hebt Primimplikantenblock farblich hervor
	 * @param primeterm Primimplikant, Wert an der Stelle i entspricht der Variablen x_i
	 * @param color Farbe in der der Block hervorgehoben wird
	 */
	public abstract void highlight(KMAPVAL[] primeterm, Color color, boolean overwrite);
	
	public void highlightCell(int pos, Color color, boolean overwrite) {
		grid.highlightCell(pos, color, overwrite);
		repaint();
	}
}
