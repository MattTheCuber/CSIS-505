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

public class MainFrame extends JFrame {
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
    JLabel titleLabel;
    JLabel textAreaLabel;
    JTextArea textArea;
    JScrollPane scrollPane;
    JButton clearButton;
    JButton verifyButton;
    JPanel textPanel;
    JPanel buttonPanel;
    JPanel mainPanel;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.initComponents();
    }

    public void initComponents() {
        titleLabel = new JLabel("Spam Score Calculator");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        textAreaLabel = new JLabel("Type or paste email text below:");
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                clearButtonClicked(evt);
            }
        });
        verifyButton = new JButton("Verify");
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                verifyButtonClicked(evt);
            }
        });

        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout(10, 10));
        textPanel.add(textAreaLabel, BorderLayout.NORTH);
        textPanel.add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(clearButton);
        buttonPanel.add(verifyButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("02 Graphical User Interface Assignment - Exercise 1");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void clearButtonClicked(ActionEvent evt) {
        textArea.setText("");
    }

    public void verifyButtonClicked(ActionEvent evt) {
        String emailMessage = textArea.getText();
        int spamScore = 0;
        for (String keyPhrase : spamKeyPhrases) {
            Pattern pattern = Pattern.compile(".*" + Pattern.quote(keyPhrase) + ".*", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailMessage);
            if (matcher.matches()) {
                spamScore++;
            }
        }
        JOptionPane.showMessageDialog(this, "The spam score for this message is " + spamScore);
    }
}
