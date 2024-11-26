/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lecture5.jpa;

/**
 *
 * @author fcarella
 */
public interface SaleableItem {
    public double getPrice();
    public void sellItem(SaleableItem si);    
}
