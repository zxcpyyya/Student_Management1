package com.example.student_management;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        // 主循环，控制程序流程
        while (true) {
            // 显示主菜单
            showMainMenu();
            // 获取用户选择的操作
            int choice = scanner.nextInt();
            scanner.nextLine();

            // 根据用户选择执行相应操作
            switch (choice) {
                case 1:
                    // 循环执行添加学生操作，直到用户不再继续添加
                    do {
                        addStudent(manager, scanner);
                        System.out.print("继续添加学生信息吗？(y/n): ");
                    } while (scanner.nextLine().equalsIgnoreCase("y"));
                    break;
                case 2:
                    // 循环执行删除学生操作，直到用户不再继续删除
                    do {
                        deleteStudent(manager, scanner);
                        System.out.print("继续删除学生信息吗？(y/n): ");
                    } while (scanner.nextLine().equalsIgnoreCase("y"));
                    break;
                case 3:
                    // 查看所有学生信息，并在显示完毕后询问用户是否返回主界面
                    viewAllStudents(manager, scanner);
                    break;
                case 4:
                    // 循环执行按ID搜索学生操作，直到用户不再继续搜索
                    do {
                        searchStudentById(manager, scanner);
                        System.out.print("继续按ID搜索学生信息吗？(y/n): ");
                    } while (scanner.nextLine().equalsIgnoreCase("y"));
                    break;
                case 5:
                    // 循环执行按名字搜索学生操作，直到用户不再继续搜索
                    do {
                        searchStudentByName(manager, scanner);
                        System.out.print("继续按名字搜索学生信息吗？(y/n): ");
                    } while (scanner.nextLine().equalsIgnoreCase("y"));
                    break;
                case 6:
                    // 循环执行修改学生信息操作，直到用户不再继续修改
                    do {
                        updateStudent(manager, scanner);
                        System.out.print("继续修改学生信息吗？(y/n): ");
                    } while (scanner.nextLine().equalsIgnoreCase("y"));
                    break;
                case 7:
                    // 退出系统，关闭输入流
                    System.out.println("成功退出系统！欢迎再次使用");
                    scanner.close();
                    return;
                default:
                    // 用户选择无效操作时，提示并重新显示主菜单
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }

    // 显示主菜单
    private static void showMainMenu() {
        System.out.println("1. 添加学生");
        System.out.println("2. 删除学生");
        System.out.println("3. 查看所有学生");
        System.out.println("4. 按ID搜索学生");
        System.out.println("5. 按名字搜索学生");
        System.out.println("6. 修改学生信息");
        System.out.println("7. 退出");
        System.out.print("请选择操作：");
    }

    // 添加学生信息
    private static void addStudent(StudentManager manager, Scanner scanner) {
        System.out.print("输入学生ID：");
        String id = scanner.nextLine();
        if (manager.searchStudentById(id) != null) {
            System.out.println("该学号已存在！");
            return;
        }
        System.out.print("输入学生名字：");
        String name = scanner.nextLine();
        System.out.print("输入学生年龄：");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("输入学生性别：");
        String gender = scanner.nextLine();
        manager.addStudent(new Student(id, name, age, gender));
        System.out.println("学生添加成功！");
    }

    // 删除学生信息
    private static void deleteStudent(StudentManager manager, Scanner scanner) {
        System.out.print("输入要删除的学生ID：");
        String deleteId = scanner.nextLine();
        Student studentToDelete = manager.searchStudentById(deleteId);
        if (studentToDelete != null) {
            manager.removeStudent(deleteId);
            System.out.println("学生删除成功！");
        } else {
            System.out.println("未找到学生！");
        }
    }

    // 查看所有学生信息，并询问用户是否返回主界面
    private static void viewAllStudents(StudentManager manager, Scanner scanner) {
        List<Student> students = manager.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.print("按y返回主界面：");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("y")) {
            System.out.print("无效输入，按y返回主界面：");
            input = scanner.nextLine();
        }
    }

    // 按ID搜索学生信息
    private static void searchStudentById(StudentManager manager, Scanner scanner) {
        System.out.print("输入学生ID：");
        String searchId = scanner.nextLine();
        Student student = manager.searchStudentById(searchId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("未找到学生！");
        }
    }

    // 按名字搜索学生信息
    private static void searchStudentByName(StudentManager manager, Scanner scanner) {
        System.out.print("输入学生名字：");
        String searchName = scanner.nextLine();
        List<Student> foundStudents = manager.searchStudentByName(searchName);
        if (!foundStudents.isEmpty()) {
            for (Student s : foundStudents) {
                System.out.println(s);
            }
        } else {
            System.out.println("未找到学生！");
        }
    }

    // 修改学生信息
    private static void updateStudent(StudentManager manager, Scanner scanner) {
        System.out.print("输入要修改的学生ID：");
        String updateId = scanner.nextLine();
        Student studentToUpdate = manager.searchStudentById(updateId);
        if (studentToUpdate != null) {
            System.out.print("输入新的学生名字：");
            String newName = scanner.nextLine();
            System.out.print("输入新的学生年龄：");
            int newAge = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("输入新的学生性别：");
            String newGender = scanner.nextLine();
            manager.updateStudent(updateId, newName, newAge, newGender);
            System.out.println("学生信息修改成功！");
        } else {
            System.out.println("未找到学生！");
        }
    }
}
