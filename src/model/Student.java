package model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String studentCode;
    private String yearOfBirth;
    private String class1;
    private double balance;

    public Student() {
    }

    public Student(String name, String studentCode, String yearOfBirth, String class1, double balance) {
        this.name = name;
        this.studentCode = studentCode;
        this.yearOfBirth = yearOfBirth;
        this.class1 = class1;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Họ và tên: " + name +
                ", Mã Sinh viên: " + studentCode +
                ", Năm sinh: " + yearOfBirth +
                ", Lớp: " + class1 +
                ", Số tiền: " + balance;


    }
}