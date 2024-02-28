import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


class SimpleTaxiOrder implements TaxiOrder {
    @Override
    public void createOrder(Connection connection, Scanner scanner) throws SQLException {

        String sql = "insert into orders(deliver_address, destiantion_address, delivery_time, price, passenger, driver) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        scanner.nextLine();

        System.out.println("Введите адрес доставки: ");
        String deliverAddress = scanner.nextLine();

        System.out.println("Введите адрес назначения: ");
        String destinationAddress = scanner.nextLine();

        System.out.println("Введите время доставки: ");
        String deliveryTime = scanner.nextLine();

        System.out.println("Введите цену: ");
        String deliveryPrice = scanner.nextLine();

        System.out.println("Введите ваше имя: ");
        String passenger = scanner.nextLine();

        System.out.println("Выберите таксиста: ");
        Statement statement = connection.createStatement();
        String select = "select * from driver order by id";
        ResultSet result = statement.executeQuery(select);

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("surname") + " " + result.getInt("raiting"));
        }

        String driverId = scanner.nextLine();
        preparedStatement.setString(1, deliverAddress);
        preparedStatement.setString(2, destinationAddress);
        preparedStatement.setString(3, deliveryTime);
        int price = Integer.parseInt(deliveryPrice);
        preparedStatement.setInt(4, price);
        preparedStatement.setString(5, passenger);
        preparedStatement.setString(6, driverId);

        System.out.println("1. Одобрить заказ");
        System.out.println("2. Отменить заказ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Ваш заказ одобрен!");
        } else {
            System.out.println("Ваш заказ отменён!");
            preparedStatement.executeUpdate();
        }
        System.out.println();
    }
}