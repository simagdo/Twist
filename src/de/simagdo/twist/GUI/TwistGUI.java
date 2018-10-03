package de.simagdo.twist.GUI;

import de.simagdo.twist.utils.ReadFile;
import de.simagdo.twist.utils.TwistMode;
import de.simagdo.twist.utils.TwistText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TwistGUI extends JFrame implements ActionListener {

    /**
     * Here are some local Variables used for the GUI
     */
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JMenuBar fileMenuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu();
    private JTextArea inputArea = new JTextArea();
    private JTextArea outputArea = new JTextArea();
    private ArrayList<String> data = new ArrayList<>();
    private ReadFile readFile = new ReadFile();
    private TwistText twistText = new TwistText();

    public void init() {

        //Set the Title GUI
        this.setTitle("Aufgabe 2 - Twist");

        //Set the Size of the GUI
        this.setSize(1200, 600);

        //Set the position of  the GUI
        this.setLocation(400, 400);

        //Reset the Layout of the Panel
        panel.setLayout(null);

        //Set the Size of the Panel
        panel.setSize(1200, 600);

        //Add the Panel to the GUI
        this.add(panel);

        //Create the Menu
        fileMenu = new JMenu("Datei");

        //Add the menu to the javax.swing.JMenuBar
        fileMenuBar.add(fileMenu);

        //Add the Open Item to the Menu
        fileMenu.add(addMenuItem("Datei öffnen", "open"));

        //Add the Close Item to the Menu
        fileMenu.add(addMenuItem("Schließen", "close"));

        //Set the MenuBar of the GUI
        this.setJMenuBar(fileMenuBar);

        //Create a Label
        label.setText("Twist");
        label.setSize(100, 30);
        label.setLocation(575, 10);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);

        //Create the InputArea which contains the Text to twist
        inputArea = createTextArea(475, 500, 15, 35);
        panel.add(inputArea);

        //Create the InputArea which contains the Text to detwist
        outputArea = createTextArea(475, 500, 700, 35);
        panel.add(outputArea);

        //Add the Input Label
        panel.add(createLabel("Eingabe", 100, 30, 15, 5));

        //Add the Output Label
        panel.add(createLabel("Ausgabe", 100, 30, 700, 5));

        //Add the Twist Button
        panel.add(createButton("Twist", "twist", 125, 30, 540, 125));

        //Add the Dewist Button
        panel.add(createButton("Enttwisten", "detwist", 125, 30, 540, 225));

        //Show the GUI
        this.show();
    }

    /**
     * Create a MenuItem and register a command
     *
     * @param title   which will be displayed
     * @param command which will be registered
     * @return the {@link JMenuItem}
     */
    private JMenuItem addMenuItem(String title, String command) {
        JMenuItem menuItem = new JMenuItem(title);

        //Set the command for this Item
        menuItem.setActionCommand(command);

        menuItem.addActionListener(this);

        return menuItem;
    }

    /**
     * Create a {@link JTextArea}
     *
     * @param width  of the JTextArea
     * @param height of the JTextArea
     * @param locX   of the JTextArea
     * @param locY   of the JTextArea
     * @return the JTextArea
     */
    private JTextArea createTextArea(int width, int height, int locX, int locY) {
        JTextArea textArea = new JTextArea();
        textArea.setSize(width, height);
        textArea.setLocation(locX, locY);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //textArea.setAutoscrolls(true);
        textArea.setRows(150);

        return textArea;
    }

    /**
     * Create a {@link JButton}
     *
     * @param title   of the Button
     * @param command which will be registered
     * @param width   of the Button
     * @param height  of the Button
     * @param locX    of the Button
     * @param locY    of the Button
     * @return the Button
     */
    private JButton createButton(String title, String command, int width, int height, int locX, int locY) {
        JButton button = new JButton(title);
        button.setSize(width, height);
        button.setLocation(locX, locY);
        button.setActionCommand(command);
        button.addActionListener(this);
        return button;
    }

    /**
     * Create a {@link JLabel}
     *
     * @param text   which will be displayed
     * @param width  of the Label
     * @param height of the Label
     * @param locX   of the Label
     * @param locY   of the Label
     * @return the Label
     */
    private JLabel createLabel(String text, int width, int height, int locX, int locY) {
        JLabel label = new JLabel(text);
        label.setSize(width, height);
        label.setLocation(locX, locY);
        return label;
    }

    /**
     * Check which {@link JMenuItem} gets triggered
     *
     * @param event of the Action
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        ArrayList<String> output = new ArrayList<>();

        System.out.println(event.getActionCommand());

        //Check which Button gets triggered
        switch (event.getActionCommand()) {

            //Open Menu was chosen
            case "open":

                //Create a new JFileChooser which allows the User to select a File which contains the Text to twist
                JFileChooser fileChooser = new JFileChooser();

                //Set the current Directory
                fileChooser.setCurrentDirectory(new File("C:\\Users\\simag\\IdeaProjects\\Twist\\"));
                fileChooser.showOpenDialog(null);

                //Check if the User has selected a File
                if (fileChooser.getSelectedFile() != null) {
                    System.out.println("Filepath: " + fileChooser.getSelectedFile().getPath());
                    data = readFile.readFile(fileChooser.getSelectedFile());

                    //Set the Text from the File into the InputArea
                    //Clear the input Area
                    inputArea.setText("");
                    for (String current : data) {
                        inputArea.setText(inputArea.getText() + current + "\n");
                    }
                }
                break;

            //Close was chosen
            case "close":
                System.exit(0);
                break;

            //Twist was chosen
            case "twist":

                //Check if the List is empty
                if (data.size() == 0) {
                    if (inputArea.getText().length() != 0) {
                        System.out.println(inputArea.getText());
                        data.add(inputArea.getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "Bitte wählen Sie zuerst eine Datei aus!");
                    }
                } else {
                    //Clear the output Area
                    outputArea.setText("");

                    //Twist the Text with the Data from the InputArea
                    //output = twistText.twist(data);
                    output = twistText.twist(data, TwistMode.TWIST);

                    //Output the twisted Text in the OutputArea
                    for (String current : output) {
                        outputArea.setText(outputArea.getText() + current + "\n");
                    }
                }
                break;

            //Detwist was chosen
            case "detwist":
                //Clear the input Area
                inputArea.setText("");

                //Endtwist the Text with the Data from the OutputArea
                //output = twistText.endTwist(data);
                output = twistText.twist(data, TwistMode.ENDTWIST);

                //Output the endtwisted Text in the InputArea
                for (String current : output) {
                    inputArea.setText(inputArea.getText() + current + "\n");
                }
                break;
        }
    }
}