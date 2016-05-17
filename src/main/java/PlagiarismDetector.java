package main.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by vrajp on 5/17/2016.
 */

/**
 * Main Class
 */
public class PlagiarismDetector {

    public static final int NUM_ARGS = 4;

    public static void main( String args[] ) {
        PlagiarismDetector plagiarismDetector = new PlagiarismDetector();

        PlagiarismService plagiarismService = new PlagiarismService();

        InputWrapper inputWrapper = plagiarismDetector.validateInput(args);

        double plagiarismRatio  = plagiarismService.checkPlagiarism(inputWrapper);

        System.out.println("Plagiarism Percentage is " + plagiarismRatio * 100 + " %");

    }

    /**
     * Method to validate the command-line arguments
     * @param args
     * @return InputWrapper
     */
    public InputWrapper validateInput(String args[]) {

        if (args.length < NUM_ARGS - 1) {
            System.out.println("Insufficient Arguments..");
            System.out.println();
            System.out.println("Usage: java -jar <name>.jar <synonym_filename> <input1_filename> <input2_filename> <N_value>");
            System.exit(1);
        }

        InputWrapper inputWrapper = new InputWrapper();

        inputWrapper.setSynonymsFile(args[0]);
        inputWrapper.setInputFile1(args[1]);
        inputWrapper.setInputFile2(args[2]);

        if (args.length == NUM_ARGS) {
            if (isInteger(args[3]));
                inputWrapper.setN(Integer.parseInt(args[3]));
        }

        return inputWrapper;
    }

    /**
     * Method to check if a String is Integer or not
     * @param input
     * @return boolean
     */
    public boolean isInteger( String input )
    {
        try {
            Integer.parseInt( input );
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


