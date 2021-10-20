package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LibraryCard implements Serializable {
    private Student student;
    private Book book;
    private LocalDate borrowedDate;
    private int borrowedDays;
    private boolean status = true;


    public LibraryCard() {
    }

    public LibraryCard(Student student, Book book, LocalDate borrowedDate, int borrowedDays,boolean status) {
        this.student = student;
        this.book = book;
        this.borrowedDate = borrowedDate.plusDays(borrowedDays);
        this.borrowedDays = borrowedDays;
        this.status = status;
    }


    public LibraryCard(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public long getBorrowedDays() {
        return borrowedDays;
    }

    public void setBorrowedDays(int borrowedDays) {
        this.borrowedDays = borrowedDays;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return student +
                ", " +
                book +
                ", Ngày mượn: " + borrowedDate +
                ", Thời hạn mượn: " + borrowedDays + " ngày"+
                ", Trạng thái thẻ: " + status;

    }

    public String show(){
        return student +
                ", Trạng thái thẻ: " + status;
    }
}