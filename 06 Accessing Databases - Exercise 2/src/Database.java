import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

class Database {
    final static String DATABASE_URL = "jdbc:derby://localhost:1527/books";
    final static String DATABASE_USERNAME = "vine";
    final static String DATABASE_PASSWORD = "vine";

    public static ResultSet getAuthors() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT authorid, firstname, lastname FROM authors";
        return statement.executeQuery(selectQuery);
    }

    public static ResultSet getTitles() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT isbn, title, copyright, editionnumber FROM titles";
        return statement.executeQuery(selectQuery);
    }

    public static void addAuthor(String firstName, String lastName) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String insertQuery = "INSERT INTO authors (firstname, lastname) VALUES ('" + firstName + "', '"
                + lastName
                + "')";
        statement.executeUpdate(insertQuery);
    }

    public static void editAuthor(int authorID, String firstName, String lastName) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String updateQuery = "UPDATE authors SET firstname = '" + firstName + "', lastname = '" + lastName
                + "' WHERE authorid = " + authorID;
        statement.executeUpdate(updateQuery);
    }

    public static void deleteAuthor(int authorID) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();

        String deleteAuthorISBNQuery = "DELETE FROM authorisbn WHERE authorid = " + authorID;
        statement.executeUpdate(deleteAuthorISBNQuery);

        String deleteQuery = "DELETE FROM authors WHERE authorid = " + authorID;
        statement.executeUpdate(deleteQuery);
    }

    public static void addTitle(String isbn, String title, int editionNumber, String year, List<Integer> authorIDs)
            throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String insertQuery = "INSERT INTO titles (isbn, title, editionnumber, copyright) VALUES ('" + isbn
                + "', '" + title + "', " + editionNumber + ", '" + year + "')";
        statement.executeUpdate(insertQuery);

        for (int authorID : authorIDs) {
            String insertAuthorQuery = "INSERT INTO authorisbn (authorid, isbn) VALUES (" + authorID + ", '" + isbn
                    + "')";
            statement.executeUpdate(insertAuthorQuery);
        }
    }

    public static void editTitle(String isbn, String title, int editionNumber, String year, List<Integer> authorIDs)
            throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String updateQuery = "UPDATE titles SET title = '" + title + "', editionnumber = " + editionNumber + ", "
                + "copyright = '" + year
                + "' WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(updateQuery);

        String deleteAuthorISBNQuery = "DELETE FROM authorisbn WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(deleteAuthorISBNQuery);

        for (int authorID : authorIDs) {
            String insertAuthorQuery = "INSERT INTO authorisbn (authorid, isbn) VALUES (" + authorID + ", '" + isbn
                    + "')";
            statement.executeUpdate(insertAuthorQuery);
        }
    }

    public static void deleteTitle(String isbn) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();

        String deleteAuthorISBNQuery = "DELETE FROM authorisbn WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(deleteAuthorISBNQuery);

        String deleteQuery = "DELETE FROM titles WHERE isbn = '" + isbn + "'";
        statement.executeUpdate(deleteQuery);
    }

    public static String[] getAuthorsNamesForTitle(String isbn) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT a.firstname, a.lastname FROM authors a JOIN authorisbn ai ON a.authorid = ai.authorid WHERE ai.isbn = '"
                + isbn + "'";
        ResultSet resultSet = statement.executeQuery(selectQuery);

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
