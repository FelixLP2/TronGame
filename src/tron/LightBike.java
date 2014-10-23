package tron;

import java.util.ArrayList;

public class LightBike {

	private int LBPositionX;
	private int LBPositionY;
	private String LBDirection;
	private int LBnumeroJoueur;
	private boolean Joueur; // true c'est un joueur, false c'est un CPU
	private boolean estVivant;
	private boolean aAvance;	// Evite à la moto de faire un demi tour sur elle même, 
								// dans le cas où le joueur appuierait sur deux flêches en même temps
	
	protected LightBike(int x, int y, boolean joueur, String direction, int num){
		this.LBPositionX=x;
		this.LBPositionY=y;
		this.Joueur=joueur;
		this.LBDirection=direction;
		this.estVivant=true;
		this.LBnumeroJoueur=num;
		this.aAvance = false;
	};
	
	/*
	 * Met à jour les coordonnées de la 
	 * LightBike en la faisant avancer 
	 */
	protected void avance(){
		switch (LBDirection){
		case "Top": LBPositionY--; break;
		case "Bottom": LBPositionY++; break;
		case "Left": LBPositionX--; break;
		case "Right": LBPositionX++; break;
		default: ;break;
		}
		aAvance = true;
	}
	
	/*
	 * Utilisé dans la méthode LBJoue
	 */
	
	protected boolean estLibre(int value){
		if(value!=0){
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
	 * Si la case de la grille est déjà occupée, le joueur qui fait un déplacement dessus perd
	 * sinon il occupe la case
	 */
	
	protected void LBJoue(TheGrid grid){
		this.avance();
		if(LBPositionX>=0&&LBPositionX<grid.getTaille()&&LBPositionY>=0&&LBPositionY<grid.getTaille()){
			if(estLibre(grid.getCase(LBPositionX, LBPositionY))){
				grid.setCase(LBPositionX, LBPositionY, LBnumeroJoueur);
			}
			else{
				this.estVivant=false;
			}
		}
		else
		{
			this.estVivant=false;
		}
		
	}
	
	/************************
	 * IA des LightBikes
	 ************************/
	
	/*
	 * IA Facile (Evite les murs)
	 */
	
	protected void IAchoisirDirectionEZ(TheGrid grid){
		ArrayList<String> directionPossible = new ArrayList<String>();
		switch(LBDirection){
		case "Top":
			if(this.isAvaibleDeplacement(grid, "Top"))
				directionPossible.add("Top");
			if(this.isAvaibleDeplacement(grid, "Left"))
				directionPossible.add("Left");
			if(this.isAvaibleDeplacement(grid, "Right"))
				directionPossible.add("Right");
			break;
		case "Bottom":
			if(this.isAvaibleDeplacement(grid, "Bottom"))
				directionPossible.add("Bottom");
			if(this.isAvaibleDeplacement(grid, "Left"))
				directionPossible.add("Left");
			if(this.isAvaibleDeplacement(grid, "Right"))
				directionPossible.add("Right");
			
			break;
		case "Right":
			if(this.isAvaibleDeplacement(grid, "Right"))
				directionPossible.add("Right");
			if(this.isAvaibleDeplacement(grid, "Bottom"))
				directionPossible.add("Bottom");
			if(this.isAvaibleDeplacement(grid, "Top"))
				directionPossible.add("Top");

		
			break;
		case "Left":
			if(this.isAvaibleDeplacement(grid, "Left"))
				directionPossible.add("Left");
			if(this.isAvaibleDeplacement(grid, "Top"))
				directionPossible.add("Top");
			if(this.isAvaibleDeplacement(grid, "Bottom"))
				directionPossible.add("Bottom");
		
			break;
		default: System.out.println("Direction inconnue");
			break;
		}
		if(directionPossible.size()!=0){
			if(directionPossible.size()==1)
				LBDirection=directionPossible.get(0);
			else if(directionPossible.size()==2)
				LBDirection=directionPossible.get(this.random01());
			else
				LBDirection=directionPossible.get(this.random012());
		}
	}
	
	private boolean isAvaibleDeplacement(TheGrid grid, String deplacement){
		boolean avaible = false;
		switch(deplacement){
		case "Top":
			if(LBPositionY-1>=0){
				if(grid.getCase(LBPositionX,LBPositionY-1)==0){
					avaible = true;
				}
			}
			break;
		case "Bottom":
			if((LBPositionY+1)<grid.getTaille()){
				if(grid.getCase(LBPositionX,LBPositionY+1)==0){
					avaible = true;
				}
			}
			break;
		case "Right":
		if((LBPositionX+1)<grid.getTaille()){
			if(grid.getCase(LBPositionX+1,LBPositionY)==0){
				avaible = true;
			}	
		}
			break;
		case "Left":
			if((LBPositionX-1)>=0){
				if(grid.getCase(LBPositionX-1,LBPositionY)==0){
					avaible = true;
				}
			}
			break;
		}
		return avaible;
	}
	
	/*
	 * Renvoie 0 ou 1 avec 29/30 chances de renvoyer 0 et 1/30 de renvoyer 1
	 */
	private int random01(){
		int val=(int) (Math.random()*30+1);
		if(val<30)
			return(0);
		else
			return(1);
	}
	
	/*
	 * Renvoie 0, 1, ou 2 avec 28/30 chances de renvoyer 0, 1/30 de renvoyer 1, 1/30 de renvoyer 2
	 */
	
	private int random012(){
		int val=(int) (Math.random()*30+1);
		if(val<29)
			return(0);
		else if(val==29)
			return(1);
		else
			return(2);
	}
	
	/**
	 * Fin de l'IA
	 */
	
	/*
	 * Getters et setters pour toutes les variables associées aux LightBikes
	 */

	protected int getLBPositionX() {
		return LBPositionX;
	}

	protected void setLBPositionX(int lBPositionX) {
		LBPositionX = lBPositionX;
	}

	protected int getLBPositionY() {
		return LBPositionY;
	}

	protected void setLBPositionY(int lBPositionY) {
		LBPositionY = lBPositionY;
	}

	protected String getLBDirection() {
		return LBDirection;
	}

	protected void setLBDirection(String lBDirection) {
		LBDirection = lBDirection;
	}

	protected int getLBnumeroJoueur() {
		return LBnumeroJoueur;
	}

	protected void setLBnumeroJoueur(int lBnumeroJoueur) {
		LBnumeroJoueur = lBnumeroJoueur;
	}

	protected boolean isJoueur() {
		return Joueur;
	}

	protected void setJoueur(boolean joueur) {
		Joueur = joueur;
	}

	protected boolean isEstVivant() {
		return estVivant;
	}

	protected void setEstVivant(boolean estVivant) {
		this.estVivant = estVivant;
	}

	public boolean isaAvance() {
		return aAvance;
	}

	public void setaAvance(boolean aAvance) {
		this.aAvance = aAvance;
	}
	
	
	
}
