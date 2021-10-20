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
        return BookMenu.BookMenuWithManagerBookHelper.INSTANCE;
    }

    private static class BookMenuWithManagerBookHelper{
        private static final BookMenu INSTANCE = new BookMenu();
    }
    public void runBook(){
        Scanner number = new Scanner(System.in);
        BookManager managerBook = BookManager.getInstance();

        try {
            managerBook.setBookArrayList(BookFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int choice = -1;

        while (choice != 0){
            System.out.println("--------Quản lý sách thư viện--------");
            System.out.println("1. Thêm sách");
            System.out.println("2. Sửa thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm theo mã sách");
            System.out.println("5. Dách sách Sách");
            System.out.println("0. Quay lại");

            choice = number.nextInt();

            switch (choice){
                case 1:
                    inputBook(managerBook);
                    break;
                case 2:
                    editBookByCode(managerBook);
                    break;
                case 3:
                    removeBookByCode(managerBook);
                    break;
                case 4:
                    System.out.println(managerBook.searchBookByCode(inputCode()));
                    break;
                case 5:
                    managerBook.showAllBook();
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
        managerBook.editStudent(inputCode(),addBook());
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
        return new Book(bookCode, bookName,quantity);
    }

    //nhập code sách
    private String inputCode() {
        System.out.print("Nhập code sách: ");
        Scanner string = new Scanner(System.in);
        return string.nextLine();
    }

}