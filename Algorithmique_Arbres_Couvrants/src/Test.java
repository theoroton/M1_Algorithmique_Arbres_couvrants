//package graphe;
import java.io.*;


import java.util.*;
public class Test{


    public static void printLaby(Graph G, int size, String file){
    {
	/* suppose que G est une grille de taille size x size et 
           crée un .tex qui contient le labyrinthe correspondant */
	
	try
	    {                      
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		writer.println("\\documentclass{article}\\usepackage{tikz}\\begin{document}");
		writer.println("\\begin{tikzpicture}");

		for (int i = 0; i < size; i++)
		    for (int j = 0; j < size; j++)
		    {			
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , j));
			writer.println("\\draw (0.1,0.1) -- (0.4,0.1);");
			writer.println("\\draw (0.6,0.1) -- (0.9,0.1);");
			writer.println("\\draw (0.1,0.9) -- (0.4,0.9);");
			writer.println("\\draw (0.6,0.9) -- (0.9,0.9);");
			writer.println("\\draw (0.1,0.1) -- (0.1, 0.4);");
			writer.println("\\draw (0.1,0.6) -- (0.1, 0.9);");
			writer.println("\\draw (0.9,0.1) -- (0.9,0.4);");
			writer.println("\\draw (0.9,0.6) -- (0.9,0.9);");
			writer.println("\\end{scope}");
		    }

		/* bord */
		for (int i = 0; i < size; i++)
		    {
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , 0));
			writer.println("\\draw(0.4,0.1) -- (0.6, 0.1);");
			writer.println("\\end{scope}");			
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , size-1));
			writer.println("\\draw(0.4,0.9) -- (0.6, 0.9);");
			writer.println("\\end{scope}");
			if (i > 0)
			    {
				writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", 0 , i));
				writer.println("\\draw(0.1,0.4) -- (0.1, 0.6);");
				writer.println("\\end{scope}");
			
			    }
			if (i < size - 1)
			    {
				writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", size -1 , i));
				writer.println("\\draw(0.9,0.4) -- (0.9, 0.6);");
				writer.println("\\end{scope}");
			
			    }
			writer.println("\\draw (0,0.4) -- (0.1, 0.4);");
			writer.println("\\draw (0,0.6) -- (0.1, 0.6);");
			writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.4)  -- ++ (-0.1, 0); ", size , size -1));
			writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.6)  -- ++ (-0.1, 0); ", size , size -1));

		    }
		
		
		for (Edge e: G.edges())
		    {
			int i = e.from % size;
			int j = e.from / size;
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , j));
			if (e.to == e.from + size){
			    /* arête verticale */
			    if (!e.used)
				{
				    writer.println("\\draw (0.4,0.9) -- (0.6,0.9);");
				    writer.println("\\draw (0.4,1.1) -- (0.6,1.1);");			    			    
				}
			    else
				{
				    writer.println("\\draw (0.4,0.9) -- (0.4,1.1);");
				    writer.println("\\draw (0.6,0.9) -- (0.6,1.1);");			    			    
				}
			}
			else{
			    /* arête horizontale */
			    
			    if (!e.used)
				{
				    writer.println("\\draw (0.9,0.4) -- (0.9,0.6);");
				    writer.println("\\draw (1.1,0.4) -- (1.1,0.6);");			    			    
				}
			    else
				{
				    writer.println("\\draw (0.9,0.4) -- (1.1,0.4);");
				    writer.println("\\draw (0.9,0.6) -- (1.1,0.6);");			    			    
				}
			    }
			    writer.println("\\end{scope}");
		    }
		writer.println("\\end{tikzpicture}");
		writer.println("\\end{document}");
		writer.close();
	    }
	catch (IOException e)
	    {
	    }                                             
    }    
	

	    
    }	
    
    public static void main(String[] args) {
	int size = 4;
	Graph G = Graph.Grid(size);
	Display d = new Display();
	d.setImage(G.toImage());
	System.out.println("appuyez sur une touche");
	new Scanner(System.in).nextLine();
	d.close();
	printLaby(G,size, "toto.tex");
	
	
    }
} 
