
/**
 * 
 * Représente une arête non orientée
 *
 */
class Edge {
	
	//Premier sommet de l'arête
	int from;
	//Second sommet de l'arête
   	int to;
   
   	//Booléen qui indique si l'arête est utilisée dans un arbre couvrant
   	boolean used;
   
   	/**
   	 * Constructeur d'une arête
   	 * @param x : premier sommet
   	 * @param y : second sommet
   	 */
    Edge(int x, int y) {
    	this.from = x;
    	this.to = y;
    	this.used = false;
    }
    
    /**
     * Méthode qui indique le sommet à l'autre extremité d'une arête
     * @param v : sommet de l'arête
     * @return sommet opposé
     */
    final int other(int v) {
    	if (this.from == v) {
    		return this.to; 
    		
    	} else {
    		return this.from;
    	}
    }
    
    /**
     * Méthode equals qui teste si l'arête possède les sommets u -> v
     * @param u : sommet from
     * @param v : sommet to
     * @return true si l'arête possède les sommets u -> v
     */
    boolean equals(int u, int v) {
    	return ((u== from) && (v == to));
    }
}
