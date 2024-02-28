import java.sql.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String user = "postgres";
    private static final String password = "Supoga80";
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";



    public static void main(String[] args) throws SQLException {
        try(Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(url, user, password)) {
            TaxiApp taxiApp = new TaxiApp(connection, scanner);
            taxiApp.start();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}