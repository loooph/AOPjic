package minimizer;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.border.AbstractBorder;


// TODO abstract
public class DescBorder extends AbstractBorder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int cellwidth;
	private int cellheight;
	private int vars;
	
	public DescBorder(int vars, int cellwidth, int cellheight) {
		this.vars = vars;
		this.cellwidth = cellwidth;
		this.cellheight = cellheight;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width, height, cellwidth / 5, cellheight / 5);
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

}
