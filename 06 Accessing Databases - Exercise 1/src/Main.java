// Accessing Databases - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 21, 2024

import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.IntStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Main class that demonstrates the use of SQL SELECT statements to query the
 * books database.
 */
public class Main {
    // Connection URL for database.
    final static String DATABASE_URL = "jdbc:derby://localhost:1527/books";

    /**
     * Main method that demonstrates the use of SQL SELECT statements to query the
     * books database.
     * 
     * @param args Unused command-line arguments
     */
    public static void main(String args[]) {
        // Query all authors and print the results.
        System.out.println("\nAuthors Table in the Books Database:");
        selectQueryAndPrintResults("SELECT * FROM authors", new int[] {});

        // Query all titles by Paul Deitel and print the results.
        int authorID = 1;
        System.out.println("\nPaul Deitel's books:");
        selectQueryAndPrintResults(
                "SELECT t.title, t.copyright, ai.isbn "
                        + "FROM titles t "
                        + "JOIN authorisbn ai ON t.isbn = ai.isbn "
                        + "WHERE ai.authorid = " + authorID
                        + " ORDER BY t.copyright",
                new int[] { 1 });

        // Query all authors of "Android for Programmers: An App-Driven Approach" and
        // print the results.
        String isbn = "0132121360";
        System.out.println("\nAuthors of \"Android for Programmers: An App-Driven Approach\":");
        selectQueryAndPrintResults(
                "SELECT a.authorid, a.firstname, a.lastname "
                        + "FROM authors a "
                        + "JOIN authorisbn ai ON a.authorid = ai.authorid "
                        + "WHERE ai.isbn = '" + isbn + "' "
                        + "ORDER BY a.lastname, a.firstname",
                new int[] {});

        // Query all books written in 2014 and print the results.
        int year = 2014;
        System.out.println("\nBooks written in 2014:");
        selectQueryAndPrintResults(
                "SELECT * "
                        + "FROM titles "
                        + "WHERE copyright = '" + year + "' ",
                new int[] { 2 });

        // Query the number of books written by each author and print the results.
        System.out.println("\nNumber of books written by each author:");
        selectQueryAndPrintResults(
                "SELECT a.authorid, a.firstname, a.lastname, COUNT(ai.isbn) AS bookcount "
                        + "FROM authors a "
                        + "JOIN authorisbn ai ON a.authorid = ai.authorid "
                        + "GROUP BY a.authorid, a.firstname, a.lastname "
                        + "ORDER BY bookcount DESC",
                new int[] {});
    }

    /**
     * Returns true if the specified array contains the specified number.
     * 
     * @param array The array to search in.
     * @param num   The number to search for.
     * @return true if the specified array contains the specified number else false.
     */
    public static boolean contains(int[] array, int num) {
        // Use a stream to check if the array contains the specified number.
        return IntStream.of(array).anyMatch(x -> x == num);
    }

    /**
     * Executes the specified SELECT query and prints the results.
     * 
     * @param selectQuery The SELECT query to execute.
     * @param longColumns The columns that should be printed with a width of 60.
     */
    public static void selectQueryAndPrintResults(String selectQuery, int[] longColumns) {
        // Try to establish a connection to the database and execute the SELECT query.
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, "vine", "vine");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery)) {
            // Get the metadata of the result set.
            ResultSetMetaData metaData = resultSet.getMetaData();
            // Get the number of columns in the result set.
            int numberOfColumns = metaData.getColumnCount();

            // Print the column names.
            for (int i = 1; i <= numberOfColumns; i++) {
                int width = 10;
                // If the column should be printed long, set the width to 60.
                if (contains(longColumns, i)) {
                    width = 60;
                }
                System.out.printf("%-" + width + "s\t", metaData.getColumnName(i));
            }
            System.out.println();

            // For each row in the result set.
            while (resultSet.next()) {
                // Print the values of each column in the row.
                for (int i = 1; i <= numberOfColumns; i++) {
                    int width = 10;
                    // If the column should be printed long, set the width to 60.
                    if (contains(longColumns, i)) {
                        width = 60;
                    }
                    System.out.printf("%-" + width + "s\t", resultSet.getObject(i));
                }
                System.out.println();
            }
        } catch (SQLException sqlException) {
            // If an SQL error occurs, print the error.
            sqlException.printStackTrace();
        }
    }
}
