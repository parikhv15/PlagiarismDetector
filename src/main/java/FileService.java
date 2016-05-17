package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by vrajp on 5/17/2016.
 */
public class FileService {

    public static HashMap<String, HashSet<String>> generateSynonymsMap(String synonymsFileName) throws IOException {
        HashMap<String, HashSet<String>> synonymsMap = new HashMap<String, HashSet<String>>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(synonymsFileName));

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.toLowerCase().split(" ");

                HashSet<String> wordSet = new HashSet<String>(Arrays.asList(words));

                for (String word : words) {
                    if (!synonymsMap.containsKey(word))
                        synonymsMap.put(word, wordSet);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                br.close();
        }

        return synonymsMap;
    }

    public static List<NTuple> generateTuples(String fileName, int N) throws IOException {
        List<NTuple> nTuples = new ArrayList<NTuple>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                nTuples.addAll(convertLineToNTuples(line, N));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                br.close();
        }

        return nTuples;
    }

    public static List<NTuple> convertLineToNTuples (String line, int N) {
        List<NTuple> nTuples = new ArrayList<NTuple>();

        String[] words = line.toLowerCase().split(" ");

        if (words.length < N)
            return nTuples;

        for (int i = 0; i <= words.length - N; i++) {

            NTuple nTuple = new NTuple(N);

            for (int j = i ; j < i + N; j++) {
                nTuple.addWord(words[j]);
            }

            nTuples.add(nTuple);
        }

        return nTuples;
    }
}
