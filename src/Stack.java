/*
Micaela Landauro
MXL190030
5/8-5/10
*/
public class Stack <E>{
    private Path top;
    private int size;

    public Stack(){
        top = null;
        size = 0;
    }

    public void push(Path p){
        if(top == null)//stack empty
            top = new Path(p.getPathCost(), p.getPathTime(), p.getLocation());
        else {//stack not empty
            Path temp = new Path(p.getPathCost(), p.getPathTime(), p.getLocation());
            temp.setNext(top);
            top = temp;
        }
    }

    public Path pop(){//remove from top

        if(isEmpty())
            return null;
        size--;
        //fix pointers
        Path temp = top;
        top = top.getNext();
        temp.setNext(null);
        return temp;
    }

    public Boolean isEmpty(){
        return top == null;
    }//empty?
}
