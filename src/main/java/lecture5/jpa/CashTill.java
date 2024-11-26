/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecture5.jpa;

/**
 *
 * @author fcarella
 */
public class CashTill {

    private double runningTotal;

    CashTill() {
        runningTotal = 0;
    }

    public void sellItem(SaleableItem saleableItem) {
        runningTotal = runningTotal + saleableItem.getPrice();
        saleableItem.sellItem(saleableItem);
        System.out.println("Sold " + saleableItem + " @ "
                + saleableItem.getPrice() + "\nSubtotal = "
                + runningTotal);
    }

    public void showTotal() {
        System.out.println("GRAND TOTAL: " + runningTotal);
    }
}
