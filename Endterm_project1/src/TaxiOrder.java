import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface TaxiOrder {
    void createOrder(Connection connection, Scanner scanner) throws SQLException;

}
