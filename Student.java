package com.example.student_management;

// 学生类，用于表示学生信息
public class Student {
    // 学生属性
    private String id;      // 学号
    private String name;    // 姓名
    private int age;        // 年龄
    private String gender;  // 性别

    // 构造方法，用于创建学生对象
    public Student(String id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getter 和 Setter 方法，用于获取和设置学生属性值
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    // 重写 toString() 方法，用于将学生对象转换为字符串表示
    @Override
    public String toString() {
        return String.format("学号：%s   姓名：%s   年龄：%d   性别：%s", id, name, age, gender);
    }

    // 将学生对象转换为文件格式字符串的方法
    public String toFileString() {
        return String.format("%s,%s,%d,%s", id, name, age, gender);
    }

    // 从文件格式字符串中创建学生对象的静态方法
    public static Student fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        return new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
    }
}
