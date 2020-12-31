/**
 * 
 * @author Théo Roton
 * Classe représentant le labyrinthe
 */
public class Labyrinthe {

	//Graphe sur lequel on va trouvé l'arbre couvrant
	private Graph arbreCouvrant;
	//Graphe représentant le labyrinthe
	private Graph graphe;
	
	//Taille N du labyrinthe
	private int taille;
	//Nombre de sommets dans le labyrinthe
	private int nbSommets;
	//Numéro du sommet de départ
	private int sommetDebut;
	//Numéro du sommet de fin
	private int sommetFin;
	
	//Nombre de culs de sac dans le labyrinthe
	private int nbCulsDeSacs;
	//Distance entre le début et la fin
	private int distanceDebutFin;
	//Tableau qui indique les sommets pour la taille du chemin
	private boolean[] sommetsVisites;
	
	/**
	 * Constructeur du Labyrinthe 
	 * @param t : taille N en largeur du labyrinthe
	 */
	public Labyrinthe(int N, int algo) {
		taille = N;
		//On aura N * N sommets
		nbSommets = taille * taille;
		
		//Création du graphe sur lequel on va cherché l'arbre couvrant
		arbreCouvrant = new Graph(nbSommets);
		//Création du graphe qui représentera le labyrinthe
		graphe = new Graph(nbSommets);
    	
		//Initialisation des sommets de début et de fin
		sommetDebut = taille * (taille-1);
		sommetFin = taille-1;
		
		//Initialisation des données de test
		nbCulsDeSacs = 0;
		distanceDebutFin = 0;	
		sommetsVisites = new boolean[nbSommets];	
		for (int i=0; i < nbSommets; i++) {
			sommetsVisites[i] = false;
		}
		
		//Pour chaque sommets on va	
		for (int i = 0; i < taille; i++) {
			
			for (int j = 0; j < taille; j++) {
				
				//Si le sommet n'est pas tout à droite du graphe
				if (j < taille - 1) {
					//On crée une arête allant de ce sommet à son voisin de droite
					arbreCouvrant.addEdge(new Edge(i*taille + j , i*taille + j + 1));
				}
				
				//Si le sommet n'est pas tout en bas du graphe
				if (i < taille - 1) {
					//On crée une arête allant de ce sommet à son voisin du dessous
					arbreCouvrant.addEdge(new Edge(i*taille + j, (i+1)*taille + j));
				}
			}
		}
		
		//Si algo vaut 1
		if (algo == 1) {
			//On exécute l'algorithme de Kruskal()
			genererKruskal();
		
		//Si algo vaut 2
		} else if (algo == 2) {
			//On exécute l'algorithme d'AldousBroder
			genererAldousBroder();
			
		}
	}
	
	
	/**
	 * Méthode qui permet de générer le labyrinthe avec l'algorithme de Kruskal
	 */
	private void genererKruskal() {	
		Kruskal k = new Kruskal();
		k.arbreCouvrant(arbreCouvrant);
		//On transforme le graphe sur lequel on a trouvé l'arbre couvrant
		transformationArbreCouvrant();
	}
	
	
	/**
	 * Méthode qui permet de générer le labyrinthe avec l'algorithme d'Aldous Broder
	 */
	private void genererAldousBroder() {
		AldousBroder a = new AldousBroder();
		a.arbreCouvrant(arbreCouvrant);
		//On transforme le graphe sur lequel on a trouvé l'arbre couvrant
		transformationArbreCouvrant();
	}
	
	
	/**
	 * Méthode qui va ajouter les arêtes au graphe représentant le labyrinthe à partir
	 * du graphe sur lequel on a trouvé l'arbre couvrant. Les arêtes du graphe représentant
	 * le labyrinthe seront les arêtes utilisés dans l'arbre couvrant.
	 */
	private void transformationArbreCouvrant() {
		//Pour chaque sommet
		for (int i = 0; i < nbSommets; i++) {
			
			//Pour chaque arête du sommet
			for (Edge e : arbreCouvrant.adj(i)) {
				//Si l'arête est utilisée dans l'arbre couvrant
				if (e.used) {
					//On l'ajoute à ce graphe
					graphe.addEdge(e);
					e.used = false;
				}
				
			}
		}
	}
	
	
	/**
	 * Méthode qui permet de trouver le nombre de culs de sac dans le labyrinthe
	 * (sans compter l'entrée et la sortie)
	 */
	private void nombreCulsSac() {
		//Pour chaque sommet
		for (int i=0; i<nbSommets; i++) {
			
			//Si le sommet n'a qu'une seule arête
			if (graphe.adj(i).size() == 1) {
				//Si le sommet est différent du sommet de début ou de fin
				if (i != sommetDebut && i != sommetFin) {
					//On a un cul de sac
					nbCulsDeSacs++;
				}				
			}
		}
	}
	
	
	/**
	 * Méthode qui permet de trouver la distance entre l'entrée et la sortie
	 * @param sommet : sommet courant
	 * @param distance : distance à ce sommet
	 */
	private void distance(int sommet, int distance) {
		//On indique qu'on a visité le sommet courant
		sommetsVisites[sommet] = true;
		
		//Si le sommet courant et le sommet de fin, on note la distance trouvée
		if (sommet == sommetFin) {
			distanceDebutFin = distance;
			
		//Sinon
		} else {
			
			//Si on n'a pas trouvé la distance entre le début et la fin
			if (distanceDebutFin == 0) {
				
				//Pour chaque arête du sommet
				for (Edge e : graphe.adj(sommet)) {
					
					//Si le sommet opposé à cette arête n'a pas été visité
					if (!sommetsVisites[e.other(sommet)]) {
						
						//On réappelle la fonction à partir de ce sommet en ajoutant 1 à la distance
						distance(e.other(sommet), distance + 1);
					}
				}
			}
		}
	}

	
	/**
	 * Méthode qui lance l'exécution des données de test
	 */
	public void lancerTest() {
		nombreCulsSac();
		distance(sommetDebut, 0);
	}
	
	/**
	 * Getter du graphe représentant le labyrinthe
	 * @return graphe représentant le labyrinthe
	 */
	public Graph getGraphe() {
		return graphe;
	}
	
	
	/**
	 * Getter de la taille du labyrinthe
	 * @return taille du labyrinthe
	 */
	public int getTaille() {
		return taille;
	}


	/**
	 * Getter du nombre de culs de sac dans le labyrinthe
	 * @return le nombre de culs de sac
	 */
	public int getNbCulsDeSacs() {
		return nbCulsDeSacs;
	}


	/**
	 * Getter de la distance entre l'entrée et la sortie du labyrinthe
	 * @return distance entre l'entrée et la sortie
	 */
	public int getDistanceDebutFin() {
		return distanceDebutFin;
	}

}
