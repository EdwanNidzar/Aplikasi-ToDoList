package main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("=== TO-DO LIST ===");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Lihat Semua Tugas");
            System.out.println("3. Tandai Tugas Selesai");
            System.out.println("0. Keluar");

            System.out.print("Pilihan Anda: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (choice) {
                case 1:
                    System.out.print("Nama Tugas: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Due Date (yyyy-MM-dd): ");
                    String dueDateString = scanner.nextLine();
                    try {
                        Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                        TaskManager.addTask(taskName, dueDate);
                        System.out.println("Tugas berhasil ditambahkan.");
                    } catch (ParseException | SQLException e) {
                        System.out.println("Terjadi kesalahan: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        List<Task> tasks = TaskManager.getAllTasks();
                        if (tasks.isEmpty()) {
                            System.out.println("Tidak ada tugas.");
                        } else {
                            System.out.println("Daftar Tugas:");
                            for (Task task : tasks) {
                                System.out.println(task.getId() + ". " + task.getTaskName() + " (Due Date: " + task.getDueDate() + ", Status: " + (task.getStatus() == 0 ? "Belum Selesai" : "Selesai") + ")");
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Terjadi kesalahan: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("ID Tugas yang selesai: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan newline
                    try {
                        TaskManager.markTaskAsDone(taskId);
                        System.out.println("Tugas berhasil ditandai selesai.");
                    } catch (SQLException e) {
                        System.out.println("Terjadi kesalahan: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
            System.out.println();
        }
    }
}
