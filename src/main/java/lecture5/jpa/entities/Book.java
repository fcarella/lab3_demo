package lecture5.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import lecture5.jpa.SaleableItem;


/**
 * @author fcarella
 */
@Entity
public class Book extends Publication {
    public Book(String author) {
        this.author = author;
    }

    public Book(String title, int copies, double price, String author) {
        super(title, copies, price);
        this.author = author;
    }

    @Basic
    private String author;

    public Book() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void sellItem(SaleableItem si) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                "} " + super.toString();
    }

    @Override
    public void initialize() {
        System.out.println("Enter author:");
        setAuthor(getInput("no author"));

    }

    @Override
    public void edit() {
        System.out.println("Enter author:");
        setAuthor(getInput(getAuthor()));
    }

}