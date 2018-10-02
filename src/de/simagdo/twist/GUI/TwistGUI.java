package de.simagdo.twist.GUI;

import de.simagdo.twist.utils.ReadFile;
import de.simagdo.twist.utils.TwistText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TwistGUI extends JFrame implements ActionListener {

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
        this.setSize(850, 450);

        //Set the position of  the GUI
        this.setLocation(500, 500);

        //Reset the Layout of the Panel
        panel.setLayout(null);

        //Set the Size of the Panel
        panel.setSize(850, 450);

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
        label.setLocation(400, 10);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);

        //Create the InputArea which contains the Text to twist
        inputArea = createTextArea(300, 350, 15, 35);
        panel.add(inputArea);

        //Create the InputArea which contains the Text to detwist
        outputArea = createTextArea(300, 350, 515, 35);
        panel.add(outputArea);

        //Add the Twist Button
        panel.add(createButton("Twist", "twist", 125, 30, 350, 125));

        //Add the Dewist Button
        panel.add(createButton("Enttwisten", "detwist", 125, 30, 350, 225));

        //Add the Input Label
        panel.add(createLabel("Eingabe", 100, 30, 15, 5));

        //Add the Output Label
        panel.add(createLabel("Ausgabe", 100, 30, 515, 5));

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
        textArea.setRows(15);

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
        switch (event.getActionCommand()) {
            case "open":
                System.out.println("FileChooser will be opened soon...");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:\\Users\\simag\\IdeaProjects\\Twist\\"));
                fileChooser.showOpenDialog(null);
                if (fileChooser.getSelectedFile() != null) {
                    System.out.println("Filepath: " + fileChooser.getSelectedFile().getPath());
                    data = readFile.readFile(fileChooser.getSelectedFile());

                    //Clear the input Area
                    inputArea.setText("");
                    for (String current : data) {
                        inputArea.setText(inputArea.getText() + current + "\n");
                    }
                }
                break;
            case "close":
                System.exit(0);
                break;
            case "twist":

                //Check if the List is empty
                if (data.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Bitte wählen Sie zuerst eine Datei aus!");
                } else {
                    //Clear the output Area
                    outputArea.setText("");
                    output = twistText.twist(data);
                    for (String current : output) {
                        outputArea.setText(outputArea.getText() + current + "\n");
                    }
                }
                break;
        }
    }
}