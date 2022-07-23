/*
Micaela Landauro
MXL190030
5/8-5/10
*/
public class Path {//node element
    private int pathCost; // weight of the path
    private int pathTime;
    private String location; // location of the path
    private Path next;

    public Path(int pathCost, int pathTime, String location) {
        this.pathCost = pathCost;
        this.pathTime = pathTime;
        this.location = location;
        this.next = null;
    }

    //setters and getters
    public int getPathCost() {
        return pathCost;
    }

    public int getPathTime(){
        return pathTime;
    }

    public String getLocation(){
        return location;
    }

    public void setNext(Path next){
        this.next = next;
    }

    public Path getNext() {return next;}

    public int compareTo(Path p, char sort){

        if(p == null)
            return -1;

        if (sort == 'T'){
            if(p.getPathTime() > this.getPathTime())
                return -1;
            else if(p.getPathTime() < this.getPathTime())
                return 1;
            else
                return 0;
        } else if (sort == 'C') {
            if(p.getPathCost() > this.getPathCost())
                return -1;
            else if(p.getPathCost() < this.getPathCost())
                return 1;
            else
                return 0;
        }
        return -1;
    }

}