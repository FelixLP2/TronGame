package tron;

public class TheGrid{

	private int[][] grid;
	private int taille;
	
	protected TheGrid(){
		this.taille=100;
		grid = new int[taille][taille];
	}
	
	protected TheGrid(int taille){
		this.taille=taille;
		grid=new int[taille][taille];
	}
	
	/*
	 * Permet de récupérer la valeur d'une case de la grille
	 */
	protected int getCase(int x, int y){
		return grid[x][y];
	}
	
	/*
	 * Permet d'écrire une valeur dans une case de la grille
	 */
	
	protected void setCase(int x, int y, int val){
		grid[x][y]=val;
	}
	
	protected int getTaille(){
		return taille;
	}
	
}
