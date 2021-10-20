package storage;


import model.Book;

import java.io.*;
import java.util.ArrayList;

public class BookFile {
    private static BookFile bookFile;

    private BookFile() {
    }
    //Singleton
    public static BookFile getInstance(){
        if(bookFile == null){
            bookFile = new BookFile();
        }
        return bookFile;
    }

    public ArrayList<Book> readFile() throws IOException, ClassNotFoundException {
        File file = new File("bookList.dat");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            ArrayList<Book> list = (ArrayList<Book>)object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(ArrayList<Book> students) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("bookList.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(students);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}