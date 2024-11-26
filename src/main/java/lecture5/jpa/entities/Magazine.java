package lecture5.jpa.entities;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import lecture5.jpa.SaleableItem;

/**
 * @author fcarella
 */
@Entity
public class Magazine extends Publication {
    public Magazine(int orderQty) {
        this.orderQty = orderQty;
    }

    public Magazine(String title, int copies, double price, int orderQty) {
        super(title, copies, price);
        this.orderQty = orderQty;
    }

    public Magazine() {
    }

    @Basic
    private int orderQty;
    @Basic
    private Date currIssue;

    @Override
    public String toString() {
        return "Magazine{" +
                "orderQty=" + orderQty +
                ", currIssue=" + currIssue +
                "} " + super.toString();
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrIssue() {
        return currIssue;
    }

    public void setCurrIssue(Date currIssue) {
        this.currIssue = currIssue;
    }

    @Override
    public void sellItem(SaleableItem si) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize() {
        System.out.println("Enter author:");
        setTitle(getInput("no title"));
    }

    @Override
    public void edit() {
        System.out.println("Enter author:");
        setTitle(getInput(getTitle()));
    }

}