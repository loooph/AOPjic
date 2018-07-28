package minimizer;

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
		
		str = "</html>";
		for(int i = (vars + 1) / 2; i < vars; ++i) {
			str = "X<sub>" + i + "</sub>" + str;
		}
		str = "<html>" + str;
		rowDesc = new JLabel(str);
	}
}
