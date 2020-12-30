import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author Théo Roton
 * Classe représentant l'algorithme d'Aldous-Broder
 */
public class AldousBroder {
	
	//Tableau indiquant les sommets visités
	private boolean[] sommetsVisites;
	
	/**
	 * Méthode qui crée un arbre couvrant pour le graphe g
	 * @param g : graphe dans lequel on cherche un arbre couvrant
	 */
	public void arbreCouvrant(Graph g) {
		//On récupère le nombre de sommets du graphe
		int nbSommets = g.vertices();
		
		//On initialise le tableau des sommets visités avec le nombre de sommets du graphe
		sommetsVisites = new boolean[nbSommets];
		for (int i=0; i<nbSommets; i++) {
			sommetsVisites[i] = false;
		}
		
		//On choisis un sommet de départ aléatoire
		Random rand = new Random();
		int sommetCourant = rand.nextInt(nbSommets);
		//On indique que le sommet de départ est visité
		sommetsVisites[sommetCourant] = true;
		
		//Initialisation des objets
		ArrayList<Edge> aretesAdjacentes;
		Edge arete;
		int sommetSuivant;
		
		//Tant que tous les sommets n'ont pas été visités
		while (!sommetsTousVisites()) {
			//On récupère les arêtes adjacentes au sommet
			aretesAdjacentes = g.adj(sommetCourant);
			//On mélange les arêtes
			Collections.shuffle(aretesAdjacentes);
			//On récupère la première arête des arêtes mélangées
			arete = aretesAdjacentes.get(0);
			
			//On trouve le sommet suivant
			sommetSuivant = arete.other(sommetCourant);
			
			//Si le sommet choisis n'a pas été visité
			if (!sommetsVisites[sommetSuivant]) {
				//On prend l'arête le reliant
				arete.used = true;
				//On indique qu'on l'a visité
				sommetsVisites[sommetSuivant] = true;				
			}
			
			//Le sommet courant devient le sommet que l'on a choisis
			sommetCourant = sommetSuivant;
		}
	}

	/**
	 * Méthode qui permet d'indiquer si tous les sommets ont été
	 * visités
	 * @return true si tous les sommets ont été visités
	 */
	private boolean sommetsTousVisites() {
		boolean res = true;
		
		//Pour chaque sommet
		for (int i=0; i<sommetsVisites.length; i++) {
			
			//On regarde s'il a été visité
			if (!sommetsVisites[i]) {
				res = false;
			}
		}
		
		return res;
	}

	/**
	 * Méthode qui permet de tester l'algorithme d'Aldous-Broder sur le graphe G1
	 * @param N : nombre de test à faire
	 */
	public void testG1(int N) {
		Graph G = Graph.exampleG1();
		int nb1 = 0, nb2 = 0, nb3 = 0, nb4 = 0, nb5 = 0, nb6 = 0, nb7 = 0, nb8 = 0;

		//A chaque test
		for (int i=0; i<N; i++) {
			//On lance l'algorithme
			arbreCouvrant(G);
			//On récupère les arêtes de l'arbre couvrant
			ArrayList<Edge> list = G.aretesArbreCouvrant();
			
			//On teste quel arbre couvrant on a
			if (list.get(0).equals(0,2) && list.get(1).equals(1,2) && list.get(2).equals(1,3)) { nb1++; }
			else if (list.get(0).equals(0,2) && list.get(1).equals(0,3) && list.get(2).equals(1,2)) { nb2++; }
			else if (list.get(0).equals(0,2) && list.get(1).equals(0,3) && list.get(2).equals(1,3)) { nb3++; }
			else if (list.get(0).equals(0,3) && list.get(1).equals(1,2) && list.get(2).equals(1,3)) { nb4++; }
			else if (list.get(0).equals(0,1) && list.get(1).equals(0,3) && list.get(2).equals(1,2)) { nb5++; }
			else if (list.get(0).equals(0,1) && list.get(1).equals(0,2) && list.get(2).equals(1,3)) { nb6++; }
			else if (list.get(0).equals(0,1) && list.get(1).equals(0,2) && list.get(2).equals(0,3)) { nb7++; }
			else if (list.get(0).equals(0,1) && list.get(1).equals(1,2) && list.get(2).equals(1,3)) { nb8++; }
			
			//On remet à zéro l'arbre couvrant et on relance l'algorithme
			G.resetArbreCouvrant();
		}
		
		//Affichage des résultats
		System.out.println("Arbre couvrant 1 : [0-2; 1-2; 1-3] | Proportion : " + ((double) nb1/N) * 100 + "%");
		System.out.println("Arbre couvrant 2 : [0-2; 0-3; 1-2] | Proportion : " + ((double) nb2/N) * 100 + "%");
		System.out.println("Arbre couvrant 3 : [0-2; 0-3; 1-3] | Proportion : " + ((double) nb3/N) * 100 + "%");
		System.out.println("Arbre couvrant 4 : [0-3; 1-2; 1-3] | Proportion : " + ((double) nb4/N) * 100 + "%");
		System.out.println("Arbre couvrant 5 : [0-1; 0-3; 1-2] | Proportion : " + ((double) nb5/N) * 100 + "%");
		System.out.println("Arbre couvrant 6 : [0-1; 0-2; 1-3] | Proportion : " + ((double) nb6/N) * 100 + "%");
		System.out.println("Arbre couvrant 7 : [0-1; 0-2; 0-3] | Proportion : " + ((double) nb7/N) * 100 + "%");
		System.out.println("Arbre couvrant 8 : [0-1; 1-2; 1-3] | Proportion : " + ((double) nb8/N) * 100 + "%");
	}
	
	/**
	 * Méthode main pour lancer le test de l'algorithme d'Aldous-Broder
	 */
	public static void main(String[] args) {
		AldousBroder a = new AldousBroder();
		a.testG1(1000000);
	}
}
