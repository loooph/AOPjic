package minimizer;

import java.awt.BorderLayout;

import java.awt.Font;

import javax.swing.JComponent;


// TODO abstract
public class Map extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Grid grid;
	
	public Map(int vars, Font font) {
		setLayout(new BorderLayout());
		setBorder(new DescBorder(vars, 30, 20));
		grid = new Grid(1 << vars / 2, 1 << (vars + 1) / 2, font);
		add(grid);
	}
	
}
