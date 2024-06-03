// Accessing Databases - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 21, 2024

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Database class that provides methods to interact with the books database.
 */
class Database {
    // Constants for database connection.
    final static String DATABASE_URL = "jdbc:derby://localhost:1527/books";
    final static String DATABASE_USERNAME = "vine";
    final static String DATABASE_PASSWORD = "vine";

    /**
     * Get all authors from the authors table.
     * 
     * @return ResultSet containing all authors from the authors table.
     * @throws SQLException If an error occurs while querying the database.
     */
    public static ResultSet getAuthors() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT authorid, firstname, lastname FROM authors";
        return statement.executeQuery(selectQuery);
    }

    /**
     * Get all titles from the titles table.
     * 
     * @return ResultSet containing all titles from the titles table.
     * @throws SQLException If an error occurs while querying the database.
     */
    public static ResultSet getTitles() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT isbn, title, copyright, editionnumber FROM titles";
        return statement.executeQuery(selectQuery);
    }

    /**
     * Adds an author to the authors table.
     * 
     * @param firstName The first name of the author.
     * @param lastName  The last name of the author.
     * @throws SQLException If an error occurs while inserting into the database.
     */
    public static void addAuthor(String firstName, String lastName) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String insertQuery = "INSERT INTO authors (firstname, lastname) VALUES ('" + firstName + "', '"
                + lastName
                + "')";
        statement.executeUpdate(insertQuery);
    }

    /**
     * Edits an author in the authors table.
     * 
     * @param authorID  The ID of the author to edit.
     * @param firstName The new first name of the author.
     * @param lastName  The new last name of the author.
     * @throws SQLException If an error occurs while updating the database.
     */
    public static void editAuthor(int authorID, String firstName, String lastName) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String updateQuery = "UPDATE authors SET firstname = '" + firstName + "', lastname = '" + lastName
                + "' WHERE authorid = " + authorID;
        statement.executeUpdate(updateQuery);
    }

    /**
     * Deletes an author from the authors table.
     * 
     * @param authorID The ID of the author to delete.
     * @throws SQLException If an error occurs while deleting from the database.
     */
    public static void deleteAuthor(int authorID) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();

        // Delete any authorship from the authorisbn table.
        String deleteAuthorISBNQuery = "DELETE FROM authorisbn WHERE authorid = " + authorID;
        statement.executeUpdate(deleteAuthorISBNQuery);

        // Delete the author from the authors table.
        String deleteQuery = "DELETE FROM authors WHERE authorid = " + authorID;
        statement.executeUpdate(deleteQuery);
    }

    /**
     * Adds a title to the titles table.
     * 
     * @param isbn          The ISBN of the title.
     * @param title         The title of the book.
     * @param editionNumber The edition number of the title.
     * @param year          The copyright year for the title.
     * @param authorIDs     The IDs of the authors of the title.
     * @throws SQLException If an error occurs while inserting into the database.
     */
    public static void addTitle(String isbn, String title, int editionNumber, String year, List<Integer> authorIDs)
            throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();

        // Insert the title into the titles table.
        String insertQuery = "INSERT INTO titles (isbn, title, editionnumber, copyright) VALUES ('" + isbn
                + "', '" + title + "', " + editionNumber + ", '" + year + "')";
        statement.executeUpdate(insertQuery);

        // Insert the authorship for the title into the authorisbn table.
        for (int authorID : authorIDs) {
            String insertAuthorQuery = "INSERT INTO authorisbn (authorid, isbn) VALUES (" + authorID + ", '" + isbn
                    + "')";
            statement.executeUpdate(insertAuthorQuery);
        }
    }

    /**
     * Edits a title in the titles table.
     * 
     * @param isbn          The ISBN of the title to edit.
     * @param title         The new title of the book.
     * @param editionNumber The new edition number of the title.
     * @param year          The new copyright year for the title.
     * @param authorIDs     The new IDs of the authors of the title.
     * @throws SQLException If an error occurs while updating the database.
     */
    public static void editTitle(String isbn, String title, int editionNumber, String year, List<Integer> authorIDs)
            throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();

        // Update the title in the titles table.
        String updateQuery = "UPDATE titles SET title = '" + title + "', editionnumber = " + editionNumber + ", "
                + "copyright = '" + year
                + "' WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(updateQuery);

        // Delete any authorship for the title from the authorisbn table.
        String deleteAuthorISBNQuery = "DELETE FROM authorisbn WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(deleteAuthorISBNQuery);

        // Insert the new authorship for the title into the authorisbn table.
        for (int authorID : authorIDs) {
            String insertAuthorQuery = "INSERT INTO authorisbn (authorid, isbn) VALUES (" + authorID + ", '" + isbn
                    + "')";
            statement.executeUpdate(insertAuthorQuery);
        }
    }

    /**
     * Deletes a title from the titles table.
     * 
     * @param isbn The ISBN of the title to delete.
     * @throws SQLException If an error occurs while deleting from the database.
     */
    public static void deleteTitle(String isbn) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();

        // Delete any authorship for the title from the authorisbn table.
        String deleteAuthorISBNQuery = "DELETE FROM authorisbn WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(deleteAuthorISBNQuery);

        // Delete the title from the titles table.
        String deleteQuery = "DELETE FROM titles WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(deleteQuery);
    }

    /**
     * Get the names of the authors for a title.
     * 
     * @param isbn The ISBN of the title.
     * @return An array of author full names for the title.
     * @throws SQLException If an error occurs while querying the database.
     */
    public static String[] getAuthorsNamesForTitle(String isbn) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();

        // Query the authors for the title.
        String selectQuery = "SELECT a.firstname, a.lastname FROM authors a JOIN authorisbn ai ON a.authorid = ai.authorid WHERE ai.isbn = '"
                + isbn + "'";
        ResultSet resultSet = statement.executeQuery(selectQuery);

        // Get the full names for each author.
        List<String> authorNames = new ArrayList<>();
        while (resultSet.next()) {
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            String fullName = firstName + " " + lastName;
            authorNames.add(fullName);
        }

        return authorNames.toArray(new String[0]);
    }
}
