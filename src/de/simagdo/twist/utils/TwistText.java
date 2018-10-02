package de.simagdo.twist.utils;

import de.simagdo.twist.objects.PossibleTwists;
import de.simagdo.twist.objects.TextMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwistText {

    private ReadFile readFile = new ReadFile();

    public ArrayList<String> twist(ArrayList<String> input) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<TextMatch> textMatches = new ArrayList<>();
        ArrayList<Character> shuffle = new ArrayList<>();
        String line;
        TextMatch textMatch = new TextMatch("", "", "", "", "", "");
        ArrayList<String> possibleTwists = new ArrayList<>();

        //Loop over the Input List
        for (String meta : input) {

            System.out.println("Meta: " + meta);

            //Split the current line at Space
            String[] parts = meta.split(" ");

            //Loop over all parts separately
            for (String part : parts) {

                line = "";

                System.out.println("Part: " + part);

                if (part.length() > 3) {

                    for (int i = 0; i < part.length(); i++) {
                        if (!Character.isAlphabetic(part.charAt(i)) && textMatch.getFirstLetter().equals("")) {
                            textMatch.setFirstDigit(textMatch.getFirstDigit() + String.valueOf(part.charAt(i)));
                        }

                        if (Character.isAlphabetic(part.charAt(i)) && textMatch.getFirstLetter().equals("")) {
                            textMatch.setFirstLetter(String.valueOf(part.charAt(i)));
                        }

                        if (Character.isAlphabetic(part.charAt(i)) && !textMatch.getFirstLetter().equals("")) {
                            textMatch.setLastLetter(String.valueOf(part.charAt(i)));
                        }

                        if (!Character.isAlphabetic(part.charAt(i)) && !textMatch.getLastLetter().equals("")) {
                            textMatch.setLastDigit(textMatch.getLastDigit() + String.valueOf(part.charAt(i)));
                        }

                    }

                    textMatch.setBetween(part.substring((textMatch.getFirstDigit().length() + textMatch.getFirstLetter().length()), (part.length() - (textMatch.getLastLetter().length() + textMatch.getLastDigit().length()))));

                    for (int i = 0; i < textMatch.getBetween().length(); i++) {
                        shuffle.add(textMatch.getBetween().charAt(i));
                    }

                    //Set the origin text
                    textMatch.setOriginalText(textMatch.getFirstLetter() + textMatch.getBetween() + textMatch.getLastLetter());

                    //Shuffle the Characters between the First and Last Word
                    Collections.shuffle(shuffle);

                    for (Character character : shuffle) line += character;

                    shuffle.clear();

                    textMatch.setBetween(line);

                    //line = textMatch.getFirstLetter() + textMatch.getBetween() + textMatch.getLastLetter();

                    //Save the Twist in the XML File
                    possibleTwists.add(textMatch.getOriginalText());

                } else {
                    textMatch.setBetween(part);
                }

                System.out.println(textMatch.toString());

                textMatches.add(textMatch);
                textMatch = new TextMatch("", "", "", "", "", "");

            }

            line = "";

            //Add the lines to the line
            for (TextMatch match : textMatches)
                line += match.getFirstDigit() + match.getFirstLetter() + match.getBetween() + match.getLastLetter() + match.getLastDigit() + " ";

            //Add the current line to the Output
            output.add(line);

            textMatches.clear();
            textMatch = new TextMatch("", "", "", "", "");

        }

        //Write all Twists to the Text File
        readFile.addLines(possibleTwists);

        return output;
    }

    public ArrayList<String> endTwist(ArrayList<String> input) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<TextMatch> textMatches = new ArrayList<>();
        ArrayList<Character> shuffle = new ArrayList<>();
        String line;
        TextMatch textMatch = new TextMatch("", "", "", "", "", "");
        ArrayList<PossibleTwists> possibleTwists = new ArrayList<>();
        List<String> result = readFile.getPossibleTwists();

        //Loop over the Input List
        for (String meta : input) {

            //System.out.println("Meta: " + meta);

            //Split the current line at Space
            String[] parts = meta.split(" ");

            //Loop over all parts separately
            for (String part : parts) {

                line = "";

                //System.out.println("Part: " + part);

                if (part.length() > 3) {

                    for (int i = 0; i < part.length(); i++) {
                        if (!Character.isAlphabetic(part.charAt(i)) && textMatch.getFirstLetter().equals("")) {
                            textMatch.setFirstDigit(textMatch.getFirstDigit() + String.valueOf(part.charAt(i)));
                        }

                        if (Character.isAlphabetic(part.charAt(i)) && textMatch.getFirstLetter().equals("")) {
                            textMatch.setFirstLetter(String.valueOf(part.charAt(i)));
                        }

                        if (Character.isAlphabetic(part.charAt(i)) && !textMatch.getFirstLetter().equals("")) {
                            textMatch.setLastLetter(String.valueOf(part.charAt(i)));
                        }

                        if (!Character.isAlphabetic(part.charAt(i)) && !textMatch.getLastLetter().equals("")) {
                            textMatch.setLastDigit(textMatch.getLastDigit() + String.valueOf(part.charAt(i)));
                        }

                    }

                    textMatch.setBetween(part.substring((textMatch.getFirstDigit().length() + textMatch.getFirstLetter().length()), (part.length() - (textMatch.getLastLetter().length() + textMatch.getLastDigit().length()))));

                    for (int i = 0; i < textMatch.getBetween().length(); i++) {
                        shuffle.add(textMatch.getBetween().charAt(i));
                    }

                    //Set the origin text
                    textMatch.setOriginalText(textMatch.getFirstLetter() + textMatch.getBetween() + textMatch.getLastLetter());

                    line = textMatch.getFirstLetter() + textMatch.getBetween() + textMatch.getLastLetter();

                    //Save the Twist in the XML File
                    possibleTwists.add(new PossibleTwists(textMatch.getOriginalText(), line));

                } else {
                    textMatch.setBetween(part);
                }

                //System.out.println(textMatch.toString());

                textMatches.add(textMatch);
                textMatch = new TextMatch("", "", "", "", "", "");

            }

            line = "";

            //Add the lines to the line
            for (TextMatch match : textMatches) {
                if (match.getFirstLetter().equalsIgnoreCase(""))
                    line += match.getLastDigit() + match.getBetween() + match.getLastDigit() + " ";
                else
                    line += match.getFirstDigit() + printPermutationsIterative(match.getFirstLetter(), match.getBetween(), match.getLastLetter()) + match.getLastDigit() + " ";
                //System.out.println("Current line: " + line);
            }

            //Add the current line to the Output
            output.add(line);

            textMatches.clear();
            textMatch = new TextMatch("", "", "", "", "");

        }

        return output;
    }

    private String printPermutationsIterative(String first, String string, String last) {
        ReadFile readFile = new ReadFile();
        List<String> possibleTwists = readFile.getPossibleTwists();
        String result = "";
        int[] factorials = new int[string.length() + 1];
        factorials[0] = 1;
        for (int i = 1; i <= string.length(); i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        for (int i = 0; i < factorials[string.length()]; i++) {
            String onePermutation = "";
            String temp = string;
            int positionCode = i;
            for (int position = string.length(); position > 0; position--) {
                int selected = positionCode / factorials[position - 1];
                onePermutation += temp.charAt(selected);
                positionCode = positionCode % factorials[position - 1];
                temp = temp.substring(0, selected) + temp.substring(selected + 1);
            }

            if (possibleTwists.contains(first + onePermutation + last)) {
                //System.out.println("===================" + first + onePermutation + last + "===================");
                result = first + onePermutation + last;
                break;
            }
            //System.out.println(onePermutation);
        }
        return result;
    }

    public ArrayList<String> twistText(ArrayList<String> input) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        char firstLetter;
        char lastLetter;
        String between;
        ArrayList<Character> shuffle = new ArrayList<>();
        int start;
        int end;
        String result;
        String line;

        for (String meta : input) {
            String[] parts = meta.split(" ");
            for (String part : parts) {
                if (part.length() > 3) {
                    if (!part.matches(".*\\d+.*")) {
                        if (Character.isAlphabetic(part.charAt(0))) {
                            firstLetter = part.charAt(0);
                            start = 1;
                        } else {
                            firstLetter = part.charAt(1);
                            start = 2;
                        }

                        if (Character.isAlphabetic(part.charAt(part.length() - 1))) {
                            lastLetter = part.charAt(part.length() - 1);
                            end = part.length();
                        } else {
                            lastLetter = part.charAt(part.length() - 2);
                            end = part.length() - 2;
                        }

                        between = part.substring(start, end);

                        System.out.print(firstLetter + " " + between + " " + lastLetter + " ");

                        for (int i = 0; i < between.length(); i++) {
                            shuffle.add(between.charAt(i));
                        }

                        //Shuffle the Characters between the First and Last Word
                        Collections.shuffle(shuffle);

                        //Build the Word
                        result = String.valueOf(firstLetter);
                        for (Character character : shuffle) {
                            result += character;
                        }
                        result += String.valueOf(lastLetter);

                        //Clear the Shuffle List
                        shuffle.clear();

                        //Add the Result to the lines List
                        lines.add(result);
                    } else {
                        System.out.print(part + " ");
                        lines.add(part);
                    }
                    //The Length of the Word is smaller or equal than 3
                } else {
                    System.out.print(part + " ");
                    lines.add(part);
                }
            }

            System.out.println();

            //Initialize the line
            line = "";

            //Add the lines to the line
            for (String string : lines) {
                line += string + " ";
            }

            //Add the current line to the Output
            output.add(line);

            //Clear the Lines List
            lines.clear();
        }

        return output;
    }

}
