
public class Main {

	public static void main(String[] args) {
		AldousBroder algo = new AldousBroder();
    	Graph G = Graph.example();
    	algo.arbreCouvrant(G);
    	Display d = new Display();
    	d.setImage(G.toImage());
	}

}
