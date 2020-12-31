/**
 * 
 * @author Théo Roton
 * Classe qui permet de tester la création de labyrinthe
 */
public class TestLabyrinthe {

	/**
	 * Méthode qui va tester N créations de labyrinthe de taille t avec l'algorithme
	 * de Kruskal et l'algorithme d'Aldous-Broder.
	 * @param N : nombre de labyrinthe
	 * @param t : taille t * t des labyrinthes
	 */
	public void lancerTest(int N, int t) {
		//Initialisation
		Labyrinthe l;
		int nbCulsDeSacKruskal = 0; int distanceKruskal = 0;
		int nbCulsDeSacAldous = 0; int distanceAldous = 0;
		
		//Exécution de l'algorithme de Kruskal N fois
		for (int i = 0; i < N; i++) {
			l = new Labyrinthe(t, 1);
			l.lancerTest();
			nbCulsDeSacKruskal += l.getNbCulsDeSacs();
			distanceKruskal += l.getDistanceDebutFin();
		}
		
		//Exécution de l'algorithme d'Aldous-Broder N fois
		for (int i = 0; i < N; i++) {
			l = new Labyrinthe(t, 2);
			l.lancerTest();
			nbCulsDeSacAldous += l.getNbCulsDeSacs();
			distanceAldous += l.getDistanceDebutFin();
		}
		
		//On affiche le nombre de culs de sac moyen et la distance moyenne pour les N exécutions de chaque algorithme
		System.out.println("Moyenne pour " + N + " labyrinthes de taille " + t + " : \n");
		System.out.println("--- Kruskal ---");
		System.out.println("Nombre de culs de sac moyen : " + (double) nbCulsDeSacKruskal/N);
		System.out.println("Distance entre l'entrée et la sortie moyenne : " + (double) distanceKruskal/N);
		System.out.println("");
		System.out.println("--- Aldous-Broder ---");
		System.out.println("Nombre de culs de sac moyen : " + (double) nbCulsDeSacAldous/N);
		System.out.println("Distance entre l'entrée et la sortie moyenne : " + (double) distanceAldous/N);
		
	}
	
	/**
	 * Méthode main pour lancer le test du labyrinthe
	 */
	public static void main(String[] args) {
		TestLabyrinthe test = new TestLabyrinthe();
		test.lancerTest(1000, 20);
	}
}
