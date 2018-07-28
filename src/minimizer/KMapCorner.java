package minimizer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

// TODO Zeichnen
public class KMapCorner extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cell rowDesc;
	private Cell colDesc;
	
	public KMapCorner(int vars) {
		String str = "</html>";
		for(int i = 0; i < (vars + 1) / 2; ++i) {
			str = "X<sub>" + i + "</sub>" + str;
		}
		str = "<html>" + str;
		colDesc = new Cell(str);
		add(colDesc);
		
		str = "</html>";
		for(int i = (vars + 1) / 2; i < vars; ++i) {
			str = "X<sub>" + i + "</sub>" + str;
		}
		str = "<html>" + str;
		rowDesc = new Cell(str);
		add(rowDesc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(Map.THICK);
		g2D.drawLine(0, 0, getWidth(), getHeight());
		g2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		rowDesc.setBounds(0, (int) (getHeight() - rowDesc.getPreferredSize().getHeight()), (int) rowDesc.getPreferredSize().getWidth(), (int) rowDesc.getPreferredSize().getHeight());
		colDesc.setBounds((int) (getWidth() - colDesc.getPreferredSize().getWidth()), 0, (int) colDesc.getPreferredSize().getWidth(), (int) colDesc.getPreferredSize().getHeight());
	}

	@Override
	public Dimension getPreferredSize() {
		int height = (int) colDesc.getPreferredSize().getHeight() + Integer.max((int) colDesc.getPreferredSize().getWidth(), (int) rowDesc.getPreferredSize().getWidth());
		return new Dimension(2 * height, height);
	}
}
