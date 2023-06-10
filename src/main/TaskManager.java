package main;

import koneksi.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TaskManager {

    public static void addTask(String taskName, Date dueDate) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (task_name, due_date, status) VALUES (?, ?, 0)");

        statement.setString(1, taskName);
        statement.setDate(2, new java.sql.Date(dueDate.getTime()));

        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
