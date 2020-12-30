import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

/**
 * 
 * Représente un graphe
 *
 */
class Graph{
	
	//Tableau des listes des arêtes reliées à chaque sommet
	private ArrayList<Edge>[] adj;
	//Tableau des coordonnées X des sommets
	private int[] coordX;
	//Tableau des coordonnées Y des sommets
	private int[] coordY;
	//Nombre de sommets
   	private final int V;
   	//Nombre d'arêtes
   	private int E;

   	/**
   	 * Constructeur du graphe
   	 * @param N : nombre de sommets
   	 */
   	@SuppressWarnings("unchecked")
   	public Graph(int N) {
	     this.V = N;
	     this.E = 0;
	     
	     adj = (ArrayList<Edge>[]) new ArrayList[N];
	     
	     for (int v= 0; v < N; v++) {
	    	 adj[v] = new ArrayList<Edge>();
	     }
	     
	     coordX = new int[N];
	     coordY = new int[N];
	     
	     for (int v= 0; v < N; v++) {
	    	 coordX[v] = 0; 
	     }
		 
	     for (int v= 0; v < N; v++) {
	    	 coordY[v] = 0;
	     }
   	}
    
   	/**
   	 * Retourne le nombre de sommets
   	 * @return
   	 */
   	public int vertices() {
   		return V;
    }

    public void setCoordinate(int i, int x, int y) {
    	coordX[i] = x;
	    coordY[i] = y;
    }
    
    /**
     * Méthode qui permet d'ajouter une arête au graphe
     * @param e : arête à ajouter
     */
    public void addEdge(Edge e) {
    	int v = e.from;
        int w = e.to;
        adj[v].add(e);
        adj[w].add(e);
    }
   
    /**
     * Méthode qui renvoie la liste des arêtes reliées à un sommet
     * @param v : sommet
     * @return liste des arêtes reliées au sommet v
     */
    public ArrayList<Edge> adj(int v) {
	   	return new ArrayList<Edge>(adj[v]);
    }

    /**
     * Méthode qui renvoie toutes les arêtes du graphe
     * @return liste des arêtes du graphe
     */
    public ArrayList<Edge> edges() {
	   	ArrayList<Edge> list = new ArrayList<Edge>();
	   
	   	for (int v = 0; v < V; v++) {
	   		
	   		for (Edge e : adj(v)) {
	   			
	   			if (e.from == v) {
                    list.add(e);
	   			}
	   			
            }
	   		
	   	}
       
       return list;
    }
       
    /**
     * Méthode qui renvoie le graphe G0
     * @return graphe G0
     */
    static Graph exampleG0() {
    	Graph g = new Graph(7);
    	//Sommets
    	g.setCoordinate(0, 50, 250);
    	g.setCoordinate(1, 150, 250);
    	g.setCoordinate(2, 250, 250);
    	g.setCoordinate(3, 150, 150);
    	g.setCoordinate(4, 250, 150);
    	g.setCoordinate(5, 350, 250);
    	g.setCoordinate(6, 250, 50);
    	//Arêtes
    	g.addEdge(new Edge(0,1));
    	g.addEdge(new Edge(0,3));
    	g.addEdge(new Edge(1,2));
    	g.addEdge(new Edge(1,3));
    	g.addEdge(new Edge(2,4));
    	g.addEdge(new Edge(2,5));
    	g.addEdge(new Edge(3,4));
    	g.addEdge(new Edge(3,6));
    	g.addEdge(new Edge(4,5));
    	g.addEdge(new Edge(4,6));
    	g.addEdge(new Edge(5,6));
    	
    	return g;
    }

    /**
     * Méthode qui renvoie le graphe G1
     * @return graphe G1
     */
    static Graph exampleG1() {
    	Graph g = new Graph(4);
    	//Sommets
    	g.setCoordinate(0, 100,100);
    	g.setCoordinate(1, 300,300);
    	g.setCoordinate(2, 300,100);
		g.setCoordinate(3, 100,300);
		//Arêtes
		g.addEdge(new Edge(0,1));
		g.addEdge(new Edge(0,2));
		g.addEdge(new Edge(0,3));
		g.addEdge(new Edge(1,2));
		g.addEdge(new Edge(1,3));
		
		return g;
    }

