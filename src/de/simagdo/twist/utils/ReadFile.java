package de.simagdo.twist.utils;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadFile {

    public final static File POSSIBLE_TWISTS = new File("possibleTwists.txt");

    public void createXML(String text) {

        Element root = new Element("Twists");

        Document document = new Document(root);

        Element twist = new Element(text);

        //twist.setAttribute(new Attribute("Twist", text));
        twist.addContent(new Element("combination", "Tiwst"));

        document.getRootElement().addContent(twist);

        saveXML(document);

    }

    public void checkIfExits(String text, String twist) {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new FileReader("possibleTwists.xml"));
            Element root = document.getRootElement();

            if (!text.contains("'") || !twist.contains("'")) {

                Element element = root.getChild(text);
                if (element != null) {
                    System.out.println(element.toString());
                    addCombination(element, text, twist);
                } else {
                    System.out.println("Element not exists");
                    addTwist(text, twist);
                }
            }
        } catch (JDOMException | IOException e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Add a Twist to the XML File
     *
     * @param text  which contains the original Text
     * @param twist which contains the Twist
     */
    public void addTwist(String text, String twist) {
        SAXBuilder saxBuilder = new SAXBuilder();

        try {
            Document document = saxBuilder.build(new FileReader("possibleTwists.xml"));
            Element root = document.getRootElement();

            Element twistElement = new Element(text);
            //twistElement.setAttribute(new Attribute(text, text));
            twistElement.addContent(new Element("combination", twist));
            root.addContent(twistElement);

            saveXML(document);
        } catch (JDOMException | IOException e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Add a combination to the Text
     *
     * @param text  where the combination will be added
     * @param twist which contains the combination
     */
    public void addCombination(Element element, String text, String twist) {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new FileReader("possibleTwists.xml"));
            Element root = document.getRootElement();

            Element twistElement = root.getChild(text);
            System.out.println("Twist: " + twistElement.getQualifiedName());
            twistElement.getContent().forEach(System.out::println);

            Iterator<Content> iterator = twistElement.getContent().iterator();

            Element content = twistElement.getChild(twist);
            if (content == null) {
                twistElement.addContent(new Element("combination", twist));
            }

            /*for (Element test : elements) {
                if (!test.getValue().equalsIgnoreCase(twist)) {
                    twistElement.addContent(new Element("combination", twist));
                }
            }*/

            //root.getChildren(element.getQualifiedName()).set(0, twistElement);

            saveXML(document);
        } catch (JDOMException | IOException e) {
            System.err.println("Error: " + e);
        }
    }

    private void saveXML(Document document) {
        try {
            XMLOutputter outputter = new XMLOutputter();
            //outputter.output(document, System.out);

            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter("possibleTwists.xml"));
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

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

    public ArrayList<String> getPossibleTwists() {
        ArrayList<String> possibleTwists = new ArrayList<>();
        ArrayList<String> input = readFile(POSSIBLE_TWISTS);

        possibleTwists.addAll(input);

        return possibleTwists;
    }

    public void addLines(ArrayList<String> possibleTwists) {
        ArrayList<String> input = readFile(POSSIBLE_TWISTS);
        boolean firstRun = true;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(POSSIBLE_TWISTS, true));

            for (String possibleTwist : possibleTwists) {
                if (input.size() == 0 && firstRun) {
                    writer.append(possibleTwist);
                    firstRun = false;
                } else {
                    writer.append("\n").append(possibleTwist);
                }
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    public void addLine(String text, String twist) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(POSSIBLE_TWISTS, true));
            writer.append("\n").append(text).append(" ").append(twist);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }

    }

}
