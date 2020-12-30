
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author Théo Roton
 * Classe représentant l'algorithme de Wilson
 */
public class Wilson {

	
	//Tableau indiquant pour chaque sommet s'il a été visité (true si oui, false sinon)
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
	
		
		//Initialisation des éléments
		//Arêtes adjacentes d'un sommet
		ArrayList<Edge> aretesAdjacentes;
		//Arête prise à chaque étape
		Edge arete;
		//Sommet suivant
		int sommetSuivant;
		//Marche créer à chaque étape
		ArrayList<Integer> marche;
		//Arêtes de la marche
		ArrayList<Edge> aretesMarche;
	
		
		//Tant que tous les sommets n'ont pas été visités
		while (!sommetsTousVisites()) {
					
			//On prend le premier sommet non visité
			sommetCourant = choisirSommetNonVisite();
			
			//Création de la marche et des arêtes prises
			marche = new ArrayList<Integer>();
			aretesMarche = new ArrayList<Edge>();
		
			//Tant que le sommet courant de la marche n'est pas un sommet visité
			while (!sommetsVisites[sommetCourant]) {
				//On récupère les arêtes adjacentes au sommet courant
				aretesAdjacentes = g.adj(sommetCourant);
				//On mélange les arêtes
				Collections.shuffle(aretesAdjacentes);
				//On récupère la première arête des arêtes mélangées
				arete = aretesAdjacentes.get(0);
				
				//On trouve le sommet suivant
				sommetSuivant = arete.other(sommetCourant);
				
				//On ajoute le sommet courant à la marche
				marche.add(sommetCourant);
				//On ajoute l'arête emprunté à la marche
				aretesMarche.add(arete);
				
				//Le sommet courant devient le sommet que l'on a choisis
				sommetCourant = sommetSuivant;
			}
			
			//On traite la marche			
			traiterMarche(marche, aretesMarche);
			
			//Pour chaque sommet de la marche après traitement
			for (Integer i : marche) {
				//On indique qu'il a été visité
				sommetsVisites[i] = true;				
			}
			
			//Pour chaque arête de la marche
			for (Edge e : aretesMarche) {
				//On indique qu'elle a été utilisé
				e.used = true;
			}
		}
	}

	
	/**
	 * Méthode qui permet de traiter une marche en supprimant les boucles
	 * @param marche : marche à traiter
	 * @param aretesPrises : arêtes de la marche à traiter
	 */
	private void traiterMarche(ArrayList<Integer> marche, ArrayList<Edge> aretesPrises) {
		int occurences;
		int indiceSuppr;
		
		//Pour chaque sommet restant de la marche
		for (int indice = 0; indice < marche.size(); indice++) {
			
			//On récupère le nombre d'occurences du sommet dans la marche
			occurences = Collections.frequency(marche, marche.get(indice));
			
			//Si le nombre d'occurences est supérieur à 1
			if (occurences > 1) {
				
				//On prend l'index du sommet traité + 1
				indiceSuppr = indice + 1;
				
				//Tant qu'il y aura toujours plus de 1 occurence du sommet
				while (occurences > 1) {
					
					//Si le sommet qu'on s'apprête à supprimer est égal au sommet traité
					if (marche.get(indiceSuppr) == marche.get(indice)) {
						//On réduit le nombre d'occurences de 1
						occurences--;
					}
					
					//On enlève le sommet d'indice indiceSuppr
					marche.remove(indiceSuppr);
					//On enlève l'arête allant au sommet indiceSuppr
					aretesPrises.remove(indiceSuppr-1);
				}
			}
		}
		
	}

	
	/**
	 * Méthode qui permet de trouver le premier sommet non visité
	 * @return
	 */
	private int choisirSommetNonVisite() {
		int s = 0;
		
		//Si le sommet est visité, on regarde le sommet suivant
		while (sommetsVisites[s]) {
			s++;
		}
		
		return s;
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
	 * Méthode qui permet de tester l'algorithme de Wilson sur le graphe G1
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
	 * Méthode main pour lancer le test de l'algorithme de Wilson
	 */
	public static void main(String[] args) {
		Wilson w = new Wilson();
		w.testG1(1000000);
	}
}
