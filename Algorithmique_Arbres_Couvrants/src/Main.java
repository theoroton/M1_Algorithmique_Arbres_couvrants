import javax.swing.JFrame;

public class Main {

	/**
	 * Lance la création d'un labyrinthe de taille N * N et l'affiche
	 */
	public static void main(String[] args) {
		Labyrinthe l = new Labyrinthe(5, 1);

		JFrame fenetre = new JFrame("Labyrinthe");
		JPanelLabyrinthe jpanel = new JPanelLabyrinthe(l);
		
		fenetre.add(jpanel);
		fenetre.setSize(jpanel.getTaille(), jpanel.getTaille());
		fenetre.setVisible(true);
	}

}
