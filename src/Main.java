import java.sql.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args)
    {
        insertCarToDB("ZMB 159", "VOLVO XC70");

        for (String car : getAllCars()) {
            System.out.println(car);
        }


    }

    private static ArrayList<String> getAllCars(){
        ArrayList<String> arrayList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        Connection databaseConnection = null; // prisijungimas prie duombazes
        PreparedStatement databasePrepareStatement = null; // uzklausu siuntimui

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase1?useSSL=false", "root", "");
            if (databaseConnection != null) {
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try {
            String selectQueryStatement = "SELECT * from rental_cars";

            databasePrepareStatement = databaseConnection.prepareStatement(selectQueryStatement);
            ResultSet results = databasePrepareStatement.executeQuery();

            while (results.next()) {
                stringBuilder.delete(0,stringBuilder.length());
                stringBuilder.append("ID: " + results.getString("id") + " CAR MAKE: " + results.getString("car_make") + "   CURRENT DRIVER ID: " + results.getString("user_id"));
                arrayList.add(stringBuilder.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    private static void insertCarToDB(String numberPlates, String CarMake){
        Connection databaseConnection = null; // prisijungimas prie duombazes
        PreparedStatement databasePrepareStatement = null; // uzklausu siuntimui

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase1?useSSL=false", "root", "");
            if (databaseConnection != null) {
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            String selectQueryStatement = "INSERT INTO `rental_cars` (`number_plate`, `car_make`) VALUES ('"+ numberPlates +"', '"+ CarMake +"')";

            databasePrepareStatement = databaseConnection.prepareStatement(selectQueryStatement);
            databasePrepareStatement.executeQuery();

//            while (results.next()) {
//                stringBuilder.delete(0,stringBuilder.length());
//                stringBuilder.append("ID: " + results.getString("id") + " CAR MAKE: " + results.getString("car_make") + "   CURRENT DRIVER ID: " + results.getString("user_id"));
//                arrayList.add(stringBuilder.toString());
//
//            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

//INSERT INTO `rental_cars` (`id`, `number_plate`, `car_make`, `user_id`) VALUES (NULL, 'GAK 381', 'VW GOLF MK4', NULL);
//INSERT INTO `rental_users` (`id`, `email`, `name`, `created_at`) VALUES (NULL, 'barsukas@miskas.lt', 'Meska13', CURRENT_TIMESTAMP);