    /**
     * Méthode qui renvoie le graphe G2
     * @return graphe G2
     */
    static Graph exampleG2() {
    	Graph g = new Graph(4);
    	//Sommets
    	g.setCoordinate(0, 100,100);
    	g.setCoordinate(1, 300,300);
    	g.setCoordinate(2, 300,100);
		g.setCoordinate(3, 100,300);
		//Arêtes
		g.addEdge(new Edge(0,2));
		g.addEdge(new Edge(0,3));
		g.addEdge(new Edge(1,2));
		g.addEdge(new Edge(1,3));
		
		return g;
    }

    /**
     * Méthode qui renvoie une grille de n * n
     * @param n
     * @return grille n * n
     */
    static Graph Grid(int n){
    	Graph g = new Graph(n*n);
    	int i,j;
    	
		for (i = 0 ; i < n; i ++) {
			
		    for (j = 0 ; j < n; j ++) {
		    	
		    	g.setCoordinate(n*i+j, 50+(300*i)/n,50+(300*j)/n);
		    }
		    
		}
	
		for (i = 0 ; i < n; i ++) {
			
		    for (j = 0 ; j < n; j ++){
		    	
				if (i < n-1) { g.addEdge(new Edge(n*i+j,n*(i+1)+j)); }
				if (j < n-1) { g.addEdge(new Edge(n*i+j,n*i+j+1)); }
		    }
		    
		}
		return g;
    }
    

    public BufferedImage toImage(){
    	BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2d = image.createGraphics();
    	g2d.setBackground(Color.WHITE);
    	g2d.fillRect(0, 0, 400, 400);
    	g2d.setColor(Color.BLACK);
    	BasicStroke bs = new BasicStroke(2);
    	g2d.setStroke(bs);
    	
		// dessine les arêtes 
		for (Edge e: edges()) {
			int i = e.from;
			int j = e.to;
			
			if (e.used) {
			    g2d.setColor(Color.RED);
			    
			}else {
			    g2d.setColor(Color.GRAY);
			}  
			
			g2d.drawLine(coordX[i], coordY[i], coordX[j], coordY[j]);
		}
		
		// dessine les sommets 
		for (int i = 0; i < V; i++) {
			g2d.setColor(Color.WHITE);
			g2d.fillOval(coordX[i]-15, coordY[i]-15,30,30);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(coordX[i]-15, coordY[i]-15,30,30);
			g2d.drawString(Integer.toString(i), coordX[i]-5, coordY[i]+5);
		}

		return image;
    }

    /**
     * Méthode qui écrit dans un fichier le résultat
     * @param s
     */
    public void writeFile(String s) {
		try {                      
			PrintWriter writer = new PrintWriter(s, "UTF-8");
			writer.println("digraph G{");
			
			for (Edge e: edges()) {
			    writer.println(e.from + "->" + e.to+";");
			}
			
			writer.println("}");
			writer.close();
			
    	} catch (IOException e){}                                             
    } 
       
    /**
     * Méthode qui remet à zéro l'arbre couvrant
     */
    public void resetArbreCouvrant() {
    	for (Edge e : edges()) {
    		e.used = false;
    	}
    }
    
    /**
     * Méthode qui renvoie la liste des arêtes prises dans l'arbre couvrant
     * @return liste des arêtes de l'arbre couvrant
     */
    public ArrayList<Edge> aretesArbreCouvrant() {
    	ArrayList<Edge> list = new ArrayList<Edge>();
    	
    	for (Edge e : edges()) {
    		
    		if (e.used) {
    			list.add(e);
    		}
    	}
    	
    	return list;
    }
}
