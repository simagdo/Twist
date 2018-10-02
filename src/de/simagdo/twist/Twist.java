package de.simagdo.twist;

import de.simagdo.twist.GUI.TwistGUI;
import de.simagdo.twist.objects.PossibleTwists;
import de.simagdo.twist.utils.ReadFile;
import de.simagdo.twist.utils.TwistText;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Twist {

    private static ArrayList<String> possibleTwists = new ArrayList<>();
    private static String input;
    private static int n;
    private static char first;
    private static char last;
    private static boolean result = false;
    private static ReadFile readFile = new ReadFile();
    private static String output = "";

    public static void main(String[] args) {
        TwistGUI twistGUI = new TwistGUI();
        twistGUI.init();
        /*ReadFile readFile = new ReadFile();
        ArrayList<PossibleTwists> possibleTwists = readFile.getPossibleTwists();

        readFile.addLine("Hallo", "Test123");*/

        //System.out.println("Old: Test, New: " + shiftText("Test"));

        //possibleTwists.forEach(System.out::println);

        ArrayList<String> list = new ArrayList<>();
        //list = new TwistText().endTwist(new ReadFile().readFile(new File("enttwist.txt")));
        //ArrayList<String> text = new ArrayList<>();
        //text.add("(tiswt)");
        //list = new TwistText().endTwist(text);
        //list.stream().forEach(System.out::println);

        /*ReadFile readFile = new ReadFile();

        ArrayList<PossibleTwists> possibleTwists = readFile.getPossbleTwists();

        possibleTwists.stream().filter(twist -> twist.getTwist().equalsIgnoreCase("Jhearn"))
                .sorted(Comparator.comparing(PossibleTwists::getTwist))
                .forEach(System.out::println);*/

        //readFile.addLine("Hallo", "Hlalo");

        //readFile.getPossibleTwists().forEach(System.out::println);

        //readFile.addTwist("Twist", "Tiwst");
        //readFile.createXML("Twist");
        //readFile.addTwist("Hallo", "Hlalo");
        //readFile.createXML("Twist");
        //readFile.addTwist("Hallo", "Hlalo");
        //readFile.addTwist("Peter", "Pteer");
        //readFile.addCombination("Hallo", "Hllao");

        //readFile.checkIfExits("Twist", "Test");

        input = "Elgicsnh";
        /*possibleTwists = readFile.getPossibleTwists();
        long startTime = System.currentTimeMillis();
        System.out.println("Test: " + permutations(input.toCharArray(), 0));
        System.out.println("Result: " + output);
        long endTime = System.currentTimeMillis() - startTime;
        System.out.printf("%02d min, %02d sec, %02d mil", TimeUnit.MILLISECONDS.toMinutes(endTime),
                TimeUnit.MILLISECONDS.toSeconds(endTime) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MICROSECONDS.toMinutes(endTime)),
                TimeUnit.MILLISECONDS.toMillis(endTime) - TimeUnit.MILLISECONDS.toMillis(TimeUnit.MICROSECONDS.toSeconds(endTime)));*/

        first = input.charAt(0);
        last = input.charAt(input.length() - 1);

        //permutation(input);
        //possibleTwists = readFile.getPossibleTwists();
        //printPermutationsIterative(input);
    }

    // Utility function to swap two characters in a character array
    private static void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    // Recursive function to generate all permutations of a String
    private static String permutations(char[] ch, int currentIndex) {
        String output = "";

        if (possibleTwists.contains(String.valueOf(ch))) {
            //System.out.println("True");
            result = true;
            System.out.println(result);
            System.out.println("Erg: " + possibleTwists.get(possibleTwists.indexOf(String.valueOf(ch))));
            output = possibleTwists.get(possibleTwists.indexOf(String.valueOf(ch)));
            return output;
        }

        if (!result) {
            for (int i = currentIndex; i < ch.length; i++) {
                swap(ch, currentIndex, i);
                permutations(ch, currentIndex + 1);
                swap(ch, currentIndex, i);
                System.out.println("1234==================================: " + String.valueOf(ch));
            }
        }

        /*for (String possibleTwist : possibleTwists) {
            System.out.println(possibleTwist + ", Current Value: " + String.valueOf(ch));
            if (possibleTwist.equals(String.valueOf(ch))) {
                //System.out.println("True");
                if (possibleTwist.equals("Englisch")) {
                    System.out.println("debug");
                }
                result = true;
                System.out.println(result);
                System.out.println("Erg: " + possibleTwist);
                output = possibleTwist;
                return output;
            } else {
                for (int i = currentIndex; i < ch.length; i++) {
                    swap(ch, currentIndex, i);
                    permutations(ch, currentIndex + 1);
                    swap(ch, currentIndex, i);
                    System.out.println("1234==================================: " + String.valueOf(ch));
                }
            }
        }*/

        /*if (!result || ch[0] == ' ') {
            for (int i = currentIndex; i < ch.length; i++) {
                swap(ch, currentIndex, i);
                permutations(ch, currentIndex + 1);
                swap(ch, currentIndex, i);
                System.out.println("1234==================================: " + String.valueOf(ch));
            }
        }*/

        return output;
    }

    private static void permutation(String str) {
        String permutation = permutation("", str.substring(1, str.length() - 1));
        System.out.println(permutation);
    }

    private static String permutation(String prefix, String str) {

        if (!result) {
            System.out.println("First: " + first + ", Prefix: " + prefix + ", Str: " + str + ", Last: " + last);
            if (possibleTwists.contains(first + prefix + str + last)) {
                //if (possibleTwists.contains(first + prefix + str + last)) {
                System.out.println("True, Result: " + first + prefix + str + last + ", N: " + n);
                result = true;
                System.out.println(result);
                //break;
                return first + prefix + str + last;
            } else {
                for (int i = 0; i < n; i++) {
                    //for (int i = 1; i < str.length() - 1; i++) {
                    System.out.println("Character: " + str.charAt(i));
                    permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
                    //permutation(prefix + str.charAt(i), str.substring(1, i) + str.substring(i + 1, n));
                }
            }
        }

        return first + prefix + str + last;
    }

    private static String printPermutationsIterative(String string) {
        String result = "";
        char first = string.charAt(0);
        char last = string.charAt(string.length() - 1);
        string = string.substring(1, string.length() - 1);
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
                System.out.println("===================" + first + onePermutation + last + "===================");
                result = onePermutation;
                break;
            }
            System.out.println(onePermutation);
        }
        return result;
    }

}
