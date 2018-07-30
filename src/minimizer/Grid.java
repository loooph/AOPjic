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
	public void paint(Graphics g) {
		positionCells();
		super.paint(g);
		drawGrid((Graphics2D) g);
	}

	private void drawGrid(Graphics2D g) {
		if(getCols() == 0) {
			return;
		}
		
		// TODO Umrandung fett zeichnen
		// Umrandung
		g.setStroke(Map.THICK);
		g.drawRect(0, 0, getCols() * getCellwidth(), getLines() * getCellheight());
		// vertikal
		for(int i = 1; i < getCols(); ++i) {
			if( i % 4 == 0 ) {
				g.setStroke(Map.THICK);
			}
			else {
				g.setStroke(Map.THIN);
			}
			g.drawLine(i * getCellwidth(), 0 , i * getCellwidth(), getLines() * getCellheight());
		}
		// horizontal
		for(int i = 1; i < getLines(); ++i) {
			if( i % 4 == 0 ) {
				g.setStroke(Map.THICK);
			}
			else {
				g.setStroke(Map.THIN);
			}
			g.drawLine(0, i * getCellheight(), getCols() * getCellwidth(), i * getCellheight());
		}
	}
	
	private void positionCells() {
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
		int row = pos / getCols();
		int col = pos % getCols();
		if(row % 2 != 0 ) {
			col = getCols() - col - 1;
		}
		setVal(val, row, col);
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
	
	// Alignment-Konstante aus SwingConstants
	public void setHorizontalCellTextAlignment(int alignment) {
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[i].length; ++j) {
				data[i][j].setHorizontalAlignment(alignment);
			}
		}
	}
	
	// Alignment-Konstante aus SwingConstants
	public void setVerticalCellTextAlignment(int alignment) {
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[i].length; ++j) {
				data[i][j].setVerticalAlignment(alignment);
			}
		}
	}

	// möglicherweise inkompatibel mit KV-Diagramm
	public void highlightCell(int pos, Color color, boolean overwrite) {
		int row = pos / getCols();
		int col = pos % getCols();
		if(row % 2 != 0 ) {
			col = getCols() - col - 1;
		}
		highlightCell(row, col, color, overwrite);
	}
	
	public void highlightCell(int row, int col, Color color, boolean overwrite) {
		if(overwrite) {
			data[row][col].setBackground(color);
		} else {
			data[row][col].setBackground(avgColor(color, data[row][col].getBackground()));
		}
	}
	
	
	public static Color avgColor(Color col1, Color col2) {
		return new Color(
				(int) Math.sqrt((col1.getRed() * col1.getRed() * col1.getAlpha() + col2.getRed() * col2.getRed() * col2.getAlpha()) / (col1.getAlpha() + col2.getAlpha())),
				(int) Math.sqrt((col1.getGreen() * col1.getGreen() * col1.getAlpha() + col2.getGreen() * col2.getGreen() * col2.getAlpha()) / (col1.getAlpha() + col2.getAlpha())),
				(int) Math.sqrt((col1.getBlue() * col1.getBlue() * col1.getAlpha() + col2.getBlue() * col2.getBlue() * col2.getAlpha()) / (col1.getAlpha() + col2.getAlpha())),
				Integer.max(col1.getAlpha(), col2.getAlpha()));
	}
	
	
}
