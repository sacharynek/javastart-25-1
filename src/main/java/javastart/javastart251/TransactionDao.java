package javastart.javastart251;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            String sql = "SELECT * FROM transactions WHERE id = ?";
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


    public List<Transaction> getAllTransactions() {
        Connection connection = connect();
        List<Transaction> output = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM transactions";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idFromDb = resultSet.getLong("id");
                boolean ttype = resultSet.getBoolean("ttype");
                String description = resultSet.getString("tdescription");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("tdate");
                output.add(new Transaction(idFromDb, ttype, description, amount, date));
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);

        return output;
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


    public List<Transaction> getAllIncome() {

        Connection connection = connect();
        List<Transaction> output = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM transactions where ttype='1'";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idFromDb = resultSet.getLong("id");
                boolean ttype = resultSet.getBoolean("ttype");
                String description = resultSet.getString("tdescription");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("tdate");
                output.add(new Transaction(idFromDb, ttype, description, amount, date));
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);

        return output;
    }

    public List<Transaction> getAllCost() {

        Connection connection = connect();
        List<Transaction> output = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM transactions where ttype='0' ";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idFromDb = resultSet.getLong("id");
                boolean ttype = resultSet.getBoolean("ttype");
                String description = resultSet.getString("tdescription");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("tdate");
                output.add(new Transaction(idFromDb, ttype, description, amount, date));
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);

        return output;
    }
}
