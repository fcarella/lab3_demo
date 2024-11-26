
  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecture5.jpa;

  import lecture5.jpa.controllers.*;
  import lecture5.jpa.entities.*;

  import javax.persistence.EntityManagerFactory;
  import javax.persistence.Persistence;
  import java.util.ArrayList;
  import java.util.Date;
  import java.util.InputMismatchException;
  import java.util.Scanner;

public class App {

    private static final int MAX_ITEMS = 100;
    private static final String menu = "\n***********************\n"
            + " 1. Add Items\n"
            + " 2. Edit Items\n"
            + " 3. Delete Items\n"
            + " 4. Sell item(s)\n"
            + " 5. List items\n"
            + "99. Quit\n"
            + "***********************\n"
            + "Enter choice: ";
    //private final SaleableItem[] saleableItems = new SaleableItem[MAX_ITEMS];
    private final ArrayList<SaleableItem> saleableItems=new ArrayList<>();
    private int currentItem = 0;
    private Scanner input = null;
    private EntityManagerFactory emf = null;

    public App() {
        try {
            emf = Persistence.createEntityManagerFactory("DEFAULT_PU");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void run() {

        populate();

        boolean done = false;
        while (!done) {
            try {
                input = new Scanner(System.in);
                System.out.println(menu);
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Add an item\n");
                        addItem();
                        break;
                    case 2:
                        System.out.println("Edit an item\n");
                        editItem();
                        break;
                    case 3:
                        System.out.println("Delete an item\n");
                        deleteItem();
                        break;
                    case 4:
                        System.out.println("Sell a car\n");
                        sellItem();
                        break;
                    case 5:
                        System.out.println("All Items\n-----------");
                        list();
                        break;
                    case 99:
                        done = true;
                        break;
                    default:
                        System.out.println("Wrong entry, try again...");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong entry, try again...");
            } catch (Exception e) {
                System.out.println("Unknown Exception : " + e.getMessage());
            }
        }
    }

    private void populate() {
        Book b=new Book("String title", 10, 19.99, "String author" );
        Magazine m=new Magazine("String title", 15, 29.99, 25);
        DiscMag dm=new DiscMag("String title", 20, 39.99, 30, true);
        Ticket t=new Ticket("This is a ticket to a really fun event");

        addItem(b);
        addItem(m);
        addItem(dm);
        addItem(t);
    }

    private void addItem() {
        String menu
                = """
                  1. Add Book
                  2. Add Magazine
                  3. Add DiscMag
                  4. Add Ticket
                  99. Exit
                  """;
        boolean done=false;

        while (!done) {
            System.out.println(menu);

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    Book b = new Book();
                    b.initialize();
                    BookJpaController bookController=new BookJpaController(emf);
                    bookController.create(b);
                    addItem(b);
                    break;
                case 2:
                    Magazine m = new Magazine();
                    m.initialize();
                    MagazineJpaController magazineController=new MagazineJpaController(emf);
                    magazineController.create(m);
                    saleableItems.add(m);//[currentItem++] = m;
                    break;
                case 3:
                    DiscMag dm = new DiscMag();
                    dm.initialize();
                    saleableItems.add(dm);//[currentItem++] = m;
                    break;
                case 4:
                    Ticket t = new Ticket();
                    t.initialize();
                    saleableItems.add(t);//[currentItem++] = m;
                    break;
                case 99:
                    done=true;
            }
        }
    }

    public void addItem(SaleableItem s) {
        saleableItems.add(s);

    }

    public boolean findItem(SaleableItem item){
        return(saleableItems.contains(item));
    }

    public void editItem() {
        try {
//            String menu
//                    = """
//                    1. Edit Book
//                    2. Edit Magazine
//                    3. Edit DiscMag
//                    4. Edit Ticket
//                    99. Exit
//                    """;
//            boolean done = false;

            while (true) {
                list();
                SaleableItem b=null;
                int choice;
                System.out.println("Choose an item to edit or 99 to exit:");
                input = new Scanner(System.in);
                choice = input.nextInt();
                if(choice==99)
                    break;
                choice--;
                if (choice > 0 && choice < saleableItems.size())
                    b = saleableItems.get(choice);
                assert b != null;
                editItem((Editable) b);
            }
        }catch (Exception e){
            System.out.println("Wrong choice, try again: \n"+e.getMessage());
        }
    }
    public void editItem(Editable s) {
        s.edit();
        PublicationJpaController publicationController=new PublicationJpaController(emf);
        try {
            publicationController.edit((Publication) s);
        }catch(Exception e){
            System.out.println("Problem with Publication...");
        }


    }

    public SaleableItem getItem(SaleableItem s){
        for(SaleableItem s2:saleableItems)
            if(s2.equals(s)){return s;}
        return null;
    }
    private void deleteItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void sellItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void list() {
        int i=0;
            for(SaleableItem s:saleableItems)
                System.out.println((++i)+". "+s+"\n");
    }


}
