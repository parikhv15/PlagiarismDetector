# Plagiarism Detector

### Problem Statement

 Program that performs plagiarism detection using a N-tuple comparison algorithm allowing for synonyms in the text.

 It takes in 3 required arguments, and 1 optional argument.

 1. file name for a list of synonyms
 2. input file 1
 3. input file 2
 4. Tuple Size [Default is 3]

 Program outputs the percent of tuples in file1 which appear in file2.

Dependencies : [Java 7] (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html "JDK 7") | [Maven] (https://maven.apache.org/download.cgi "Maven Build")

### Steps to Run

1) Compile Project and generate jar
```
mvn clean package
```

2) Run Project
```
java -jar target/plagiarism-detector-0.0.1-SNAPSHOT.jar <synonym_filename> <input1_filename> <input2_filename> <N_value>
```

### License

VP

