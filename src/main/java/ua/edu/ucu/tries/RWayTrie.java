package ua.edu.ucu.tries;

public class RWayTrie implements Trie {

    private static final int R = 256;
    private Node root;
    private int size;

    private static class Node {

        protected int value;
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
            if (x.getValue() == 0) size++;
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
            if (x.value != 0) size--;
            x.setValue(0);
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.getValue() != 0) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

    @Override
    public Iterable words() {
        return wordsWithPrefix("");
    }

    public Iterable<String> wordsWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }
    private void collect(Node x, String str, Queue<String> queue) {
        if (x == null) return;
        if (x.value != 0) queue.enqueue(str);
        for (char c = 0; c < R; c++)
            collect(x.next[c], str + c, queue);
    }
    @Override
    public int size() {
        return size;
    }
}