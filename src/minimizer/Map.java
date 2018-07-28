package minimizer;

import java.awt.BasicStroke;
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

	public Map(int vars, Font font) {
		super();
		grid = new Grid(1 << vars / 2, 1 << (vars + 1) / 2, font);
		setViewportView(grid);
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
}
