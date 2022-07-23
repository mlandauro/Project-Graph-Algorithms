/*
Micaela Landauro
MXL190030
5/8-5/10
*/
import java.io.PrintWriter;
import java.util.ArrayList;

public class Graph {

    //Array List of Linked List
    private static ArrayList<LinkedList<Path>> graph; // graph is an arraylist of linked lists that comprise of path types
    private static LinkedList<Path> finalPaths = new LinkedList<>();
    private int size = 0; // keep track of sizes
    private int numPaths = 0;

    public Graph() { // construct
        graph = new ArrayList<LinkedList<Path>>();
    }

    // getters and setters
    public static ArrayList<LinkedList<Path>> getGraph() {
        return graph;
    }

    public static void setGraph(ArrayList<LinkedList<Path>> graph) {
        Graph.graph = graph;
    }

    public int getSize() {
        return size;
    }

    //method to add path to graph
    public void add(String [] path, int flag) {
        //CHECK THAT LOCATION IS NOT IN LIST ALREADY
        for(int i = 0; i < graph.size(); i++){
            LinkedList<Path> temp = graph.get(i);
            String location = temp.get(0).getLocation();
            if(location.equals(path[0])){//if already in list
                graph.get(i).add(new Path(Integer.parseInt(path[2]), Integer.parseInt(path[3]), path[1]));
                if(flag == 0){//make undirected, flag for no infinite loops
                    String [] pathTemp = new String[path.length];
                    pathTemp[0] = path[1];
                    pathTemp[1] = path[0];
                    pathTemp[2] = path[2];
                    pathTemp[3] = path[3];
                    add(pathTemp, 1);
                }
                return;
            }
        }
        //NOT IN GRAPH
        LinkedList<Path> newPath = new LinkedList<>();
        newPath.add(new Path(0, 0, path[0]));//add source
        newPath.add(new Path(Integer.parseInt(path[2]),Integer.parseInt(path[3]), path[1]));//add destination
        if(flag == 0){//make undirected, flag for no infinite loops
            String [] pathTemp = new String[path.length];
            pathTemp[0] = path[1];
            pathTemp[1] = path[0];
            pathTemp[2] = path[2];
            pathTemp[3] = path[3];
            add(pathTemp, 1);
        }
        graph.add(newPath);//add path

    }

    //method to print graph
    public void printGraph(){//test method
        for(int i = 0; i < graph.size(); i++){
            LinkedList<Path> temp = graph.get(i);
            System.out.print(temp.get(0).getLocation() + ": ");
            for(int j = 1; j < temp.size(); j++){
                System.out.print(temp.get(j).getLocation() + "{" + temp.get(j).getPathCost() + ", " + temp.get(j).getPathTime() + "}" +" -> ");
            }
            System.out.println();

        }
    }

    // method to print top 3 paths
    public void printPaths(char sort, PrintWriter writer){
        sortList(sort);//sort paths based on time/cost

        int loop = finalPaths.size();//less than 3 paths
        if(loop > 3)//if more than 3 paths
            loop = 3;

        for(int i = 0; i < loop; i++){
            Path temp = finalPaths.get(i);

            if(temp == null)
                return;

            //write path to file
            writer.print("Path " + (i + 1) + ": ");
            writer.print(temp.getLocation());
            writer.print("Time: " + temp.getPathTime());
            writer.println(" Cost: " + temp.getPathCost());

        }
    }

    //method to find the shortest path through iterative backtracking
    public void findShortestPaths(String source, String dest){//given a source and destination find the shortest path
        finalPaths.clear();//reset final path list
        String paths[] = new String[graph.size()];
        int index = 0;
        numPaths = 0;
        int time = 0;
        int cost = 0;
        shortestPathHelper(source, dest, paths, index, time, cost);
    }

    private void shortestPathHelper(String source, String dest, String paths[], int index, int time, int cost){
        //add path to path
        paths[index] = source;
        index++;

        if(source.equals(dest)) {//base case
            //make final path
            numPaths++;
            String pathString = "";
            for (int i = 0; i < index-1; i++)
                pathString += paths[i] + " -> ";
            pathString += paths[index - 1] + " ";
            Path finalPath = new Path(cost, time, pathString);
            finalPaths.add(finalPath);
        } else {//continue the search
            Stack<Path> stack = new Stack<>();
            int currIndex = findIndex(source);

            if(currIndex < 0){//invalid case
                return;
            }

            LinkedList<Path> temp = graph.get(currIndex);

            for(int i = 1; i < temp.size(); i++){//dont include first path b/c its source
                if (!visited(temp.get(i).getLocation(), paths))
                    stack.push(temp.get(i));//add all possible moves to stack
            }

            //recursive call to all adj vertices
            while(!stack.isEmpty()){//while still elements in stack
                Path tmp = stack.pop();//get next path
                time += tmp.getPathTime();
                cost += tmp.getPathCost();

                //recursion
                shortestPathHelper(tmp.getLocation(), dest, paths, index, time, cost);//new call with new path
                if(index < paths.length - 1)
                    paths[index + 1] = null;

                //reset time and cost math
                time -= tmp.getPathTime();
                cost -= tmp.getPathCost();
            }

        }

    }

    //method to sort final paths by time or cost
    public LinkedList<Path> sortList(char sort){//bubble sort
        Path temp;
        boolean sorted = false;

        while(!sorted){
            sorted = true;
            for(int i = 0; i < finalPaths.size()-2; i++){
                if(finalPaths.get(i).compareTo(finalPaths.get(i + 1), sort) >= 0){//sort in descending order
                    finalPaths.swap(i, i+1);
                    sorted = false;
                }
            }
        }

        return finalPaths;
    }

    //method to check if location has been visited
    private Boolean visited(String source, String paths[]){

        for(int i = 0; i < paths.length; i++){
            if(paths[i] != null && paths[i].equals(source))
                return true;
        }

        return false;
    }

    //method to find index of given location
    private int findIndex(String location){
        for(int i = 0; i < graph.size(); i++){
            LinkedList<Path> temp = graph.get(i);
            String loc = temp.get(0).getLocation();
            if(loc.equals(location)){//if already in list
                return i;
            }
        }
        return -1;//doesn't exist
    }
}