package minimizer;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

// TODO Zeichnen
public class KMapCorner extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel rowDesc;
	private JLabel colDesc;
	
	public KMapCorner(int vars) {
		String str = "</html>";
		for(int i = 0; i < (vars + 1) / 2; ++i) {
			str = "X<sub>" + i + "</sub>" + str;
		}
		str = "<html>" + str;
		colDesc = new JLabel(str);
		add(colDesc);
		
		str = "</html>";
		for(int i = (vars + 1) / 2; i < vars; ++i) {
			str = "X<sub>" + i + "</sub>" + str;
		}
		str = "<html>" + str;
		rowDesc = new JLabel(str);
		add(rowDesc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(Map.THICK);
		g2D.drawLine(0, 0, getWidth(), getHeight());
		g2D.drawRect(0, 0, getWidth(), getHeight());
		
	}
}
