// Generic Collections Assignment - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 31, 2024

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import colormap.ColorMap;

/**
 * MainFrame application using Java Swing to create a GUI to change the
 * background color using the ColorMap class.
 */
public class MainFrame extends JFrame {
    // Components variables
    ColorMap colorMap;
    JList<String> colorList;
    JScrollPane scrollPane;
    JButton applyColorButton;
    JPanel mainPanel;

    /**
     * Main method to create and display the main frame of the application.
     * 
     * @param args Unused command-line arguments
     */
    public static void main(String[] args) {
        // Create and display the main frame as well as the ColorMap object.
        MainFrame mainFrame = new MainFrame();
        mainFrame.colorMap = new ColorMap();
        mainFrame.initComponents();
    }

    /**
     * Initialize the components of the main frame.
     */
    private void initComponents() {
        // Create the interactive components.
        colorList = new JList<>(colorMap.getColorNamesArray());
        colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(colorList);
        applyColorButton = new JButton("Apply Color");
        // Add an action listener to the apply color button.
        applyColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                applyColorButtonClicked(evt);
            }
        });

        // Create the main panel.
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.WEST);
        mainPanel.add(applyColorButton, BorderLayout.SOUTH);

        // Add the main panel to the main frame.
        add(mainPanel);

        // Set the main frame properties.
        setTitle("03 Generic Collections - Exercise 1");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Applies the background color selected by the user to the main panel.
     * 
     * @param evt Action event from the apply color button's callback.
     */
    public void applyColorButtonClicked(ActionEvent evt) {
        // Get the selected color name.
        String selectedColor = colorList.getSelectedValue();
        // If a color is selected
        if (selectedColor != null) {
            // Get the Color and change the main panel background to it.
            mainPanel.setBackground(colorMap.getColor(selectedColor));
        }
    }
}
