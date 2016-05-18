package main.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by vrajp on 5/17/2016.
 */

/**
 * Class acting as Plagiarism Service.
 */
public class PlagiarismService {

    /**
     * Method to check plagiarism given the input
     * @param inputWrapper
     * @return
     */
    public double checkPlagiarism(InputWrapper inputWrapper) {
        double plagiarismRatio = 0.0;

        try {
            HashMap<String, HashSet<String>> synonymsMap = FileService.generateSynonymsMap(inputWrapper.getSynonymsFile());

            List<NTuple> nTuples1 = FileService.generateTuples(inputWrapper.getInputFile1(), inputWrapper.getN());

            List<NTuple> nTuples2 = FileService.generateTuples(inputWrapper.getInputFile2(), inputWrapper.getN());

            plagiarismRatio  = calculatePlagiarismRatio(synonymsMap, nTuples1, nTuples2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return plagiarismRatio;
    }

    /**
     * Method to calculate the ratio of plagiarism given List of N-Tuples of 2 files
     * @param synonymsMap
     * @param nTuples1
     * @param nTuples2
     * @return Plagiarism ratio
     */
    public double calculatePlagiarismRatio(HashMap<String, HashSet<String>> synonymsMap, List<NTuple> nTuples1, List<NTuple> nTuples2) {
        double count = 0;

        if (nTuples1.size() == 0)
            return count;

        for (NTuple nTuple1 : nTuples1) {
            for (NTuple nTuple2 : nTuples2) {
                if (nTuple1.isMatch(nTuple2, synonymsMap))
                    count++;
            }
        }

        return count / nTuples1.size();
    }
}
