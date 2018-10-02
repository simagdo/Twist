package de.simagdo.twist.objects;

public class TextMatch {

    private String originalText;
    private String firstDigit;
    private String firstLetter;
    private String between;
    private String lastLetter;
    private String lastDigit;

    public TextMatch(String firstDigit, String firstLetter, String between, String lastLetter, String lastDigit) {
        this("", firstDigit, firstLetter, between, lastLetter, lastDigit);
    }

    public TextMatch(String originalText, String firstDigit, String firstLetter, String between, String lastLetter, String lastDigit) {
        this.originalText = originalText;
        this.firstDigit = firstDigit;
        this.firstLetter = firstLetter;
        this.between = between;
        this.lastLetter = lastLetter;
        this.lastDigit = lastDigit;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getFirstDigit() {
        return firstDigit;
    }

    public void setFirstDigit(String firstDigit) {
        this.firstDigit = firstDigit;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getBetween() {
        return between;
    }

    public void setBetween(String between) {
        this.between = between;
    }

    public String getLastLetter() {
        return lastLetter;
    }

    public void setLastLetter(String lastLetter) {
        this.lastLetter = lastLetter;
    }

    public String getLastDigit() {
        return lastDigit;
    }

    public void setLastDigit(String lastDigit) {
        this.lastDigit = lastDigit;
    }

    public String toString() {
        return "Original: " + originalText + ", FirstDigit: " + firstDigit + ", FirstLetter: " + firstLetter + ", Between: " + between + ", LastLetter: " + lastLetter + ", LastDigit: " + lastDigit;
    }

}