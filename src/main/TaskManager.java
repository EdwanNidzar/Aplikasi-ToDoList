package main;

import koneksi.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<Task> getAllTasks() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String taskName = resultSet.getString("task_name");
            Date dueDate = resultSet.getDate("due_date");
            int status = resultSet.getInt("status");
            Task task = new Task(id, taskName, dueDate, status);
            tasks.add(task);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return tasks;
    }

}
