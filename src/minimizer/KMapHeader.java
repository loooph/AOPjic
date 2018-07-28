package minimizer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

public class KMapHeader extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int HORIZONTAL = SwingConstants.HORIZONTAL;
	public static final int VERTICAL = SwingConstants.VERTICAL;
	private Grid content;
	
	
	public KMapHeader(int orientation, int cellsize, int cells, Font font) {
		if(orientation != HORIZONTAL && orientation != VERTICAL) {
			throw new IllegalArgumentException("Illegal KMapHeader orientation: " + orientation);
		}
		if(orientation == VERTICAL) {
			content = new Grid(cells, 1, font);
		} else {
			content = new Grid(1, cells, font);
		}
		for(int i = 0; i < cells; ++i) {
			content.setVal(getGrayCode(i, cells - 1), i);
		}
		content.setBounds(new Rectangle(content.getPreferredSize()));
		this.add(content);
		System.out.println(content.getPreferredSize());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(Map.THICK);
		g2D.drawRect(0, 0, (int) content.getPreferredSize().getWidth() - 1, (int) content.getPreferredSize().getHeight() - 1);
	}

	@Override
	public Dimension getPreferredSize() {
		return content.getPreferredSize();
	}

	private String getGrayCode(int i, int maxVal) {
		String grayCode = Integer.toBinaryString(i ^ (i >> 1));
		while(grayCode.length() < Integer.toBinaryString(maxVal).length()) {
			grayCode = "0" + grayCode;
		}
		return grayCode;
	}
	
	public int getCellheight() {
		return content.getCellheight();
	}
	
	public int getCellwidth() {
		return content.getCellwidth();
	}
	
	
}
