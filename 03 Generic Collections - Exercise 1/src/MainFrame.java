
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

public class MainFrame extends JFrame {
    ColorMap colorMap;
    JList<String> colorList;
    JScrollPane scrollPane;
    JButton applyButton;
    JPanel mainPanel;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.colorMap = new ColorMap();
        mainFrame.initComponents();
    }

    private void initComponents() {
        colorList = new JList<>(colorMap.getColorNamesArray());
        colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(colorList);

        applyButton = new JButton("Apply Color");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                applyColorButtonClicked(evt);
            }
        });

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.WEST);
        mainPanel.add(applyButton, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("03 Generic Collections - Exercise 1");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void applyColorButtonClicked(ActionEvent evt) {
        String selectedColor = colorList.getSelectedValue();
        if (selectedColor != null) {
            mainPanel.setBackground(colorMap.getColor(selectedColor));
        }
    }
}
