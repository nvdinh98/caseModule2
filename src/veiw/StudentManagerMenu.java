package veiw;

import control.BookManager;
import control.LibraryCardManager;
import control.StudentManager;
import storage.BookFile;
import storage.LibraryCardFile;
import storage.StudentFile;

import java.io.IOException;
import java.util.Scanner;

 public class StudentManagerMenu {
    public StudentManagerMenu() {
    }

    public static StudentManagerMenu getInstance() {
        return StudentManagerMenu.MenuManagerStudentHelper.INSTANCE;
    }

    private static class MenuManagerStudentHelper{
        private static final StudentManagerMenu INSTANCE = new StudentManagerMenu();

    }

    public void runMenuStudent() {
        LibraryCardMenu libraryCardMenu = LibraryCardMenu.getInstance();
        BookManager bookManager = BookManager.getInstance();
        LibraryCardManager libraryCardManager = LibraryCardManager.getInstance();
        StudentManager studentManager = StudentManager.getInstance();
        //đọc file học sinh
        try {
            StudentManager.setStudentArrayList(StudentFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner number = new Scanner(System.in);
        //Đọc file book
        try {
            BookManager.setBookArrayList(BookFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Đọc thẻ thư viện
        try {
            libraryCardManager.setLibraryCardArrayList(LibraryCardFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int choice = -1;

        while (choice != 0){
            System.out.println("--------Trang trủ--------");
            System.out.println("1. Mượn Sách");
            System.out.println("2. Trả sách");
            System.out.println("3. Danh sách sách trong thư viện");
            System.out.println("0. Quay lại");

            choice = number.nextInt();
            switch (choice){
                case 1:
                    libraryCardMenu.borrowBooks();
                    break;
                case 2:
                    libraryCardMenu.giveBookBack();
                    break;
                case 3:
                    bookManager.showAllBook();
                    break;
                case 0:
            }
        }

    }
}
