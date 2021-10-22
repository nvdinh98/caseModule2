package control;

import model.Book;
import storage.BookFile;

import java.io.IOException;
import java.util.ArrayList;

public class BookManager {
    ArrayList<Book> bookArrayList = new ArrayList<>();
    BookFile bookFile = BookFile.getInstance();
    private BookManager() {
    }

    private static class ManagerBookHelper{
        private static final BookManager INSTANCE = new BookManager();
    }
    public static BookManager getInstance() {
        return BookManager.ManagerBookHelper.INSTANCE;
    }




    public BookManager(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public static void setBookArrayList(ArrayList<Book> bookArrayList) {
        bookArrayList = bookArrayList;
    }

    ///thêm sách
    public void addBook(Book student){
        bookArrayList.add(student);
        try {
            bookFile.writeFile(bookArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //sửa thông tin sách theo code
    public void editStudent (String code, Book newBook) {
        Book book = searchBookByCode(code);
        if(book != null){
            for (int i = 0; i < bookArrayList.size(); i++) {
                if(bookArrayList.get(i).equals(book)){
                    bookArrayList.set(i,newBook);
                }
            }
        } else {
            System.out.println("Không tìm thấy sách");
        }
        try {
            bookFile.writeFile(bookArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //xóa sách theo code
    public void removeBook(String code) {
        Book book = searchBookByCode(code);
        if(book != null){
            for (int i = 0; i < bookArrayList.size(); i++) {
                if(bookArrayList.get(i).equals(book)){
                    bookArrayList.remove(i);
                }
            }
        } else {
            System.out.println("Không tìm thấy sách");
        }
        try {
            bookFile.writeFile(bookArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //showAll danh sách sách
    public void showAllBook(){
        for (Book book: bookArrayList) {
            System.out.println(book);
        }
    }


    //tìm kiếm sách theo code ( trả về object)
    public Book searchBookByCode(String code){
        for (Book book : bookArrayList) {
            if (book.getBookCode().equalsIgnoreCase(code)) {
                return book;
            }
        }
        return null;
    }

}