package model;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookCode;
    private String bookName;
    private int quantity;

    public Book() {
    }

    public Book(String bookCode, String bookName, int quantity) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.quantity = quantity;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Mã sách: " + bookCode +
                ", Tên sách: " + bookName +
                ", Số lượng: " + quantity
                ;
    }
}