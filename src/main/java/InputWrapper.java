package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrajp on 5/17/2016.
 */

class InputWrapper {
    private String inputFile1;
    private String inputFile2;

    private String synonymsFile;


    private int N;

    InputWrapper () {
        this.N = 3; // Default Value
    }

    public String getSynonymsFile() {
        return synonymsFile;
    }

    public void setSynonymsFile(String synonymsFile) {
        this.synonymsFile = synonymsFile;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public String getInputFile1() {
        return inputFile1;
    }

    public void setInputFile1(String inputFile1) {
        this.inputFile1 = inputFile1;
    }

    public String getInputFile2() {
        return inputFile2;
    }

    public void setInputFile2(String inputFile2) {
        this.inputFile2 = inputFile2;
    }
}