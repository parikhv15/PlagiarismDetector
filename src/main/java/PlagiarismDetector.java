package main.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by vrajp on 5/17/2016.
 */
public class PlagiarismDetector {

    public static final int NUM_ARGS = 4;

    public static void main( String args[] ) {
        PlagiarismDetector plagiarismDetector = new PlagiarismDetector();

        InputWrapper inputWrapper = plagiarismDetector.validateInput(args);

        try {
            HashMap<String, HashSet<String>> synonymsMap = FileService.generateSynonymsMap(inputWrapper.getSynonymsFile());

            List<NTuple> nTuples1 = FileService.generateTuples(inputWrapper.getInputFile1(), inputWrapper.getN());

            List<NTuple> nTuples2 = FileService.generateTuples(inputWrapper.getInputFile2(), inputWrapper.getN());

            double plagiarismRatio  = plagiarismDetector.checkPlagiarism(synonymsMap, nTuples1, nTuples2);

            System.out.println("Plagiarism Percentage is " + plagiarismRatio * 100 + " %");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public double checkPlagiarism(HashMap<String, HashSet<String>> synonymsMap, List<NTuple> nTuples1, List<NTuple> nTuples2) {
        double count = 0;

        for (NTuple nTuple1 : nTuples1) {
            for (NTuple nTuple2 : nTuples2) {
                if (nTuple1.isMatch(nTuple2, synonymsMap))
                    count++;
            }
        }

        return count / nTuples1.size();
    }

    public InputWrapper validateInput(String args[]) {

        if (args.length < NUM_ARGS - 1) {
            System.out.println("Insufficient Arguments..");
            System.exit(1);
        }

        InputWrapper inputWrapper = new InputWrapper();

        inputWrapper.setSynonymsFile(args[0]);
        inputWrapper.setInputFile1(args[1]);
        inputWrapper.setInputFile2(args[2]);

        if (args.length == NUM_ARGS)
            inputWrapper.setN(Integer.parseInt(args[3]));

        return inputWrapper;
    }
}


