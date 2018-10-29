package de.simagdo.twist.utils;

import de.simagdo.twist.objects.TextMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TwistText {

    private ReadFile readFile = new ReadFile();

    /**
     * Twist or untwist the given Text
     *
     * @param input which gets twisted or untwisted
     * @param mode  which way the Text twisted <br>
     *              TWIST - Text gets twisted <br>
     *              ENDTWIST - Text gets untwisted
     * @return the Text based on the mode
     */
    public ArrayList<String> twist(ArrayList<String> input, TwistMode mode) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<TextMatch> textMatches = new ArrayList<>();
        List<Character> shuffle;
        String line;
        TextMatch textMatch;
        ArrayList<String> occurrences = new ArrayList<>();

        //Loop over the Input List
        for (String meta : input) {

            //Split the current line at Space
            String[] parts = meta.split(" ");

            //Loop over all parts separately
            for (String part : parts) {

                //Prepare the Text
                textMatch = prepareText(part);

                //Set the origin text
                textMatch.setOriginalText(textMatch.getFirstLetter() + textMatch.getBetween() + textMatch.getLastLetter());

                //Set the between Part
                textMatch.setBetween(part.substring((textMatch.getFirstDigits().length() + textMatch.getFirstLetter().length()), (part.length() - (textMatch.getLastLetter().length() + textMatch.getLastDigits().length()))));

                //Check which mode is currently active
                if (mode.equals(TwistMode.TWIST) && part.length() > 3) {

                    //Append each Character to the shuffle List
                    shuffle = textMatch.getBetween().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

                    //Shuffle the Characters between the First and Last Word
                    Collections.shuffle(shuffle);

                    //Create the Twist based on the shuffle List
                    line = shuffle.stream().map(String::valueOf).collect(Collectors.joining());

                    //Clear the Shuffle List
                    shuffle.clear();

                    textMatch.setBetween(line);

                    //Save the Twist in the XML File
                    occurrences.add(textMatch.getOriginalText());

                }

                textMatches.add(textMatch);

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

        }

        //Write all Twists to the Text File
        readFile.addLines(occurrences);

        return output;
    }

    /**
     * Create all possible combinations from the Text <br>
     * To create a better algorithm the first and last character are ignored <br>
     * because they are always the same
     *
     * @param first  of the word
     * @param string from which all the possibilities are generated
     * @param last   of the word
     * @return the original word
     */
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

            /*
              Check if the Result List contains the current combination <br>
              If so the current Loop gets cancelled and the current combination will be returned
             */
            if (possibleTwists.contains(first + onePermutation + last)) {
                result = first + onePermutation + last;
                break;
            }
        }
        return result;
    }

    /**
     * Prepare the current part
     *
     * @param part which gets prepared
     * @return @{@link TextMatch} which contains structure of the text
     */
    private TextMatch prepareText(String part) {
        //Create a new TextMatch object
        TextMatch textMatch = new TextMatch("", "", "", "", "");

        //Check if the length is greater than 3
        if (part.length() > 3) {
            for (int i = 0; i < part.length(); i++) {

                //Set the Digits which are located before the word
                if (!Character.isAlphabetic(part.charAt(i)) && textMatch.getFirstLetter().equals(""))
                    textMatch.setFirstDigits(textMatch.getFirstDigits() + String.valueOf(part.charAt(i)));

                //Set the first letter of the word
                if (Character.isAlphabetic(part.charAt(i)) && textMatch.getFirstLetter().equals(""))
                    textMatch.setFirstLetter(String.valueOf(part.charAt(i)));

                //Set the last letter of the word
                if (Character.isAlphabetic(part.charAt(i)) && !textMatch.getFirstLetter().equals(""))
                    textMatch.setLastLetter(String.valueOf(part.charAt(i)));

                //Set the Digits which are located after the word
                if (!Character.isAlphabetic(part.charAt(i)) && !textMatch.getLastLetter().equals(""))
                    textMatch.setLastDigits(textMatch.getLastDigits() + String.valueOf(part.charAt(i)));

            }
        } else {
            textMatch.setBetween(part);
        }

        return textMatch;
    }

}
