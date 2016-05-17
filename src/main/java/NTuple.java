package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by vrajp on 5/17/2016.
 */
public class NTuple {
    private List<String> words;

    private int N;

    public NTuple(int N) {
        words = new ArrayList<String>();
        this.N = N;
    }

    public void addWord(String word) {
        words.add(word);
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public boolean isMatch(NTuple nTuple, HashMap<String, HashSet<String>> synonymsMap) {
        List<String> words1 = this.getWords();
        List<String> words2 = nTuple.getWords();

        if (words1.size() != words2.size())
            return false;

        for (int i = 0; i < N; i++) {
            String word1 = words1.get(i);
            String word2 = words2.get(i);

            if (!word1.equals(word2)) {

                if (!synonymsMap.containsKey(word1))
                    return false;

                HashSet<String> synonymsSet = synonymsMap.get(word1);

                if (!synonymsSet.contains(word2))
                    return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return words.toString();
    }
}
