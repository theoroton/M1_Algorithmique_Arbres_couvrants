import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Théo Roton
 * Classe représentant l'algorithme de Kruskal
 */
public class Kruskal {
	
	//Tableau parent pour Union-Fin
	private int[] parent;

	/**
	 * Méthode qui crée un arbre couvrant pour le graphe g
	 * @param g : graphe dans lequel on cherche un arbre couvrant
	 */
	public void arbreCouvrant(Graph g) {
		//Récupère les arêtes du graphe g
		ArrayList<Edge> aretes = g.edges();
		
		//Mélange les arêtes
		Collections.shuffle(aretes);
		
		//Initilisation du tableau père avec makeSet
		parent = new int[g.vertices()];
		makeSet(g.vertices());
		
		//Pour chaque arête mélangée du graphe
		for (Edge e : aretes) {
			
			//On cherche la classe d'équivalence des 2 sommets de l'arête e
			int u = find(e.from);
			int v = find(e.to);
			
			//Si les 2 sommets on une classe différente, on peut les unir et utiliser cette arête
			//dans l'arbre couvrant.
			//Si les 2 sommets on la même classe, on a alors un cycle (on ne prend donc pas cette arête).
			if (u != v) {
				
				//On indique qu'on utilise l'arête dans le chemin couvrant
				e.used = true;
				//On unie les 2 ensembles
				union(e.from, e.to);
			}
		}
	}

	/**
	 * Méthode makeSet qui construit la classe d'équivalence de chaque sommet 
	 * @param vertices : nombre de sommets
	 */
	private void makeSet(int vertices) {
		//Pour chaque sommet
		for (int i=0; i<vertices; i++) {
			//On crée une classe d'équivalence avec un seul élément
			parent[i] = i;
		}	
	}
	
	/**
	 * Méthode find qui détermine la classe d'équivalence du sommet s
	 * @param s : ensemble dont on cherche la classe
	 * @return entier correspondant à la classe d'équivalence
	 */
	private int find(int s) {
		//Si le sommet s est égal à sa propre classe d'équivalence
		if (s == parent[s]) {
			//On renvoie s
			return s;
		
		//Sinon
		} else {
			//On cherche la classe d'équivalence de son père
			return find(parent[s]);
		}
	}
	
	/**
	 * Méthode union qui permet d'unir 2 sommets dans le même ensemble
	 * @param u : premier sommet
	 * @param v : second sommet
	 */
	private void union(int u, int v) {
		int racineU = find(u);
		int racineV = find(v);
		
		//Si les 2 sommets ont une classe d'équivalence différente
		if (racineU != racineV) {
			//On les unit
			parent[racineU] = racineV;
		}
	}
	
	
	
	
	/**
	 * Méthode qui permet de tester l'algorithme de Kruskal sur le graphe G1
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
	 * Méthode qui permet de tester l'algorithme de Kruskal sur le graphe G2
	 * @param N : nombre de test à faire
	 */
	public void testG2(int N) {
		Graph G = Graph.exampleG2();
		int nb1 = 0, nb2 = 0, nb3 = 0, nb4 = 0;

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
			
			//On remet à zéro l'arbre couvrant et on relance l'algorithme
			G.resetArbreCouvrant();
		}
		
		//Affichage des résultats
		System.out.println("Arbre couvrant 1 : [0-2; 1-2; 1-3] | Proportion : " + ((double) nb1/N) * 100 + "%");
		System.out.println("Arbre couvrant 2 : [0-2; 0-3; 1-2] | Proportion : " + ((double) nb2/N) * 100 + "%");
		System.out.println("Arbre couvrant 3 : [0-2; 0-3; 1-3] | Proportion : " + ((double) nb3/N) * 100 + "%");
		System.out.println("Arbre couvrant 4 : [0-3; 1-2; 1-3] | Proportion : " + ((double) nb4/N) * 100 + "%");
	}
	
	/**
	 * Méthode main pour lancer le test de l'algorithme de Kruskal
	 */
	public static void main(String[] args) {
		Kruskal k = new Kruskal();	
		k.testG1(1000000);
	}
}
