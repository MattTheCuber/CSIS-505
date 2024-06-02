import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    String[] authorsColumns = { "Author ID", "First Name", "Last Name" };
    String[] titlesColumns = { "ISBN", "Title", "Edition Number", "Copyright Year", "Authors" };

    private JTable authorsTable, titlesTable;
    private DefaultTableModel authorsModel, titlesModel;
    private JButton editAuthorButton, addAuthorButton, deleteAuthorButton, editTitleButton, addTitleButton,
            deleteTitleButton;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.initComponents();
    }

    public void initComponents() {
        authorsModel = new DefaultTableModel(authorsColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        authorsTable = new JTable(authorsModel);
        JScrollPane authorsScrollPane = new JScrollPane(authorsTable);

        titlesModel = new DefaultTableModel(titlesColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        titlesTable = new JTable(titlesModel);
        titlesTable.getColumnModel().getColumn(1).setCellRenderer(new TooltipRenderer());
        titlesTable.getColumnModel().getColumn(4).setCellRenderer(new TooltipRenderer());
        JScrollPane titlesScrollPane = new JScrollPane(titlesTable);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 5, 5));
        topPanel.add(authorsScrollPane);
        topPanel.add(titlesScrollPane);

        editAuthorButton = new JButton("Edit Selected Author");
        editAuthorButton.addActionListener(e -> editAuthorDialog());
        addAuthorButton = new JButton("Add Author");
        addAuthorButton.addActionListener(e -> addAuthorDialog());
        deleteAuthorButton = new JButton("Delete Author");
        deleteAuthorButton.addActionListener(e -> deleteAuthorDialog());

        editTitleButton = new JButton("Edit Selected Book");
        editTitleButton.addActionListener(e -> editTitleDialog());
        addTitleButton = new JButton("Add Book");
        addTitleButton.addActionListener(e -> addTitleDialog());
        deleteTitleButton = new JButton("Delete Book");
        deleteTitleButton.addActionListener(e -> deleteTitleDialog());

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        buttonsPanel.add(editAuthorButton);
        buttonsPanel.add(addAuthorButton);
        buttonsPanel.add(deleteAuthorButton);
        buttonsPanel.add(editTitleButton);
        buttonsPanel.add(addTitleButton);
        buttonsPanel.add(deleteTitleButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        mainPanel.add(topPanel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(buttonsPanel);

        add(mainPanel);

        updateTables();

        setTitle("06 Accessing Databases - Exercise 2");
        setSize(1080, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addAuthorDialog() {
        JTextField firstNameField = new JTextField(10);
        JTextField lastNameField = new JTextField(10);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Author", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            System.out.println("New Author: " + firstNameField.getText() + " " + lastNameField.getText());
            try {
                Database.addAuthor(firstNameField.getText(), lastNameField.getText());
                updateAuthorsTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding author: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editAuthorDialog() {
        int selectedRow = authorsTable.getSelectedRow();
        if (selectedRow >= 0) {
            int authorID = Integer.parseInt(authorsModel.getValueAt(selectedRow, 0).toString());
            String firstName = authorsModel.getValueAt(selectedRow, 1).toString();
            String lastName = authorsModel.getValueAt(selectedRow, 2).toString();
            JTextField firstNameField = new JTextField(firstName, 10);
            JTextField lastNameField = new JTextField(lastName, 10);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("First Name:"));
            panel.add(firstNameField);
            panel.add(new JLabel("Last Name:"));
            panel.add(lastNameField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Author", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Updated Author: " + firstNameField.getText() + " " + lastNameField.getText());
                try {
                    Database.editAuthor(authorID, firstNameField.getText(), lastNameField.getText());
                    updateAuthorsTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating author: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No author selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAuthorDialog() {
        int selectedRow = authorsTable.getSelectedRow();
        if (selectedRow >= 0) {
            int authorID = Integer.parseInt(authorsModel.getValueAt(selectedRow, 0).toString());
            String firstName = authorsModel.getValueAt(selectedRow, 1).toString();
            String lastName = authorsModel.getValueAt(selectedRow, 2).toString();
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete author: " + firstName + " " + lastName + "?", "Delete Author",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Deleted Author: " + firstName + " " + lastName);
                try {
                    Database.deleteAuthor(authorID);
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting author: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No author selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTitleDialog() {
        JTextField isbnField = new JTextField(10);
        JTextField titleField = new JTextField(10);
        JTextField editionField = new JTextField(10);
        JTextField yearField = new JTextField(4);
        Map<String, Integer> authorNamesMap = getAuthorNames();
        String[] authorNames = authorNamesMap.keySet().toArray(new String[0]);
        JList<String> authorsList = new JList<>(authorNames);
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

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Title", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int edition;
            try {
                edition = Integer.parseInt(editionField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Edition must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<String> selectedAuthors = authorsList.getSelectedValuesList();
            List<Integer> authorIDs = new ArrayList<>();
            for (String authorName : selectedAuthors) {
                authorIDs.add(authorNamesMap.get(authorName));
            }

            System.out.println("New book with ISBN: " + isbnField.getText() + " Title: " + titleField.getText()
                    + ", Edition: " + edition + ", Year: " + yearField.getText() + ", Authors: " + selectedAuthors);
            try {
                Database.addTitle(isbnField.getText(), titleField.getText(), edition, yearField.getText(), authorIDs);
                updateTitlesTable();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding title: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editTitleDialog() {
        int selectedRow = titlesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String isbn = titlesModel.getValueAt(selectedRow, 0).toString();
            String title = titlesModel.getValueAt(selectedRow, 1).toString();
            String editionNumber = titlesModel.getValueAt(selectedRow, 2).toString();
            String copyrightYear = titlesModel.getValueAt(selectedRow, 3).toString();
            Map<String, Integer> authorNamesMap = getAuthorNames();
            String[] authorNames = authorNamesMap.keySet().toArray(new String[0]);
            JList<String> authorsList = new JList<>(authorNames);
            authorsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            authorsList.setLayoutOrientation(JList.VERTICAL);
            authorsList.setVisibleRowCount(3);
            authorsList.setToolTipText("Hold Ctrl to select multiple authors");
            int[] authorIndices = getSelectedAuthorIndices(authorNames, isbn);
            authorsList.setSelectedIndices(authorIndices);
            JScrollPane authorsScrollPane = new JScrollPane(authorsList);
            JTextField isbnField = new JTextField(isbn, 10);
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
            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Title", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int edition;
                try {
                    edition = Integer.parseInt(editionField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Edition must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<String> selectedAuthors = authorsList.getSelectedValuesList();
                List<Integer> authorIDs = new ArrayList<>();
                for (String authorName : selectedAuthors) {
                    authorIDs.add(authorNamesMap.get(authorName));
                }

                System.out.println("Updated book with ISBN: " + isbnField.getText() + " Title: " + titleField.getText()
                        + ", Edition: " + edition + ", Copyright Year: " + copyrightYearField.getText() + ", Authors: "
                        + selectedAuthors);
                try {
                    Database.editTitle(isbn, titleField.getText(), edition, copyrightYearField.getText(), authorIDs);
                    updateTitlesTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating title: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No title selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTitleDialog() {
        int selectedRow = titlesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String isbn = titlesModel.getValueAt(selectedRow, 0).toString();
            String title = titlesModel.getValueAt(selectedRow, 1).toString();
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete book: " + title + "?",
                    "Delete Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Deleted Book: " + title);
                try {
                    Database.deleteTitle(isbn);
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting title: " + e.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No title selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTables() {
        updateAuthorsTable();
        updateTitlesTable();
    }

    private void updateAuthorsTable() {
        ResultSet authors = null;
        try {
            authors = Database.getAuthors();
            authorsModel.setRowCount(0);

            while (authors.next()) {
                String authorID = authors.getString("authorid");
                String firstName = authors.getString("firstname");
                String lastName = authors.getString("lastname");
                authorsModel.addRow(new Object[] { authorID, firstName, lastName });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading authors: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (authors != null) {
                    authors.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
        }
    }

    private void updateTitlesTable() {
        ResultSet titles = null;
        try {
            titles = Database.getTitles();
            titlesModel.setRowCount(0);

            while (titles.next()) {
                String isbn = titles.getString("isbn");
                String title = titles.getString("title");
                String editionNumber = titles.getString("editionnumber");
                String copyrightYear = titles.getString("copyright");
                String[] authors = Database.getAuthorsNamesForTitle(isbn);
                titlesModel.addRow(
                        new Object[] { isbn, title, editionNumber, copyrightYear,
                                String.join(", ", authors) });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading titles: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (titles != null) {
                    titles.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
        }
    }

    private Map<String, Integer> getAuthorNames() {
        Map<String, Integer> authorNames = new HashMap<>();
        try {
            ResultSet authors = Database.getAuthors();
            while (authors.next()) {
                String firstName = authors.getString("firstname");
                String lastName = authors.getString("lastname");
                String fullName = firstName + " " + lastName;
                authorNames.put(fullName, authors.getInt("authorid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding title: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return authorNames;
    }

    private int[] getSelectedAuthorIndices(String[] authorNames, String isbn) {
        String[] authors;
        try {
            authors = Database.getAuthorsNamesForTitle(isbn);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding title: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

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
