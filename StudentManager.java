package com.example.student_management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 学生管理类，用于管理学生信息的增删改查操作
public class StudentManager {
    // 存储学生信息的列表
    private List<Student> students;
    // 存储学生信息的文件名
    private static final String FILE_NAME = "students.txt";

    // 构造方法，初始化学生列表并从文件加载学生信息
    public StudentManager() {
        this.students = new ArrayList<>();
        loadFromFile();
    }

    // 添加学生信息到列表并保存到文件
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // 从列表中删除指定学号的学生信息并保存到文件
    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
        saveToFile();
    }

    // 获取所有学生信息列表的副本
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // 根据学号搜索学生信息
    public Student searchStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // 根据姓名搜索学生信息，可能有多个同名学生，返回列表
    public List<Student> searchStudentByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }
        return result;
    }

    // 更新学生信息并保存到文件
    public void updateStudent(String id, String name, int age, String gender) {
        Student student = searchStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
            student.setGender(gender);
            saveToFile();
        }
    }

    // 保存学生信息到文件
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.println(student.toFileString());
            }
        } catch (IOException e) {
            System.err.println("保存学生数据到文件时发生错误: " + e.getMessage());
        }
    }

    // 从文件加载学生信息到列表
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("文件不存在，创建新的文件。");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("创建文件时发生错误: " + e.getMessage());
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (IOException e) {
            System.err.println("从文件加载学生数据时发生错误: " + e.getMessage());
        }
    }
}
