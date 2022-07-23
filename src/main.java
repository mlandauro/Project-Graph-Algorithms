/*
Micaela Landauro
MXL190030
5/8-5/10
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class main {
    public static void main (String [] args) {
        Scanner in = new Scanner(System.in);

        //read file and create graph
        try{
            System.out.print("Enter Graph File Name: ");
            String graphFile = in.next();
            //create graph
            Graph graph = createGraph(new Scanner(new File(graphFile)));

            //read path file and validate paths
            System.out.print("Enter Path File Name: ");
            String pathFile = in.next();

            //iterative back tracking method
            findPath(new Scanner(new File(pathFile)), graph);

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //method to create graph
    public static Graph createGraph(Scanner inGraph){
        Graph graph = new Graph();
        int lines = Integer.parseInt(inGraph.nextLine());

        for(int i = 0; i < lines; i++){
            String line = inGraph.nextLine();
            String[] s = line.split("\\|", 4);
            //add path to graph, undirected
            graph.add(s, 0);
        }
        return graph;
    }

    //method to output paths to file
    public static void findPath(Scanner inPath, Graph graph){
        //output to file
        File file = new File("outputFile.txt");
        try {
            file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            PrintWriter writer = new PrintWriter(file);

        // read in paths wanted from file
        int numPaths = Integer.parseInt(inPath.nextLine());
        for(int i = 0; i < numPaths; i++){
            String srcDest = inPath.nextLine();
            String [] s = srcDest.split("\\|", 3);

            writer.print("Flight: " + (i + 1) + ": " + s[0] + ", " + s[1]);
            if(s[2].charAt(0) == 'T')
                writer.println(" (Time)");
            else if (s[2].charAt(0) == 'C')
                    writer.println(" (Cost)");

            //System.out.println();

            //exhaustive search for all paths
            graph.findShortestPaths(s[0], s[1]);
            //print top 3 to file
            graph.printPaths(s[2].charAt(0), writer);
            writer.println();
        }
        writer.close();
        } catch(FileNotFoundException f){
            f.printStackTrace();
        }
    }
}
