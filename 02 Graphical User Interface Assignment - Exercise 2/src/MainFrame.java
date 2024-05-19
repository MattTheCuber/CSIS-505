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

public class MainFrame extends JFrame {
    JLabel titleLabel;
    JLabel inputLabel;
    JTextField dateField;
    JButton convertButton;
    JPanel inputPanel;
    JPanel buttonPanel;
    JPanel mainPanel;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.initComponents();
    }

    public void initComponents() {
        titleLabel = new JLabel("Date Format Converter");
        inputLabel = new JLabel("Enter date in the format mm/dd/yyyy:");
        dateField = new JTextField(10);
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                convertButtonClicked(evt);
            }
        });

        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        inputPanel.add(inputLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(dateField);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(convertButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("02 Graphical User Interface Assignment - Exercise 2");
        setSize(380, 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void convertButtonClicked(ActionEvent evt) {
        String dateInput = dateField.getText();
        // Starts with 2 digits, followed by a slash, followed by 2 digits, followed by
        // a slash, and ending with 4 digits
        String regex = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateInput);

        if (matcher.matches()) {
            DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

            Date parsedDate;
            try {
                parsedDate = inputFormat.parse(dateInput);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error parsing provided date. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String convertedDate = outputFormat.format(parsedDate);
            JOptionPane.showMessageDialog(
                    this,
                    "The converted date is " + convertedDate,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "An invalid date format was specified. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
