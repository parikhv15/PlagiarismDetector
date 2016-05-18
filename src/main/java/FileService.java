package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vrajp on 5/17/2016.
 */
public class FileService {

    /**
     * Method to generate a Synonyms Map for each line of Synonym file
     * @param synonymsFileName
     * @return Map containing <word, set of synonyms>
     * @throws IOException
     */
    public static HashMap<String, HashSet<String>> generateSynonymsMap(String synonymsFileName) throws IOException {
        HashMap<String, HashSet<String>> synonymsMap = new HashMap<String, HashSet<String>>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(synonymsFileName));

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\s+");

                HashSet<String> wordSet = new HashSet<String>(Arrays.asList(words));

                for (String word : words) {
                    if (!synonymsMap.containsKey(word))
                        synonymsMap.put(word, wordSet);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("Invalid File Name: " + synonymsFileName);
            System.out.println();
            System.exit(1);
        } finally {
            if (br != null)
                br.close();
        }

        return synonymsMap;
    }

    /**
     * Method to generate a List of N-Tuples of a file
     * @param fileName
     * @param N tuples
     * @return List of all the N-Tuples in the file
     * @throws IOException
     */
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
            System.out.println();
            System.out.println("Invalid File Name: " + fileName);
            System.out.println();
            System.exit(1);
        } finally {
            if (br != null)
                br.close();
        }

        return nTuples;
    }

    /**
     * Method to convert line to N-Tuples
     * @param line of the file
     * @param N tuples
     * @return List of N-Tuples of each line
     */
    public static List<NTuple> convertLineToNTuples (String line, int N) {
        List<NTuple> nTuples = new ArrayList<NTuple>();

//        String[] words = line.toLowerCase().split(" ");
        String[] words = extractWords(line);

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

    /**
     * Method to clean line of punctuations and extract words
     * @param line
     * @return array of words
     */
    public static String[] extractWords(String line) {
        ArrayList<String> wordList = new ArrayList<String>();

        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(line);

        while ( m.find() ) {
            String word = line.substring(m.start(), m.end());

            wordList.add(word);
        }

        String words[] = new String[wordList.size()];

        wordList.toArray(words);

        return words;
    }
}
