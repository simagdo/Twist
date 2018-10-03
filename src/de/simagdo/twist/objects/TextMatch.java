package de.simagdo.twist.objects;

/**
 * This object stores the structure of the words with its properties
 *
 * @author Simon Agethen
 * @version 1.0
 */
public class TextMatch {

    private String originalText;
    private String firstDigits;
    private String firstLetter;
    private String between;
    private String lastLetter;
    private String lastDigits;

    /**
     * Constructor for {@link TextMatch}
     *
     * @param firstDigits which are in front of the word
     * @param firstLetter of the word
     * @param between     the word which gets twisted
     * @param lastLetter  of the word
     * @param lastDigits  which are at the end of the word
     */
    public TextMatch(String firstDigits, String firstLetter, String between, String lastLetter, String lastDigits) {
        this("", firstDigits, firstLetter, between, lastLetter, lastDigits);
    }

    /**
     * Constructor for {@link TextMatch}
     *
     * @param originalText which is the original Text
     * @param firstDigits  which are in front of the word
     * @param firstLetter  of the word
     * @param between      the word which gets twisted
     * @param lastLetter   of the word
     * @param lastDigits   which are at the end of the word
     */
    public TextMatch(String originalText, String firstDigits, String firstLetter, String between, String lastLetter, String lastDigits) {
        this.originalText = originalText;
        this.firstDigits = firstDigits;
        this.firstLetter = firstLetter;
        this.between = between;
        this.lastLetter = lastLetter;
        this.lastDigits = lastDigits;
    }

    /**
     * @return the name of the original text.
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * Set the name of the original text
     *
     * @param originalText which will be set
     */
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    /**
     * @return the first digits which are in front of the word
     */
    public String getFirstDigits() {
        return firstDigits;
    }

    /**
     * Set the first digits which are in front of the word
     *
     * @param firstDigits which will be set
     */
    public void setFirstDigits(String firstDigits) {
        this.firstDigits = firstDigits;
    }

    /**
     * @return the first Letter of the word
     */
    public String getFirstLetter() {
        return firstLetter;
    }

    /**
     * Set the first word from the Text
     *
     * @param firstLetter which will be set
     */
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    /**
     * @return get the Text between the second and the last letter <br>
     * This Text wil be twisted
     */
    public String getBetween() {
        return between;
    }

    /**
     * Set the Between Text
     *
     * @param between which will be set
     */
    public void setBetween(String between) {
        this.between = between;
    }

    /**
     * @return get the last Letter of the word
     */
    public String getLastLetter() {
        return lastLetter;
    }

    /**
     * Set the last Letter of the word
     *
     * @param lastLetter which will be set
     */
    public void setLastLetter(String lastLetter) {
        this.lastLetter = lastLetter;
    }

    /**
     * @return the last digits from the word which are on the end of the Text
     */
    public String getLastDigits() {
        return lastDigits;
    }

    /**
     * Set the last digits
     *
     * @param lastDigits which will be set
     */
    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    /**
     * @return the Values as a String
     */
    public String toString() {
        return "Original: " + originalText + ", FirstDigit: " + firstDigits + ", FirstLetter: " + firstLetter + ", Between: " + between + ", LastLetter: " + lastLetter + ", LastDigit: " + lastDigits;
    }

}