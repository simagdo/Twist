package de.simagdo.twist.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFile {

    private final static File POSSIBLE_TWISTS = new File("possibleTwists.txt");

    /**
     * Read the lines from the File and save it in an {@link ArrayList}
     *
     * @param file which will be read
     * @return the content of the File
     */
    public ArrayList<String> readFile(File file) {

        ArrayList<String> data = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                data.add(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Get all lines from the Text File in a @{@link List}. <br>
     * Also, it removes duplicates from the List so the Algorithm is faster <br>
     * because there are less combinations than with duplicates
     *
     * @return the Data from the @{@link List}
     */
    public List<String> getPossibleTwists() {
        return readFile(POSSIBLE_TWISTS).stream().distinct().collect(Collectors.toList());
    }

    /**
     * Add all occurrences from the twisting to the File.
     *
     * @param occurrences which were found while twisting
     */
    public void addLines(ArrayList<String> occurrences) {

        try {

            //Create a BufferedWriter which will write the occurrences to the Text File
            BufferedWriter writer = new BufferedWriter(new FileWriter(POSSIBLE_TWISTS, true));

            //Append each to occurrence to the BufferedWriter
            for (String combination : occurrences) {
                writer.append("\n").append(combination);
            }

            //Close the BufferedWriter after each occurrence has been added to it
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Add an occurrence to the twisting File
     *
     * @param occurrence which will be added
     */
    public void addLine(String occurrence) {
        try {
            //Create a BufferedWriter which will write the occurrences to the Text File
            BufferedWriter writer = new BufferedWriter(new FileWriter(POSSIBLE_TWISTS, true));

            //Append the Text to the BufferedWriter
            writer.append("\n").append(occurrence);

            //Close the BufferedWriter after the Text has been added to it
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }

    }

}
