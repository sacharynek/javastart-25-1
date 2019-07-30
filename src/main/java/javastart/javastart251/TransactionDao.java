package javastart.javastart251;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDao {

    public void insert(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO transactions(ttype, tdescription, amount, tdate) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, transaction.getTType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setBigDecimal(3, transaction.getAmount());
            preparedStatement.setDate(4, transaction.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);
    }

    public Transaction findByID(Long id) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM transactions WHERE isbn = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                long idFromDb = resultSet.getLong("id");
                boolean ttype = resultSet.getBoolean("ttype");
                String description = resultSet.getString("tdescription");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("tdate");
                Transaction transaction = new Transaction(idFromDb, ttype, description, amount, date);
                return transaction;
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);

        return null;
    }


    public void update(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE transactions SET ttype = ? , tdescription = ?, amount = ?, tdate = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, transaction.getTType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setBigDecimal(3,transaction.getAmount());
            preparedStatement.setDate(4, transaction.getDate());
            preparedStatement.setLong(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);
    }

    public void deleteByID(Long id) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM transactions WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);
    }



    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/budget?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";
        try {
            return DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}
