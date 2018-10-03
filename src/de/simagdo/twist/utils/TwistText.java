package de.simagdo.twist.utils;

import de.simagdo.twist.objects.TextMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwistText {

    private ReadFile readFile = new ReadFile();

    public ArrayList<String> twist(ArrayList<String> input, TwistMode mode) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<TextMatch> textMatches = new ArrayList<>();
        ArrayList<Character> shuffle = new ArrayList<>();
        String line;
        TextMatch textMatch = new TextMatch("", "", "", "", "", "");
        ArrayList<String> occurrences = new ArrayList<>();

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
                            textMatch.setFirstDigits(textMatch.getFirstDigits() + String.valueOf(part.charAt(i)));
                        }

                        if (Character.isAlphabetic(part.charAt(i)) && textMatch.getFirstLetter().equals("")) {
                            textMatch.setFirstLetter(String.valueOf(part.charAt(i)));
                        }

                        if (Character.isAlphabetic(part.charAt(i)) && !textMatch.getFirstLetter().equals("")) {
                            textMatch.setLastLetter(String.valueOf(part.charAt(i)));
                        }

                        if (!Character.isAlphabetic(part.charAt(i)) && !textMatch.getLastLetter().equals("")) {
                            textMatch.setLastDigits(textMatch.getLastDigits() + String.valueOf(part.charAt(i)));
                        }

                    }

                    textMatch.setBetween(part.substring((textMatch.getFirstDigits().length() + textMatch.getFirstLetter().length()), (part.length() - (textMatch.getLastLetter().length() + textMatch.getLastDigits().length()))));

                    if (mode.equals(TwistMode.TWIST)) {
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

                        //Save the Twist in the XML File
                        occurrences.add(textMatch.getOriginalText());

                    }

                } else {
                    textMatch.setBetween(part);
                }

                System.out.println(textMatch.toString());

                textMatches.add(textMatch);
                textMatch = new TextMatch("", "", "", "", "", "");

            }

            line = "";

            if (mode.equals(TwistMode.TWIST)) {
                //Add the lines to the line
                for (TextMatch match : textMatches)
                    line += match.getFirstDigits() + match.getFirstLetter() + match.getBetween() + match.getLastLetter() + match.getLastDigits() + " ";

            } else if (mode.equals(TwistMode.ENDTWIST)) {
                //Add the lines to the line
                for (TextMatch match : textMatches) {
                    if (match.getFirstLetter().equalsIgnoreCase(""))
                        line += match.getLastDigits() + match.getBetween() + match.getLastDigits() + " ";
                    else
                        line += match.getFirstDigits() + printPermutationsIterative(match.getFirstLetter(), match.getBetween(), match.getLastLetter()) + match.getLastDigits() + " ";
                }
            }

            //Add the current line to the Output
            output.add(line);

            textMatches.clear();
            textMatch = new TextMatch("", "", "", "", "");

        }

        //Write all Twists to the Text File
        readFile.addLines(occurrences);

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

}
