package main;

import java.util.Date;
public class Task {

    private int id;
    private String taskName;
    private Date dueDate;
    private int status;

    public Task(int id, String taskName, Date dueDate, int status) {
        this.id = id;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.status = status;
    }
}
