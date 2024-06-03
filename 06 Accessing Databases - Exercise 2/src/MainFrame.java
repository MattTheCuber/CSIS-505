// Accessing Databases - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 21, 2024

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MainFrame application that displays the authors and titles tables and allows
 * the user to add, edit, and delete authors and titles.
 */
public class MainFrame extends JFrame {
    // Column names for the authors and titles tables.
    String[] authorsColumns = { "Author ID", "First Name", "Last Name" };
    String[] titlesColumns = { "ISBN", "Title", "Edition Number", "Copyright Year", "Authors" };

    // Component variables
    private JTable authorsTable, titlesTable;
    private DefaultTableModel authorsModel, titlesModel;
    private JButton editAuthorButton, addAuthorButton, deleteAuthorButton, editTitleButton, addTitleButton,
            deleteTitleButton;

    /**
     * Main method to create and display the main frame of the application.
     * 
     * @param args Unused command-line arguments
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.initComponents();
    }

    /**
     * Initialize the components of the main frame.
     */
    public void initComponents() {
        // Create the authors and titles tables putting them in scroll panes.
        authorsModel = new DefaultTableModel(authorsColumns, 0) {
            // Disable editing cells in the table.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        authorsTable = new JTable(authorsModel);
        JScrollPane authorsScrollPane = new JScrollPane(authorsTable);

        titlesModel = new DefaultTableModel(titlesColumns, 0) {
            // Disable editing cells in the table.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        titlesTable = new JTable(titlesModel);
        // Add the tooltip renderer for the titles and authors columns in the table.
        titlesTable.getColumnModel().getColumn(1).setCellRenderer(new TooltipRenderer());
        titlesTable.getColumnModel().getColumn(4).setCellRenderer(new TooltipRenderer());
        JScrollPane titlesScrollPane = new JScrollPane(titlesTable);

        // Create the top panel with the authors and titles tables.
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 5, 5));
        topPanel.add(authorsScrollPane);
        topPanel.add(titlesScrollPane);

        // Create the buttons for editing authors.
        editAuthorButton = new JButton("Edit Selected Author");
        editAuthorButton.addActionListener(e -> editAuthorDialog());
        addAuthorButton = new JButton("Add Author");
        addAuthorButton.addActionListener(e -> addAuthorDialog());
        deleteAuthorButton = new JButton("Delete Author");
        deleteAuthorButton.addActionListener(e -> deleteAuthorDialog());

        // Create the buttons for editing titles.
        editTitleButton = new JButton("Edit Selected Book");
        editTitleButton.addActionListener(e -> editTitleDialog());
        addTitleButton = new JButton("Add Book");
        addTitleButton.addActionListener(e -> addTitleDialog());
        deleteTitleButton = new JButton("Delete Book");
        deleteTitleButton.addActionListener(e -> deleteTitleDialog());

        // Create the buttons panel with the edit, add, and delete buttons for both
        // table.
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        buttonsPanel.add(editAuthorButton);
        buttonsPanel.add(addAuthorButton);
        buttonsPanel.add(deleteAuthorButton);
        buttonsPanel.add(editTitleButton);
        buttonsPanel.add(addTitleButton);
        buttonsPanel.add(deleteTitleButton);

        // Combine the panels into the main panel.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        mainPanel.add(topPanel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(buttonsPanel);

        // Add the main panel to the main frame.
        add(mainPanel);

        // Update the authors and titles tables.
        updateTables();

        // Set the main frame properties.
        setTitle("06 Accessing Databases - Exercise 2");
        setSize(1080, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Display a dialog to add a new author then adds the input to the database.
     */
    private void addAuthorDialog() {
        // Create the dialog components and add them to a panel.
        JTextField firstNameField = new JTextField(10);
        JTextField lastNameField = new JTextField(10);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        // Display the dialog and get the result.
        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Author", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            System.out.println("New Author: " + firstNameField.getText() + " " + lastNameField.getText());
            try {
                // Add the new author to the database and update the authors table.
                Database.addAuthor(firstNameField.getText(), lastNameField.getText());
                updateAuthorsTable();
            } catch (SQLException e) {
                // Display an error message if there is an error adding the author.
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding author: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Display a dialog to edit the selected author then updates the input in the
     */
    private void editAuthorDialog() {
        // Get the selected row in the authors table.
        int selectedRow = authorsTable.getSelectedRow();
        if (selectedRow >= 0) { // Check if a row is selected.
            // Get the author ID, first name, and last name of the selected row.
            int authorID = Integer.parseInt(authorsModel.getValueAt(selectedRow, 0).toString());
            String firstName = authorsModel.getValueAt(selectedRow, 1).toString();
            String lastName = authorsModel.getValueAt(selectedRow, 2).toString();
            // Create the dialog components and add them to a panel.
            JTextField firstNameField = new JTextField(firstName, 10);
            JTextField lastNameField = new JTextField(lastName, 10);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("First Name:"));
            panel.add(firstNameField);
            panel.add(new JLabel("Last Name:"));
            panel.add(lastNameField);
            // Display the dialog and get the result.
            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Author", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Updated Author: " + firstNameField.getText() + " " + lastNameField.getText());
                try {
                    // Edit the author in the database and update the authors table.
                    Database.editAuthor(authorID, firstNameField.getText(), lastNameField.getText());
                    updateAuthorsTable();
                } catch (SQLException e) {
                    // Display an error message if there is an error editing the author.
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating author: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // If no row is selected, display an error message.
            JOptionPane.showMessageDialog(this, "No author selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Display a dialog to delete the selected author then deletes the author from
     * the database.
     */
    private void deleteAuthorDialog() {
        // Get the selected row in the authors table.
        int selectedRow = authorsTable.getSelectedRow();
        if (selectedRow >= 0) { // Check if a row is selected.
            // Get the author ID, first name, and last name of the selected row.
            int authorID = Integer.parseInt(authorsModel.getValueAt(selectedRow, 0).toString());
            String firstName = authorsModel.getValueAt(selectedRow, 1).toString();
            String lastName = authorsModel.getValueAt(selectedRow, 2).toString();
            // Display a confirmation dialog and get the result.
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete author: " + firstName + " " + lastName + "?", "Delete Author",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Deleted Author: " + firstName + " " + lastName);
                try {
                    // Delete the author from the database and update both tables.
                    Database.deleteAuthor(authorID);
                    updateTables();
                } catch (SQLException e) {
                    // Display an error message if there is an error deleting the author.
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting author: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // If no row is selected, display an error message.
            JOptionPane.showMessageDialog(this, "No author selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Display a dialog to add a new title then adds the input to the database.
     */
    private void addTitleDialog() {
        // Get the author names from the database.
        Map<String, Integer> authorNamesMap = getAuthorNames();
        String[] authorNames = authorNamesMap.keySet().toArray(new String[0]);

        // Create the dialog components and add them to a panel.
        JTextField isbnField = new JTextField(10);
        JTextField titleField = new JTextField(10);
        JTextField editionField = new JTextField(10);
        JTextField yearField = new JTextField(4);
        JList<String> authorsList = new JList<>(authorNames);
        // Set the authors list to allow multiple selections.
        authorsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        authorsList.setLayoutOrientation(JList.VERTICAL);
        authorsList.setVisibleRowCount(3);
        authorsList.setToolTipText("Hold Ctrl to select multiple authors");
        JScrollPane authorsScrollPane = new JScrollPane(authorsList);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Edition Number:"));
        panel.add(editionField);
        panel.add(new JLabel("Copyright Year:"));
        panel.add(yearField);
        panel.add(new JLabel("Authors:"));
        panel.add(authorsScrollPane);
        // Display the dialog and get the result.
        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Title", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Parse the edition number and year fields.
            int edition;
            try {
                edition = Integer.parseInt(editionField.getText());
            } catch (NumberFormatException e) {
                // Display an error message if the edition number is not a number.
                JOptionPane.showMessageDialog(this, "Edition must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Get the selected authors from the authors list.
            List<String> selectedAuthors = authorsList.getSelectedValuesList();
            List<Integer> authorIDs = new ArrayList<>();
            // Get the author IDs for the selected authors.
            for (String authorName : selectedAuthors) {
                authorIDs.add(authorNamesMap.get(authorName));
            }

            System.out.println("New book with ISBN: " + isbnField.getText() + " Title: " + titleField.getText()
                    + ", Edition: " + edition + ", Year: " + yearField.getText() + ", Authors: " + selectedAuthors);
            try {
                // Add the new title to the database and update the titles table.
                Database.addTitle(isbnField.getText(), titleField.getText(), edition, yearField.getText(), authorIDs);
                updateTitlesTable();
            } catch (SQLException e) {
                // Display an error message if there is an error adding the title.
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding title: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Display a dialog to edit the selected title then updates the input in the
     * database.
     */
    private void editTitleDialog() {
        // Get the selected row in the titles table.
        int selectedRow = titlesTable.getSelectedRow();
        if (selectedRow >= 0) { // Check if a row is selected.
            // Get the author names from the database.
            Map<String, Integer> authorNamesMap = getAuthorNames();
            String[] authorNames = authorNamesMap.keySet().toArray(new String[0]);

            // Get the ISBN, title, edition number, and copyright year of the selected row.
            String isbn = titlesModel.getValueAt(selectedRow, 0).toString();
            String title = titlesModel.getValueAt(selectedRow, 1).toString();
            String editionNumber = titlesModel.getValueAt(selectedRow, 2).toString();
            String copyrightYear = titlesModel.getValueAt(selectedRow, 3).toString();
            JList<String> authorsList = new JList<>(authorNames);
            // Set the authors list to allow multiple selections.
            authorsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            authorsList.setLayoutOrientation(JList.VERTICAL);
            authorsList.setVisibleRowCount(3);
            authorsList.setToolTipText("Hold Ctrl to select multiple authors");
            // Set the indices for the selected authors.
            int[] authorIndices = getSelectedAuthorIndices(authorNames, isbn);
            authorsList.setSelectedIndices(authorIndices);
            JScrollPane authorsScrollPane = new JScrollPane(authorsList);
            JTextField isbnField = new JTextField(isbn, 10);
            // Disable editing the ISBN field.
            isbnField.setEditable(false);
            isbnField.setToolTipText("ISBN cannot be edited. To change ISBN, delete and re-add the book.");
            JTextField titleField = new JTextField(title, 10);
            JTextField editionField = new JTextField(editionNumber, 10);
            JTextField copyrightYearField = new JTextField(copyrightYear, 4);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("ISBN:"));
            panel.add(isbnField);
            panel.add(new JLabel("Title:"));
            panel.add(titleField);
            panel.add(new JLabel("Edition Number:"));
            panel.add(editionField);
            panel.add(new JLabel("Copyright Year:"));
            panel.add(copyrightYearField);
            panel.add(new JLabel("Authors:"));
            panel.add(authorsScrollPane);
            // Display the dialog and get the result.
            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Title", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Parse the edition number and year fields.
                int edition;
                try {
                    edition = Integer.parseInt(editionField.getText());
                } catch (NumberFormatException e) {
                    // Display an error message if the edition number is not a number.
                    JOptionPane.showMessageDialog(this, "Edition must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Get the selected authors from the authors list.
                List<String> selectedAuthors = authorsList.getSelectedValuesList();
                List<Integer> authorIDs = new ArrayList<>();
                // Get the author IDs for the selected authors.
                for (String authorName : selectedAuthors) {
                    authorIDs.add(authorNamesMap.get(authorName));
                }

                System.out.println("Updated book with ISBN: " + isbnField.getText() + " Title: " + titleField.getText()
                        + ", Edition: " + edition + ", Copyright Year: " + copyrightYearField.getText() + ", Authors: "
                        + selectedAuthors);
                try {
                    // Edit the title in the database and update the titles table.
                    Database.editTitle(isbn, titleField.getText(), edition, copyrightYearField.getText(), authorIDs);
                    updateTitlesTable();
                } catch (SQLException e) {
                    // Display an error message if there is an error editing the title.
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating title: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // If no row is selected, display an error message.
            JOptionPane.showMessageDialog(this, "No title selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Display a dialog to delete the selected title then deletes the title from the
     * database.
     */
    private void deleteTitleDialog() {
        // Get the selected row in the titles table.
        int selectedRow = titlesTable.getSelectedRow();
        if (selectedRow >= 0) { // Check if a row is selected.
            // Get the ISBN and title of the selected row.
            String isbn = titlesModel.getValueAt(selectedRow, 0).toString();
            String title = titlesModel.getValueAt(selectedRow, 1).toString();
            // Display a confirmation dialog and get the result.
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete book: " + title + "?",
                    "Delete Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Deleted Book: " + title);
                try {
                    // Delete the title from the database and update both tables.
                    Database.deleteTitle(isbn);
                    updateTables();
                } catch (SQLException e) {
                    // Display an error message if there is an error deleting the title.
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting title: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // If no row is selected, display an error message.
            JOptionPane.showMessageDialog(this, "No title selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Update the authors and titles tables.
     */
    private void updateTables() {
        updateAuthorsTable();
        updateTitlesTable();
    }

    /**
     * Update the authors table with the authors from the database.
     */
    private void updateAuthorsTable() {
        // Initialize the result set.
        ResultSet authors = null;
        try {
            // Get the authors from the database.
            authors = Database.getAuthors();

            // Clear the table then add the authors to the table.
            authorsModel.setRowCount(0);
            while (authors.next()) {
                String authorID = authors.getString("authorid");
                String firstName = authors.getString("firstname");
                String lastName = authors.getString("lastname");
                Object[] data = { authorID, firstName, lastName };
                authorsModel.addRow(data);
            }
        } catch (SQLException e) {
            // Display an error message if there is an error getting the authors from the
            // database.
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading authors: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Update the titles table with the titles from the database.
     */
    private void updateTitlesTable() {
        // Initialize the result set.
        ResultSet titles = null;
        try {
            // Get the authors from the database.
            titles = Database.getTitles();

            // Clear the table then add the authors to the table.
            titlesModel.setRowCount(0);
            while (titles.next()) {
                String isbn = titles.getString("isbn");
                String title = titles.getString("title");
                String editionNumber = titles.getString("editionnumber");
                String copyrightYear = titles.getString("copyright");
                // Get the authors for the title and join them into a single string.
                String[] authors = Database.getAuthorsNamesForTitle(isbn);
                String authorsString = String.join(", ", authors);
                Object[] data = { isbn, title, editionNumber, copyrightYear, authorsString };
                titlesModel.addRow(data);
            }
        } catch (SQLException e) {
            // Display an error message if there is an error getting the titles from the
            // database.
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading titles: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Get the author names from the database mapped to the author ID.
     * 
     * @return A map of author names to author IDs.
     */
    private Map<String, Integer> getAuthorNames() {
        // Initialize the map of author names.
        Map<String, Integer> authorNames = new HashMap<>();
        try {
            // Get the authors from the database.
            ResultSet authors = Database.getAuthors();
            // Add the author names to the map.
            while (authors.next()) {
                String firstName = authors.getString("firstname");
                String lastName = authors.getString("lastname");
                String fullName = firstName + " " + lastName;
                authorNames.put(fullName, authors.getInt("authorid"));
            }
        } catch (SQLException e) {
            // Display an error message if there is an error getting the authors from the
            // database.
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding title: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return authorNames;
    }

    /**
     * Get the selected author indices for a title.
     * 
     * @param authorNames The list of displaying author names to index.
     * @param isbn        The ISBN of the title.
     * @return An array of selected author indices.
     */
    private int[] getSelectedAuthorIndices(String[] authorNames, String isbn) {
        // Get the authors for the title from the database.
        String[] authors;
        try {
            // Get the authors for the title.
            authors = Database.getAuthorsNamesForTitle(isbn);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding title: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Get the selected indices for the authors.
        int[] selectedIndices = new int[authors.length];
        for (int i = 0; i < authors.length; i++) {
            for (int j = 0; j < authorNames.length; j++) {
                if (authors[i].equals(authorNames[j])) {
                    selectedIndices[i] = j;
                    break;
                }
            }
        }
        return selectedIndices;
    }
}
