package veiw;

import control.StudentManager;
import model.Student;
import storage.StudentFile;

import java.io.IOException;
import java.util.Scanner;

public class StudentMenu {
    private StudentMenu() {
    }

    public static StudentMenu getInstance() {
        return StudentMenu.StudentMenuWithManagerStudentHelper.INSTANCE;
    }

    private static class StudentMenuWithManagerStudentHelper {
        private static final StudentMenu INSTANCE = new StudentMenu();
    }

    public void runStudent() {
        Scanner number = new Scanner(System.in);
        StudentManager managerStudent = StudentManager.getInstance();
        try {
            managerStudent.setStudentArrayList(StudentFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        int choice = -1;

        while (choice != 0) {
            System.out.println("--------Quản lý sinh viên--------");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tìm kiếm theo mã sinh viên");
            System.out.println("5. Dách sách sinh viên");
            System.out.println("0. Quay lại");
            System.out.println("----------------図書館--------------");

            choice = number.nextInt();

            switch (choice) {
                case 1:
                    inputStudent(managerStudent);
                    break;
                case 2:
                    editStudent(managerStudent);
                    break;
                case 3:
                    removeStudent(managerStudent);
                    break;
                case 4:
                    System.out.println(managerStudent.searchStudentByCode(inputCode()));
                    break;
                case 5:
                    managerStudent.showAllStudent();
                    break;
                case 0:
            }
        }
    }

    //xóa sinh viên
    private void removeStudent(StudentManager managerStudent) {
        managerStudent.removeStudent(inputCode());
    }


    //sửa thông tin
    private void editStudent(StudentManager managerStudent) {
        managerStudent.editStudent(inputCode());

    }

    //tạo mới sinh viên
    private void inputStudent(StudentManager managerStudent) {
        managerStudent.addStudent(addStudent());

    }

    // them sinh vien.da fix.
    public static Student addStudent() {
        Scanner string = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên sinh viên: ");
        String nameStudent = string.nextLine();
        System.out.print("Nhập mã sinh viên: ");
        String codeStudent = string.nextLine();
        System.out.print("Nhập năm sinh: ");
        String yearOfBirth = string.nextLine();
        System.out.print("Nhập tên lớp: ");
        String class1 = string.nextLine();
        System.out.print("Nhập số tiền hiện có: ");
        double balance = sc.nextDouble();
        return new Student(nameStudent, codeStudent, yearOfBirth, class1, balance);
    }

    //nhập code sinh viên
    private String inputCode() {
        System.out.print("Nhập code sinh viên: ");
        Scanner string = new Scanner(System.in);
        return string.nextLine();

    }
}