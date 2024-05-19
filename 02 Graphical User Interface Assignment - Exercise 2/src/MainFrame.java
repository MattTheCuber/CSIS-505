// Graphical User Interface Assignment - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 24, 2024

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MainFrame application using Java Swing to create a GUI to convert from
 * MM/dd/yyyy format to MMMM d, yyyy format.
 */
public class MainFrame extends JFrame {
    // Components variables
    JLabel titleLabel;
    JLabel inputLabel;
    JTextField dateField;
    JButton convertButton;
    JPanel inputPanel;
    JPanel buttonPanel;
    JPanel mainPanel;

    /**
     * Main method to create and display the main frame of the application.
     * 
     * @param args Unused command-line arguments
     */
    public static void main(String[] args) {
        // Create and display the main frame
        MainFrame mainFrame = new MainFrame();
        mainFrame.initComponents();
    }

    /**
     * Initialize the components of the main frame.
     */
    public void initComponents() {
        // Create the interactive components
        titleLabel = new JLabel("Date Format Converter");
        inputLabel = new JLabel("Enter date in the format mm/dd/yyyy:");
        dateField = new JTextField(10);
        convertButton = new JButton("Convert");
        // Add an action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            /**
             * Calls the convert button clicked method.
             * 
             * @param evt Action event from the convert button's callback.
             */
            public void actionPerformed(ActionEvent evt) {
                convertButtonClicked(evt);
            }
        });

        // Create the input panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        inputPanel.add(inputLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(dateField);

        // Create the button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align the button to the right
        buttonPanel.add(convertButton);

        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the main frame
        add(mainPanel);

        // Set the main frame properties
        setTitle("02 Graphical User Interface Assignment - Exercise 2");
        setSize(380, 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Converts the input date from MM/dd/yyyy format to MMMM d, yyyy format and
     * displays the result to the user.
     * 
     * @param evt Action event from the convert button's callback.
     */
    public void convertButtonClicked(ActionEvent evt) {
        // Get the date string from the text area
        String dateInput = dateField.getText();
        // Create a regex to check if the date input starts with 2 digits, followed by a
        // slash, followed by 2 digits, followed by a slash, and ending with 4 digits
        String regex = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateInput);

        if (matcher.matches()) {
            // Setup the date format conversion
            DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

            // Try parsing the date input
            Date parsedDate;
            try {
                parsedDate = inputFormat.parse(dateInput);
            } catch (ParseException e) {
                // Display an error message if the date cannot be parsed
                JOptionPane.showMessageDialog(
                        this,
                        "Error parsing provided date. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Convert the date to the desired format and display it to the user
            String convertedDate = outputFormat.format(parsedDate);
            JOptionPane.showMessageDialog(
                    this,
                    "The converted date is " + convertedDate,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Display an error message if the date format is invalid
            JOptionPane.showMessageDialog(
                    this,
                    "An invalid date format was specified. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
