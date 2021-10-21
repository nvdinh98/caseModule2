package control;

import model.Student;
import storage.StudentFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    ArrayList<Student> studentArrayList = new ArrayList<>();
    StudentFile studentFile = StudentFile.getInstance();

    private StudentManager() {
    }

    public static StudentManager getInstance() {
        return ManagerStudentHelper.INSTANCE;
    }

    private static class ManagerStudentHelper{
        private static final StudentManager INSTANCE = new StudentManager();
    }



    public StudentManager(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public static void setStudentArrayList(ArrayList<Student> studentArrayList) {
        studentArrayList = studentArrayList;
    }

    ///thêm sinh viên
    public void addStudent(Student student)  {
        studentArrayList.add(student);
        try {
            studentFile.writeFile(studentArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //sửa thông tin sinh viên theo code
    public void editStudent (String code) {
        Student student = searchStudentByCode(code);
        if(student != null){
            inputStudent(student);
        } else {
            System.out.println("Không tìm thấy sinh viên");
        }
        try {
            studentFile.writeFile(studentArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //xóa thông tin sinh viên theo code
    public void removeStudent(String code) {
        Student student = searchStudentByCode(code);
        if(student != null){
            for (int i = 0; i < studentArrayList.size(); i++) {
                if(studentArrayList.get(i).equals(student)){
                    studentArrayList.remove(i);
                }
            }
        } else {
            System.out.println("Không tìm thấy sinh viên");
        }
        try {
            studentFile.writeFile(studentArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //showAll danh sách sinh viên
    public void showAllStudent(){
        for (Student student: studentArrayList) {
            System.out.println(student);
        }
    }

    //tìm kiếm sinh viên theo code ( trả về object)
    public Student searchStudentByCode(String code){
        Student student = null;
        for (Student value : studentArrayList) {
            if (value.getStudentCode().equalsIgnoreCase(code)) {
                student = value;
                break;
            }
        }
        return student;
    }

    //tìm kiếm sinh viên theo code ( trả về index)
    public int searchIndexByCode(String code){
        for (Student student : studentArrayList) {
            if (student.getStudentCode().equalsIgnoreCase(code)) {
                return studentArrayList.indexOf(student);
            }
        }
        return -1;
    }

    //nhập lại student
    private void inputStudent(Student student) {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        String nameStudent,yearOfBirth,class1;
        double balance;
        System.out.println("Nhập lại thông tin sinh viên");
        System.out.print("Nhập tên sinh viên: ");
        nameStudent = sc.nextLine();
        System.out.print("Nhập năm sinh: ");
        yearOfBirth = sc.nextLine();
        System.out.print("Nhập tên lớp: ");
        class1 = sc.nextLine();
        System.out.print("Nhập số tiền hiện có: ");
        balance = sc1.nextDouble();
        student.setName(nameStudent);
        student.setYearOfBirth(yearOfBirth);
        student.setClass1(class1);
        student.setBalance(balance);
    }
}
