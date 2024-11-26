package lecture5.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;


/**
 * @author fcarella
 */
@Entity
public class DiscMag extends Magazine {
    public DiscMag(int orderQty, boolean hasDisc) {
        super(orderQty);
        this.hasDisc = hasDisc;
    }

    public DiscMag(String title, int copies, double price, int orderQty, boolean hasDisc) {
        super(title, copies, price, orderQty);
        this.hasDisc = hasDisc;
    }

    public DiscMag(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    public DiscMag() {
    }

    @Basic
    private boolean hasDisc;

    public boolean isHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public void initialize() {
        super.initialize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void edit() {
        super.edit(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    

}