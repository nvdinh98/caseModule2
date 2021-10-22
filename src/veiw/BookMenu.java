package veiw;

import control.BookManager;
import model.Book;
import storage.BookFile;


import java.io.IOException;
import java.util.Scanner;

public class BookMenu {
    private BookMenu() {
    }

    public static BookMenu getInstance() {
        return BookMenuHelper.INSTANCE;
    }

    private static class BookMenuHelper {
        private static final BookMenu INSTANCE = new BookMenu();
    }

    public void runBook() {
        Scanner number = new Scanner(System.in);
        BookManager bookManager = BookManager.getInstance();

        try {
            bookManager.setBookArrayList(BookFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int choice = -1;

        while (choice != 0) {
            System.out.println("--------Quản lý sách thư viện--------");
            System.out.println("1.Hiển thị Danh sách");
            System.out.println("2.Sửa thông tin sách");
            System.out.println("3.Xóa sách");
            System.out.println("4.Tìm kiếm theo mã sách");
            System.out.println("5.Thêm Sách");
            System.out.println("0. Quay lại");
            System.out.println("----------------図書館--------------");

            choice = number.nextInt();

            switch (choice) {
                case 1:
                    bookManager.showAllBook();
                    break;
                case 2:
                    editBookByCode(bookManager);
                    break;
                case 3:
                    removeBookByCode(bookManager);
                    break;
                case 4:
                    System.out.println(bookManager.searchBookByCode(inputCode()));
                    break;
                case 5:
                    inputBook(bookManager);
                    break;
                case 0:
            }
        }

    }

    //xóa sách
    private void removeBookByCode(BookManager managerBook) {
        managerBook.removeBook(inputCode());
    }

    //sửa sách theo code
    private void editBookByCode(BookManager managerBook) {
        managerBook.editStudent(inputCode(), addBook());
    }

    //thêm sách
    private void inputBook(BookManager managerBook) {
        managerBook.addBook(addBook());
    }

    public static Book addBook() {
        Scanner string = new Scanner(System.in);
        Scanner inputNumber = new Scanner(System.in);
        String bookCode, bookName;
        int quantity;
        System.out.print("Nhập mã sách: ");
        bookCode = string.nextLine();
        System.out.print("Nhập tên sách: ");
        bookName = string.nextLine();
        System.out.print("Nhập số lượng sách: ");
        quantity = inputNumber.nextInt();
        return new Book(bookCode, bookName, quantity);
    }

    //nhập code sách
    private String inputCode() {
        System.out.print("Nhập code sách: ");
        Scanner string = new Scanner(System.in);
        return string.nextLine();
    }

}