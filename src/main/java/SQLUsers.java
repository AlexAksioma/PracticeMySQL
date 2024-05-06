import java.sql.*;
import java.util.Random;

public class SQLUsers {
    public static final String URL = "jdbc:mysql://localhost:3306/users";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";

    static Connection connection;
    static Statement statement;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            //connection.close();
            if (!connection.isClosed())
                System.out.println("connection created");
            else
                System.out.println("connection closed");
            int i = new Random().nextInt(1000) + 1000;
            statement = connection.createStatement();
            statement.executeUpdate("insert  into user_table (id, email, password) " +
                    "values (" + i + ", 'new_added1@mail.com', 'added" + i + "');");
            ResultSet resultSet = statement.executeQuery("select * from user_table;");
            while (resultSet.next()) {
                System.out.print(resultSet.getString("id") + "  ");
                System.out.print(resultSet.getString(2) + "  ");
                System.out.println(resultSet.getString(3) + "  ");
            }
            System.out.println("================================================");
            if( statement.execute("select * from user_table where id=1071;")){
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    System.out.print(resultSet.getString("id") + "  ");
                    System.out.print(resultSet.getString(2) + "  ");
                    System.out.println(resultSet.getString(3) + "  ");
                }
            }

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
