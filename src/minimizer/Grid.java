package minimizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Random;

import javax.swing.JComponent;

public class Grid extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void paintComponent(Graphics g) {
		draw((Graphics2D) g);
	}

	private void draw(Graphics2D g) {
		if(getCols() == 0) {
			return;
		}
		Stroke thin = new BasicStroke(1.0f);
		Stroke thick = new BasicStroke(1.6f);
		int cellheight = (int) data[0][0].getPreferredSize().getHeight();
		int cellwidth = Integer.max((int) data[0][0].getPreferredSize().getWidth(), cellheight * 2);//getWidth() / getCols();
		int xOff = 0;//getWidth() % getCols() / 2;
		int yOff = 0;//getHeight() % getLines() / 2;
		
		g.setStroke(thin);		
		g.setColor(Color.DARK_GRAY);
		
		// vertikal
		for(int i = 1; i < getCols(); ++i) {
			if( i % 4 == 0 ) {
				g.setStroke(thick);
			}
			else {
				g.setStroke(thin);
			}
			g.drawLine(getX() + xOff + i * cellwidth, getY(), getX() + xOff + i * cellwidth, getY() + getHeight());
		}
		// horizontal
		for(int i = 1; i < getLines(); ++i) {
			if( i % 4 == 0 ) {
				g.setStroke(thick);
			}
			else {
				g.setStroke(thin);
			}
			g.drawLine(getX(), getY() + yOff + i * cellheight, getX() + getWidth(), getY() + yOff + i * cellheight);
		}
		// Zellen
		for(int i = 0; i < getLines(); ++i) {
			for(int j = 0; j < getCols(); ++j) {
				data[i][j].setBounds(getX() + j * cellwidth, getY() + i * cellheight, cellwidth, cellheight);
			}
		}
	}
	
	public int getCols() {
		if(data.length == 0) {
			return 0;
		}
		return data[0].length;
	}
	
	public int getLines() {
		return data.length;
	}

	public void setVal(KMAPVAL val, int row, int col) {
		data[row][col].setText(val.toString());
	}
	
	@Override
	public Dimension getPreferredSize() {
		if(getCols() == 0) {
			return new Dimension(0,0);
		}
		return new Dimension(getCols() * Integer.max((int) data[0][0].getPreferredSize().getWidth(), (int) data[0][0].getPreferredSize().getHeight() * 2), (int)(getLines() * data[0][0].getPreferredSize().getHeight()));
	}

	private Cell[][] data;
	
	public Grid(int lines, int columns, Font font) {
		super();
		//setLayout(new GridLayout(lines, columns));
		data = new Cell[lines][columns];
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[i].length; ++j) {
				data[i][j] = new Cell(KMAPVAL.values()[new Random().nextInt(KMAPVAL.values().length)]);
				data[i][j].setFont(font);
				add(data[i][j]);
			}
		}
	}
	
	
}
