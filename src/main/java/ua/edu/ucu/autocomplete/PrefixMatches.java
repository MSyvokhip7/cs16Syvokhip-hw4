package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int temp = trie.size();
        for (String arr : strings) {
            for (String str : arr.split(" ")) {
                if (str.length() > 2)
                    trie.add(new Tuple(str, str.length()));
            }
        }
        return trie.size() - temp;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        }
        else {
            return null;
        }
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        ArrayList<String> arrlst = new ArrayList<>();
        Iterable<String> words = trie.wordsWithPrefix(pref);

        for (String word : words) {
            if (word.length() < pref.length() + k) {
                arrlst.add(word);
            }
        }
        return arrlst;
    }

    public int size() {
        return trie.size();
    }
}
