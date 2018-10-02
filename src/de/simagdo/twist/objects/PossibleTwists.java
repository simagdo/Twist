package de.simagdo.twist.objects;

public class PossibleTwists {

    private String text;
    private String twist;

    public PossibleTwists(String text, String twists) {
        this.text = text;
        this.twist = twists;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTwist() {
        return twist;
    }

    public void setTwist(String twist) {
        this.twist = twist;
    }

    public String toString() {
        return "Text: " + text + ", Twist: " + twist;
    }

}
