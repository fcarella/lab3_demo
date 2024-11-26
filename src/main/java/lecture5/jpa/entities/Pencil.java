package lecture5.jpa.entities;

import lecture5.jpa.Editable;
import lecture5.jpa.SaleableItem;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author fcarella
 */
@Entity
public class Pencil extends Editable implements SaleableItem, Serializable {

    public Pencil() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String type;
    @Basic
    private String color;
    @Basic
    private double price;
    @Basic
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Pencil(String type, String color, double price) {
        this.type = type;
        this.color = color;
        this.price = price;
    }

    @Override
    public void sellItem(SaleableItem si) {
        quantity--;
    }

    @Override
    public void edit() {

    }

    @Override
    public void initialize() {

    }
}