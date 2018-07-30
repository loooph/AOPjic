package minimizer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class KMapCorner extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel rowDesc;
	private JLabel colDesc;
	
	public KMapCorner(int vars, Font font) {
		String str = "</html>";
		for(int i = 0; i < (vars + 1) / 2; ++i) {
			str = "X<sub>" + i + "</sub>" + str;
		}
		str = "<html>" + str;
		colDesc = new JLabel(str);
		colDesc.setFont(font.deriveFont(font.getSize() * 0.7f));
		colDesc.setBorder(new EmptyBorder(0, 0, 0, 1));
		add(colDesc);
		
		str = "</html>";
		for(int i = (vars + 1) / 2; i < vars; ++i) {
			str = "X<sub>" + i + "</sub>" + str;
		}
		str = "<html>" + str;
		rowDesc = new JLabel(str);
		rowDesc.setFont(font.deriveFont(font.getSize() * 0.7f));
		rowDesc.setBorder(new EmptyBorder(0, 1, 1, 0));
		add(rowDesc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(Map.THICK);
		g2D.drawLine(0, 0, getWidth() - 1, getHeight() - 1);
		g2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		// Positionierung der JLabels
		rowDesc.setBounds(0, (int) (getHeight() - rowDesc.getPreferredSize().getHeight() + rowDesc.getFont().getSize() / 6), (int) rowDesc.getPreferredSize().getWidth(), (int) rowDesc.getPreferredSize().getHeight());
		colDesc.setBounds((int) (getWidth() - colDesc.getPreferredSize().getWidth()), - colDesc.getFont().getSize() / 5, (int) colDesc.getPreferredSize().getWidth(), (int) colDesc.getPreferredSize().getHeight());
	}

	@Override
	public Dimension getPreferredSize() {
		int height = (int) (0.88 * (colDesc.getPreferredSize().getHeight() + Integer.max((int) colDesc.getPreferredSize().getWidth(), (int) rowDesc.getPreferredSize().getWidth()) / 2.5));
		return new Dimension((int) (2.5 * height), height);
	}
}
