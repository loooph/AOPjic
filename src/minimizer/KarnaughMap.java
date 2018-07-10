package minimizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class KarnaughMap {
	
	/**
	 * Werte des Karnaugh-Plans, erster Index entspricht Zeilen, zweiter Index entspricht Spalten
	 */
	private KMAPVAL[][] data;
	
	private List<Prime> primes;
	
	public KarnaughMap(int vars) {
		primes = new ArrayList<>();
		data = new KMAPVAL[1 << vars / 2][1 << (vars + 1) / 2 ];
	}
	
	public KarnaughMap(KMAPVAL[][] data) {
		primes = new ArrayList<>();
		this.data = data;
	}
	
	public int getXDim() {
		if(data.length == 0) {
			return 0;
		}
		return data[0].length;
	}
	
	public int getYDim() {
		return data.length;
	}
	
	public KMAPVAL[][] getData() {
		return data;
	}
	
	public KMAPVAL getVal(int row, int col) {
		return data[row][col];
	}
	
	public KMAPVAL setVal(KMAPVAL val, int row, int col) {
		return data[row][col] = val;
	}
	
	public void addPrime(Prime prime) {
		primes.add(prime);
	}
	// TODO Füllen
	/**
	 * Zeichnet den Karnaugh-Plan
	 * @param g Graphikobjekt zum Zeichnen
	 * @param x x-Koordinate der links oberen Ecke des Plans
	 * @param y y-Koordinate der links oberen Ecke des Plans
	 * @param width Breite des Plans, sollte ein Vielfaches von getXDim() + 1 sein
	 * @param height Höhe des Plans, sollte ein Vielfaches von getYDim() + 1 sein
	 */
	public void draw(Graphics2D g, int x, int y, int width, int height) {
		int cellwidth = width / (getXDim() + 1);
		int cellheight = height / (getYDim() + 1);
		Stroke thin = new BasicStroke(1.0f);
		Stroke thick = new BasicStroke(1.6f);
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.BLACK);
		g.setStroke(thick);
		g.drawRoundRect(x, y, width, height, cellwidth / 4, cellheight / 4);
		g.drawLine(x + cellwidth, y, x + cellwidth, y + height);
		g.drawLine(x, y + cellheight, x + width, y + cellheight);
		
		// cellwidth / 8 bzw. cellheight / 8 sind die Radien der Viertelellipsen in den Ecken, 0.293 ist 1 - 1 / sqrt(2) gerundet
		g.drawLine((int) ((x +  cellwidth / 8 * 0.293)) + 1, (int) (y + cellheight / 8 * 0.293) + 1, x + cellwidth, y + cellheight);
		
		g.setStroke(thin);		
		g.setColor(Color.DARK_GRAY);
		for(int i = 2; i <= getXDim(); ++i) {
			if( i % 4 == 1 ) {
				g.setStroke(thick);
			}
			else {
				g.setStroke(thin);
			}
			g.drawLine(x + i * cellwidth, y, x + i * cellwidth, y + height);
		}
		for(int i = 2; i <= getYDim(); ++i) {
			if( i % 4 == 1 ) {
				g.setStroke(thick);
			}
			else {
				g.setStroke(thin);
			}
			g.drawLine(x, y + i * cellheight, x + width, y + i * cellheight);
		}
		// Beschriftung
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 240));
		g.setFont(chooseFontSize(g.getFont(), g, getGrayCode(0, getXDim() - 1), 4 * cellwidth / 5,  4 * cellheight / 5));
		Rectangle2D textBounds = new TextLayout(getGrayCode(0, getXDim() - 1), g.getFont(), g.getFontRenderContext()).getBounds();
		// Gray-Code
		// Spalten
		for(int i = 0; i < getXDim(); ++i) {
			g.drawString(getGrayCode(i, getXDim() - 1), (int) (x + (i + 2) * cellwidth - textBounds.getWidth() - cellwidth / 10), (int) (y + 2 * cellheight / 3 + textBounds.getHeight() / 3));
		}
		// Zeilen
		textBounds = new TextLayout(getGrayCode(0, getYDim() - 1), g.getFont(), g.getFontRenderContext()).getBounds();
		for(int i = 0; i < getYDim(); ++i) {
			g.drawString(getGrayCode(i, getYDim() - 1), (int) (x + cellwidth - textBounds.getWidth()) - cellwidth / 10, (int) (y + (i + 2) * cellheight - (cellheight - textBounds.getHeight()) / 3));
		}
		// TODO Variablennamen eintragen
		// TODO Daten eintragen
	}
	
	private Font chooseFontSize(Font font, Graphics2D g, String maxText, int maxWidth, int maxHeight) {
		while(new TextLayout(maxText, font, g.getFontRenderContext()).getBounds().getWidth() > maxWidth || new TextLayout(maxText, font, g.getFontRenderContext()).getBounds().getHeight() > maxHeight) {
			font = font.deriveFont(font.getSize() - 1.0f);
		}
		return font;
	}

	private String getGrayCode(int i, int maxVal) {
		String grayCode = Integer.toBinaryString(i ^ (i >> 1));
		while(grayCode.length() < Integer.toBinaryString(maxVal).length()) {
			grayCode = "0" + grayCode;
		}
		return grayCode;
	}
}
