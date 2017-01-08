package ua.edu.ucu.tries.immutable;


/**
 * Created by TheOriginMS7 on 03.11.2016.
 */

public class Node implements Cloneable {
    Node next = null;
    String data;

    public Node(String data) {
        this.data = data;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Node clone(){
        return new Node((String) this.data);
    }

    public String getData(){
        return data;
    }

}
