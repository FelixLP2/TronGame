package tron;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class AffichageGrid extends JPanel {

	private TheGrid grid = new TheGrid();
	
	protected void setGrid(TheGrid grid){
		this.grid=grid;
	}
	
	protected void paintComponent(Graphics g){
		for(int i=0;i<grid.getTaille();i++){
			for(int j=0;j<grid.getTaille();j++){
				switch(grid.getCase(i, j)){
				case 1: g.setColor(Color.RED); break;
				case 2: g.setColor(Color.BLUE); break;
				case 3: g.setColor(Color.BLACK);break;
				case 4: g.setColor(Color.CYAN); break;
				default: g.setColor(Color.WHITE); break;
				}
				g.fillRect(6*i, 6*j, 6, 6);
			}
		}
	}
}
