package minimizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		
		// TODO Umrandung fett zeichnen
		// Umrandung
		g.setStroke(Map.THICK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		// vertikal
		for(int i = 1; i < getCols(); ++i) {
			if( i % 4 == 0 ) {
				g.setStroke(Map.THICK);
			}
			else {
				g.setStroke(Map.THIN);
			}
			g.drawLine(i * getCellwidth(), 0 , i * getCellwidth(), getHeight());
		}
		// horizontal
		for(int i = 1; i < getLines(); ++i) {
			if( i % 4 == 0 ) {
				g.setStroke(Map.THICK);
			}
			else {
				g.setStroke(Map.THIN);
			}
			g.drawLine(0, i * getCellheight(), getWidth(), i * getCellheight());
		}
		// Zellen
		for(int i = 0; i < getLines(); ++i) {
			for(int j = 0; j < getCols(); ++j) {
				data[i][j].setBounds(j * getCellwidth(), i * getCellheight(), getCellwidth(), getCellheight());
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
		updateCellSize(row, col);
	}
	
	public void setVal(String val, int row, int col) {
		data[row][col].setText(val);
		updateCellSize(row, col);
	}
	
	public void setVal(String val, int pos) {
		data[pos % getLines()][pos / getLines()].setText(val);;
		updateCellSize(pos % getLines(), pos / getLines());
	}
	
	private void updateCellSize(int row, int col) {
		cellheight = Integer.max((int) data[row][col].getPreferredSize().getHeight(), cellheight);
		cellwidth = Integer.max(Integer.max((int) data[row][col].getPreferredSize().getWidth(), getCellheight() * 2), cellwidth);
	}
	
	private void resetCellSize() {
		cellheight = (int) data[0][0].getPreferredSize().getHeight();
		cellwidth = Integer.max((int) data[0][0].getPreferredSize().getWidth(), getCellheight() * 2);
	}
	
	public int getCellwidth() {
		return cellwidth;
	}
	
	public int getCellheight() {
		return cellheight;
	}
	
	public void setCellheight(int cellheight) {
		this.cellheight = cellheight;
	}
	
	public void setCellwidth(int cellwidth) {
		this.cellwidth = cellwidth;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (getCols() * getCellwidth()), getLines() * getCellheight());
	}

	private Cell[][] data;
	private int cellwidth;
	private int cellheight;
	
	public Grid(int lines, int columns, Font font) {
		super();
		data = new Cell[lines][columns];
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[i].length; ++j) {
				data[i][j] = new Cell(KMAPVAL.values()[new Random().nextInt(KMAPVAL.values().length)]);
				data[i][j].setFont(font);
				add(data[i][j]);
			}
		}
		resetCellSize();
	}
	
	public Grid(KMAPVAL[][] content, Font font) {
		this(content.length, content[0].length, font);
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[i].length; ++j) {
				data[i][j].setText(content[i][j].toString());
			}
		}
		resetCellSize();
	}
	
	public Grid(Cell[][] content) {
		super();
		data = content;
		for(Cell[] row : content) {
			for(Cell cell : row) {
				add(cell);
			}
		}
		resetCellSize();
	}
	
	// Alignment in SwingConstants
	public void setHorizontalCellTextAlignment(int alignment) {
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[i].length; ++j) {
				data[i][j].setHorizontalAlignment(alignment);
			}
		}
	}
	
	// Alignment in SwingConstants
	public void setVerticalCellTextAlignment(int alignment) {
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[i].length; ++j) {
				data[i][j].setVerticalAlignment(alignment);
			}
		}
	}

	public void highlightCell(int pos, Color color) {
		data[pos % getLines()][pos / getLines()].setBackground(color);
	}
	
	
}
