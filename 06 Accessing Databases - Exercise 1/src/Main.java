import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.IntStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Main {
    final static String DATABASE_URL = "jdbc:derby://localhost:1527/books";

    public static void main(String args[]) {
        System.out.println("\nAuthors Table in the Books Database:");
        selectQueryAndPrintResults("SELECT * FROM authors", new int[] {});

        int authorID = 1;
        System.out.println("\nPaul Deitel's books:");
        selectQueryAndPrintResults(
                "SELECT t.title, t.copyright, ai.isbn "
                        + "FROM titles t "
                        + "JOIN authorisbn ai ON t.isbn = ai.isbn "
                        + "WHERE ai.authorid = " + authorID
                        + " ORDER BY t.copyright",
                new int[] { 1 });

        String isbn = "0132121360";
        System.out.println("\nAuthors of \"Android for Programmers: An App-Driven Approach\":");
        selectQueryAndPrintResults(
                "SELECT a.authorid, a.firstname, a.lastname "
                        + "FROM authors a "
                        + "JOIN authorisbn ai ON a.authorid = ai.authorid "
                        + "WHERE ai.isbn = '" + isbn + "' "
                        + "ORDER BY a.lastname, a.firstname",
                new int[] {});

        int year = 2014;
        System.out.println("\nBooks written in 2014:");
        selectQueryAndPrintResults(
                "SELECT * "
                        + "FROM titles "
                        + "WHERE copyright = '" + year + "' ",
                new int[] { 2 });

        System.out.println("\nNumber of books written by each author:");
        selectQueryAndPrintResults(
                "SELECT a.authorid, a.firstname, a.lastname, COUNT(ai.isbn) AS bookcount "
                        + "FROM authors a "
                        + "JOIN authorisbn ai ON a.authorid = ai.authorid "
                        + "GROUP BY a.authorid, a.firstname, a.lastname "
                        + "ORDER BY bookcount DESC",
                new int[] {});
    }

    public static boolean contains(int[] array, int num) {
        return IntStream.of(array).anyMatch(x -> x == num);
    }

    public static void selectQueryAndPrintResults(String selectQuery, int[] longColumns) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, "vine", "vine");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                int width = 10;
                if (contains(longColumns, i)) {
                    width = 60;
                }
                System.out.printf("%-" + width + "s\t", metaData.getColumnName(i));
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    int width = 10;
                    if (contains(longColumns, i)) {
                        width = 60;
                    }
                    System.out.printf("%-" + width + "s\t", resultSet.getObject(i));
                }
                System.out.println();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
