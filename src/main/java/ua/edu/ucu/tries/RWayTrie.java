package ua.edu.ucu.tries;

public class RWayTrie implements Trie {

    private static final int R = 256;
    private Node root;
    private int size;

    private static class Node {

        protected int value = -1;
        private Node[] next = new Node[R];
        private int weight;

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private Node put(Node x, Tuple t, int d) {
        if (x == null) x = new Node();
        if (t.term.length() == d) {
            if (x.getValue() == -1) size++;
            x.setValue(t.weight);
            return x;
        }
        char c = t.term.charAt(d);
        x.next[c] = put(x.next[c], t, d+1);
        return x;
    }

    @Override
    public void add(Tuple t) {
        root = put(root, t, 0);
    }

    public Node get(Node x, String key, int depth) {
        if (x == null) return null;
        if (depth == key.length()) return x;
        char c = key.charAt(depth);
        return get(x.next[c], key, depth + 1);
    }

    @Override
    public boolean contains(String word) {
        Node x = get(root, word, 0);
        return x != null && x.getValue() != 0;
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
        return true;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.getValue() != -1) size--;
            x.setValue(-1);
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.getValue() != -1) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

    @Override
    public Iterable words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable wordsWithPrefix(String s) {
        Queue lst = new Queue();
        Node x = get(root, s, 0);
        collect(x, new StringBuilder(s), lst);
        return lst.toIterable();
    }

    private void collect(Node x, StringBuilder s, Queue lst) {
        if (x == null) return;
        if (x.getValue() != -1) lst.enqueue(s.toString());
        for (char c = 0; c < R; c++) {
            s.append(c);
            collect(x.next[c], s, lst);
            s.deleteCharAt(s.length() - 1);
        }
    }

    @Override
    public int size() {
        return size;
    }
}