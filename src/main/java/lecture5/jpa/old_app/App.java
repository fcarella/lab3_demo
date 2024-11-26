/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecture5.jpa.old_app;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lecture5.jpa.entities.Book;
import lecture5.jpa.entities.Magazine;
import lecture5.jpa.entities.Publication;
import lecture5.jpa.entities.Ticket;
import lecture5.jpa.controllers.BookJpaController;
import lecture5.jpa.controllers.MagazineJpaController;
import lecture5.jpa.controllers.PublicationJpaController;
import lecture5.jpa.controllers.TicketJpaController;

/**
 *
 * @author fcarella
 */
public class App {

    void run() {
        EntityManagerFactory emf = null;

        try {
            emf = Persistence.createEntityManagerFactory("DEFAULT_PU");
            Book book = new Book();
            book.setAuthor("Author");
            book.setPrice(29.99d);
            book.setTitle("Book Title");
            
            Ticket ticket=new Ticket();
            ticket.setDescription("Taylor Swift concert...");

            Magazine magazine = new Magazine();
            long millis = System.currentTimeMillis();
            magazine.setCurrIssue(new java.sql.Date(millis));
            magazine.setPrice(29.99d);
            magazine.setTitle("Magazine Title");
                        
            BookJpaController bookController=new BookJpaController(emf);
            MagazineJpaController magazineController=new MagazineJpaController(emf);
            PublicationJpaController publicationController=new PublicationJpaController(emf);
            TicketJpaController ticketController=new TicketJpaController(emf);
            
            bookController.create(book);
            magazineController.create(magazine);
            ticketController.create(ticket);
            
            // your database should have 2 tables, one for publications, another for tickets
            // confirm in debug and ide views
            
            System.out.println("----------------------------");
            System.out.println("List of Books");
            System.out.println("----------------------------");
            List<Book> books = bookController.findBookEntities();
            for(Book p:books){
                System.out.println(p);
            }
            
            System.out.println("----------------------------");
            System.out.println("List of Magazines");
            System.out.println("----------------------------");
            List<Magazine> magazines = magazineController.findMagazineEntities();
            for(Magazine p:magazines){
                System.out.println(p);
            }
            
            System.out.println("----------------------------");
            System.out.println("List of Publications");
            System.out.println("----------------------------");
            List<Publication> publications = publicationController.findPublicationEntities();
            for(Publication p:publications){
                System.out.println(p);
            }
            
            // NOTE use the debugger to confirm entity and datavse changes
            // edit a book
            String originalAuthor=book.getAuthor();
            // find a book first
            Book foundBook=bookController.findBook(book.getId());
            foundBook.setAuthor("This author has been edited");
            
            bookController.edit(foundBook);
            
            bookController.destroy(book.getId());
            
            
            
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (emf != null) {
                emf.close();
            }
//            if(em!=null)
//                em.close();
        }
    }

}
