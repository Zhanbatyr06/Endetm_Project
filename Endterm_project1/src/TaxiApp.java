import java.sql.*;
import java.util.*;

public class TaxiApp {


    private final Connection connection;
    private final Scanner scanner;


    public TaxiApp(Connection connection, Scanner scanner) throws SQLException{
        this.connection = connection;
        this.scanner = scanner;
    }


    private void showDrivers() throws SQLException{
        Statement statement = connection.createStatement();
        String select = "select * from driver order by id";
        ResultSet result = statement.executeQuery(select);

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("surname") + " " + result.getInt("raiting"));

        }
        System.out.println();
        System.out.println("1. Выйти из первого пункта");
        int exit = scanner.nextInt();
        if(exit == 1){
            return;
        }
        System.out.println();
    }

    private void showCars() throws  SQLException{
        Statement statement = connection.createStatement();
        String select = "select * from car order by id";
        ResultSet result = statement.executeQuery(select);

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("make") + " " + result.getString("model") + " " + result.getString("state_number") + " " + result.getInt("capacity"));
        }
        System.out.println();
        System.out.println("1. Выйти из второго пункта");
        int exit = scanner.nextInt();
        if(exit == 1){
            return;
        }
        System.out.println();
    }

    public void start() throws SQLException{
        while (true){
            System.out.println("1. Показать список водителей");
            System.out.println("2. Показать список автомобили");
            System.out.println("3. Заказать такси");
            System.out.println("4. Выход");
            int command = scanner.nextInt();
            if(command == 1){
                showDrivers();
            } else if (command == 2) {
                showCars();
            } else if (command == 3) {
                Map<Integer, TaxiOrder> strategies = new HashMap<>();
                strategies.put(1, new SimpleTaxiOrder());
                strategies.put(2, new BusinessTaxiOrder());
                System.out.println("Выберите стратегию создания заказа: ");
                System.out.println("1. Простой заказ");
                System.out.println("2. Продвинутый заказ");
                    int strategyChoice = scanner.nextInt();
                    TaxiOrder taxiOrder = strategies.get(strategyChoice);
                    if (taxiOrder != null){
                        taxiOrder.createOrder(connection, scanner);
                    }
                    else{
                        System.err.println("Ошибка!");
                    }

            } else if (command == 4) {
                System.exit(0);
            }
            else {
                System.err.println("Команда не распознана!");
            }
        }
    }
}
