
public class Main {

	public static void main(String[] args) {
		AldousBroder a = new AldousBroder();
    	Graph G = Graph.exampleG2();
    	a.arbreCouvrant(G);
    	Display d = new Display();
    	d.setImage(G.toImage());
	}

}
