import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author Théo Roton
 * JPanel qui crée l'affichage du labyrinthe
 */
public class JPanelLabyrinthe extends JPanel {

	private Labyrinthe labyrinthe;
	private static int TAILLE_CASE = 30;
	private static int TAILLE_MUR = TAILLE_CASE/3;
	private int taille;
	
	public JPanelLabyrinthe(Labyrinthe l) {
		labyrinthe = l;
		taille = 80 + TAILLE_CASE * labyrinthe.getTaille() + TAILLE_MUR * (labyrinthe.getTaille() - 1);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		
		//Affichage des cases
		for (int i=0; i < labyrinthe.getTaille(); i++) {
			
			for (int j=0; j < labyrinthe.getTaille(); j++) {
				g.setColor(Color.BLACK);
				g.drawRect(25 + i * (TAILLE_CASE+TAILLE_MUR), 25 + j * (TAILLE_CASE+TAILLE_MUR), TAILLE_CASE, TAILLE_CASE);
				g.setColor(Color.WHITE);				
				g.fillRect(26 + i * (TAILLE_CASE+TAILLE_MUR), 26 + j * (TAILLE_CASE+TAILLE_MUR), TAILLE_CASE-2, TAILLE_CASE-2);
			}
		}
		
		int sommet;
		//Affichage des murs
		for (int i=0; i < labyrinthe.getTaille(); i++) {
			
			for (int j=0; j < labyrinthe.getTaille(); j++) {
				
				sommet = i*labyrinthe.getTaille() + j;
				
				for (Edge e : labyrinthe.getGraphe().adj(sommet)) {
					
					g.setColor(Color.BLACK);
					
					if (sommet + 1 == e.to) {
						
						g.drawLine(25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * i, 
								   25 + TAILLE_CASE + TAILLE_MUR + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * i);
						
						g.drawLine(25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + 2*TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * i, 
								   25 + TAILLE_CASE + TAILLE_MUR + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + 2*TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * i);
						
						g.setColor(Color.WHITE);
						
						g.fillRect(25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * j - 1,
								   25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * i + 1, 
								   TAILLE_MUR+2, TAILLE_MUR-1);
						
					} else if (sommet + labyrinthe.getTaille() == e.to) {
						
						g.drawLine(25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + TAILLE_CASE + TAILLE_MUR + (TAILLE_CASE + TAILLE_MUR) * i, 
								   25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * i);
						
						g.drawLine(25 + 2*TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + TAILLE_CASE + TAILLE_MUR + (TAILLE_CASE + TAILLE_MUR) * i, 
								   25 + 2*TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * j, 
								   25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * i);
						
						g.setColor(Color.WHITE);
						
						g.fillRect(25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * j + 1, 
								   25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * i - 1,  
								   TAILLE_MUR-1, TAILLE_MUR+2);
					}

				
				}
			}
		}
		
		//Affichage de l'entrée et de la sortie
		g.setColor(Color.BLACK);
		
		g.drawLine(25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1), 
				   25 + TAILLE_CASE/3, 
				   25 + TAILLE_CASE + TAILLE_MUR + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1), 
				   25 + TAILLE_CASE/3);
		
		g.drawLine(25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1), 
				   25 + 2*TAILLE_CASE/3, 
				   25 + TAILLE_CASE + TAILLE_MUR + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1), 
				   25 + 2*TAILLE_CASE/3);
		
		g.drawLine(25 - TAILLE_MUR, 
				   25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1), 
				   25, 
				   25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1));
		
		g.drawLine(25 - TAILLE_MUR, 
				   25 + 2*TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1), 
				   25, 
				   25 + 2*TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1));
		
		g.setColor(Color.WHITE);
		
		g.fillRect(25 + TAILLE_CASE + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1) - 1, 
				   25 + TAILLE_CASE/3 + 1, 
				   TAILLE_MUR+2, TAILLE_MUR-1);
		
		g.fillRect(25 - TAILLE_MUR + 1, 
				   25 + TAILLE_CASE/3 + (TAILLE_CASE + TAILLE_MUR) * (labyrinthe.getTaille() - 1) + 1, 
				   TAILLE_MUR+2, TAILLE_MUR-1);
		
	}

	public int getTaille() {
		return taille;
	}
	
	
}
