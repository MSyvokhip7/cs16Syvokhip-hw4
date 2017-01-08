package ua.edu.ucu.tries;

public interface Trie {

    public void add(Tuple word);

    public boolean contains(String word);

    public boolean delete(String word);

    public Iterable words();

    public Iterable wordsWithPrefix(String pref);
    
    public int size();
}
