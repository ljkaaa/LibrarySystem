package vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    int barCode;
    String name;
    String author;
    String publisher;
    Date publishDate;
    int count;

    public Book() {
    }

    public Book(int barCode, String name, String author, String publisher, Date publishDate, int count) {
        this.barCode = barCode;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.count = count;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        return ""+barCode+"\t"+name+"\t"+author+"\t"+publisher+"\t"+d.format(publishDate)+"\t"+count;
    }
}
