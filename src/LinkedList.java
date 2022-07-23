/*
Micaela Landauro
MXL190030
5/8-5/10
*/
public class LinkedList <E> {
    private Path head = null;
    private Path tail = null;
    private int size = 0;

    //method to add path to list
    public void add(Path path){
        if(head == null){ // list is empty
            head = path;
        } else {
            tail.setNext(path);
        }
        tail = path;
        tail.setNext(null);
        size++;
    }

    //setters and getters
    public Path getHead() {return head;}
    public void setHead(Path head) {this.head = head;}

    public Path getTail() {return tail;}

    //swap two elements given their indices
    public void swap(int index1, int index2){
        if(index1 == index2)
            return;
        Path wantedX = get(index1);
        Path prevX = null, currX = head;
        while(currX != null && currX.getLocation() != wantedX.getLocation()){//find x element
            prevX = currX;
            currX = currX.getNext();
        }

        Path wantedY = get(index2);
        Path prevY = null, currY = head;
        while(currY != null && currY.getLocation() != wantedY.getLocation()){//find y element
            prevY = currY;
            currY = currY.getNext();
        }

        if(currX == null || currY == null)//either null
            return;

        //fix pointers
        if(prevX != null)
            prevX.setNext(currY);
        else
            setHead(currY);

        if(prevY != null)
            prevY.setNext(currX);
        else
            setHead(currX);

        //swap elements
        Path temp = currX.getNext();
        currX.setNext(currY.getNext());
        currY.setNext(temp);
    }

    //return path at given location
    public Path get(int index){
        if(index > size || index < 0)
            return null;
        Path temp = head;
        for(int i = 0; i < index; i++){
            if(temp.getNext() == null)
                return null;
            temp = temp.getNext();
        }
        return temp;
    }

    //clear list
    public void clear(){
        head = null;
    }

    public int size(){
        return size;
    }
}
