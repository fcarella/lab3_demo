package lecture5.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lecture5.jpa.Editable;
import lecture5.jpa.SaleableItem;

/**
 * @author fcarella
 */
@Entity
public abstract class Publication extends Editable implements SaleableItem, Serializable {
    public Publication() {
    }

    public Publication(String title, int copies, double price) {
        this.title = title;
        this.copies = copies;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String title;
    @Basic
    private int copies;

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    @Basic
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}