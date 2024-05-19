// Graphical User Interface Assignment - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 24, 2024

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MainFrame application using Java Swing to create a GUI for calculating a spam
 * score for the provided email message.
 */
public class MainFrame extends JFrame {
    // Array of known spam key phrases
    String[] spamKeyPhrases = {
            "As seen on",
            "Buy",
            "Meet singles",
            "Extra income",
            "Million dollars",
            "Save big money",
            "Opportunity",
            "Cash",
            "No fees",
            "Online degree",
            "Work at home",
            "Additional income",
            "Eliminate debt",
            "Lower interest rate",
            "Pre-approved",
            "Consolidate your debt",
            "Avoid bankruptcy",
            "Miracle",
            "Satisfaction",
            "Risk free",
            "Free hosting",
            "You have been selected",
            "Weekend getaway",
            "You're a Winner!",
            "Offer",
            "Unlimited",
            "No obligation",
            "Trial",
            "Guarantee",
            "No purchase necessary"
    };
    // Components variables
    JLabel titleLabel;
    JLabel textAreaLabel;
    JTextArea textArea;
    JScrollPane scrollPane;
    JButton clearButton;
    JButton verifyButton;
    JPanel textPanel;
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
        titleLabel = new JLabel("Spam Score Calculator");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add padding
        textAreaLabel = new JLabel("Type or paste email text below:");
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        clearButton = new JButton("Clear");
        // Add an action listener to the clear button
        clearButton.addActionListener(new ActionListener() {
            /**
             * Calls the clear button clicked method.
             * 
             * @param evt Action event from the clear button's callback.
             */
            public void actionPerformed(ActionEvent evt) {
                clearButtonClicked(evt);
            }
        });
        verifyButton = new JButton("Verify");
        // Add an action listener to the verify button
        verifyButton.addActionListener(new ActionListener() {
            /**
             * Calls the verify button clicked method.
             * 
             * @param evt Action event from the verify button's callback.
             */
            public void actionPerformed(ActionEvent evt) {
                verifyButtonClicked(evt);
            }
        });

        // Create the text panel
        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout(10, 10));
        textPanel.add(textAreaLabel, BorderLayout.NORTH);
        textPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        buttonPanel.add(clearButton);
        buttonPanel.add(verifyButton);

        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the main frame
        add(mainPanel);

        // Set the main frame properties
        setTitle("02 Graphical User Interface Assignment - Exercise 1");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Clear the text area for the email message.
     * 
     * @param evt Action event from the clear button's callback.
     */
    public void clearButtonClicked(ActionEvent evt) {
        textArea.setText("");
    }

    /**
     * Calculates and displays the spam score for the provided email message.
     * 
     * The spam score is calculated by checking the email message for the presence
     * of known spam key phrases. Each key phrase found in the email message
     * increases the spam score by one.
     * 
     * @param evt Action event from the verify button's callback.
     */
    public void verifyButtonClicked(ActionEvent evt) {
        // Get the email message from the text area
        String emailMessage = textArea.getText();
        int spamScore = 0;
        for (String keyPhrase : spamKeyPhrases) {
            // Check if the key phrase is present in the email message using a
            // case-insensitive regex match
            Pattern pattern = Pattern.compile(".*" + Pattern.quote(keyPhrase) + ".*", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailMessage);
            if (matcher.matches()) {
                // Increment the spam score if the key phrase is found
                spamScore++;
            }
        }
        // Display the spam score to the user
        JOptionPane.showMessageDialog(this, "The spam score for this message is " + spamScore);
    }
}
