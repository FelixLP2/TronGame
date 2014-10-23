package tron;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Affichage extends JFrame implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4119610370931535634L;
	//On initialise les LightBike et les variables
	private TheGrid grille = new TheGrid(100);
	private LightBike LB1 = new LightBike(grille.getTaille()/4,grille.getTaille()/4,true,"Right",1);
	private LightBike LB2 = new LightBike(grille.getTaille()/4,3*grille.getTaille()/4,false,"Bottom",2);
	private LightBike LB3 = new LightBike(3*grille.getTaille()/4,grille.getTaille()/4,false,"Left",3);
	private LightBike LB4 = new LightBike(3*grille.getTaille()/4,3*grille.getTaille()/4,false,"Top",4);
	private AffichageGrid affGrille = new AffichageGrid();
	
	protected Affichage(){
		
	//Caractéristiques de la fenêtre
			this.setTitle("Tron Le Jeu");
			this.setSize(605,635);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.setContentPane(affGrille);
			this.addKeyListener(this);
			this.setVisible(true);
			
				
		 Thread thread = new Thread( new Runnable(){

			@Override
			public void run() {
			// TODO Auto-generated method stub
				while(LB1.isEstVivant()){
					LB1.LBJoue(grille);
					if(LB2.isEstVivant()){
						LB2.IAchoisirDirectionEZ(grille);
						LB2.LBJoue(grille);
					}
					if(LB3.isEstVivant()){
						LB3.IAchoisirDirectionEZ(grille);
						LB3.LBJoue(grille);
					}
					if(LB4.isEstVivant()){
						LB4.IAchoisirDirectionEZ(grille);
						LB4.LBJoue(grille);
					}
					affGrille.setGrid(grille);
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			 
		 });
		 thread.start();
			
	}
	//Les touches du clavier
	public void keyPressed(KeyEvent e){
		if(LB1.isaAvance()){
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(LB1.getLBDirection()!="Right"){
					LB1.setLBDirection("Left");
					LB1.setaAvance(false);
				}
			break;
			case KeyEvent.VK_RIGHT:
				if(LB1.getLBDirection()!="Left"){
					LB1.setLBDirection("Right");
					LB1.setaAvance(false);
				}
			break;
			case KeyEvent.VK_DOWN:
				if(LB1.getLBDirection()!="Top"){
					LB1.setLBDirection("Bottom");
					LB1.setaAvance(false);
				}
			break;
			case KeyEvent.VK_UP:
				if(LB1.getLBDirection()!="Bottom"){
					LB1.setLBDirection("Top");
					LB1.setaAvance(false);
				}
			break;
			default:
			break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
