
public class Main {

	public static void main(String[] args) {
		Graph G;
		
		G = Graph.exampleG0();
		//G = Graph.exampleG1();
		
		//Kruskal k = new Kruskal(); k.arbreCouvrant(G);
		//AldousBroder a = new AldousBroder(); a.arbreCouvrant(G);
		Wilson w = new Wilson(); w.arbreCouvrant(G);
    	
    	Display d = new Display();
    	d.setImage(G.toImage());
	}

}
