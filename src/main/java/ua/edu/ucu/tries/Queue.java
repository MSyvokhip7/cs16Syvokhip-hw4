package ua.edu.ucu.tries;

import ua.edu.ucu.tries.immutable.ImmutableLinkedList;
import java.util.Iterator;


/**
 * Created by TheOriginMS7 on 04.01.2017.
 */
public class Queue<Item> implements Iterable<Item>{
    public ImmutableLinkedList myList = new ImmutableLinkedList();

    public String peek(){
        return myList.getFirst();
    }

    public String dequeue(){
        String first = peek();
        myList = myList.remove(0);
        return first;
    }

    public void enqueue(String e){
        myList = myList.addLast(e);
    }

    public String toString(){
        return myList.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

     public Iterable<String> toIterable() {
        return () -> new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public String next() {
                return dequeue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    boolean isEmpty() {
        return myList.isEmpty();
    }
}
