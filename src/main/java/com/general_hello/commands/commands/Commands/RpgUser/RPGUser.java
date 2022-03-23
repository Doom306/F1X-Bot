package com.general_hello.commands.commands.Commands.RpgUser;

import com.general_hello.commands.Database.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RPGUser {
    public static int getRaceIncome(long userId) {
        try (Connection connection = SQLiteDataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT RaceIncome FROM UserData WHERE UserId = ?")) {

            preparedStatement.setString(1, String.valueOf(userId));

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("RaceIncome");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addRaceIncome(long userId, int shekels) {
        int total = (shekels) + getRaceIncome(userId);

        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("UPDATE UserData SET RaceIncome=? WHERE UserId=?"
                )) {

            preparedStatement.setString(2, String.valueOf(userId));
            preparedStatement.setString(1, String.valueOf(total));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getDriverDeductions(long userId) {
        try (Connection connection = SQLiteDataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT DriverDeductions FROM UserData WHERE UserId = ?")) {

            preparedStatement.setString(1, String.valueOf(userId));

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("DriverDeductions");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addDriverDeductions(long userId, int shekels) {
        int total = (shekels) + getDriverDeductions(userId);

        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("UPDATE UserData SET DriverDeductions=? WHERE UserId=?"
                )) {

            preparedStatement.setString(2, String.valueOf(userId));
            preparedStatement.setString(1, String.valueOf(total));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getPrizeMoney(long userId) {
        try (Connection connection = SQLiteDataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT PrizeMoney FROM UserData WHERE UserId = ?")) {

            preparedStatement.setString(1, String.valueOf(userId));

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("PrizeMoney");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addPrizeMoney(long userId, int shekels) {
        int total = (shekels) + getPrizeMoney(userId);

        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("UPDATE UserData SET PrizeMoney=? WHERE UserId=?"
                )) {

            preparedStatement.setString(2, String.valueOf(userId));
            preparedStatement.setString(1, String.valueOf(total));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLoanRepayment(long userId) {
        try (Connection connection = SQLiteDataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT LoanRepayment FROM UserData WHERE UserId = ?")) {

            preparedStatement.setString(1, String.valueOf(userId));

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("LoanRepayment");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addLoanRepayment(long userId, int shekels) {
        int total = (shekels) + getLoanRepayment(userId);

        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("UPDATE UserData SET LoanRepayment=? WHERE UserId=?"
                )) {

            preparedStatement.setString(2, String.valueOf(userId));
            preparedStatement.setString(1, String.valueOf(total));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLoanPayment(long userId) {
        try (Connection connection = SQLiteDataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT LoanPayment FROM UserData WHERE UserId = ?")) {

            preparedStatement.setString(1, String.valueOf(userId));

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("LoanPayment");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addLoanPayment(long userId, int shekels) {
        int total = (shekels) + getLoanPayment(userId);

        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("UPDATE UserData SET LoanPayment=? WHERE UserId=?"
                )) {

            preparedStatement.setString(2, String.valueOf(userId));
            preparedStatement.setString(1, String.valueOf(total));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newInfo(long userId) {
        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("INSERT INTO UserData" +
                        "(UserId)" +
                        "VALUES (?);")) {

            preparedStatement.setString(1, String.valueOf(userId));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteInfo(long userId) {
        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("DELETE FROM UserData WHERE UserId=?;")) {

            preparedStatement.setString(1, String.valueOf(userId));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
