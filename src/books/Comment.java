package src.books;

import java.io.Serializable;

import src.users.User;

/**
 * Αντικείμενο που αναπαριστά ένα σχόλιο για ένα βιβλίο.
 */
public class Comment implements Serializable {
    private String comment; // Το περιεχόμενο του σχολίου
    private User user; // Ο χρήστης που δημιούργησε το σχόλιο

    /**
     * Κατασκευαστής του σχολίου.
     * 
     * @param comment Το περιεχόμενο του σχολίου
     */
    public Comment(String comment) {
        this.comment = comment;
    }

    /**
     * Μέθοδος για την επεξεργασία του σχολίου.
     * 
     * @param comment Το νέο περιεχόμενο του σχολίου
     */
    public void editComment(String comment) {
        this.comment = comment;
    }

    /**
     * Μέθοδος για την επιστροφή του χρήστη που δημιούργησε το σχόλιο.
     * 
     * @return Ο χρήστης που δημιούργησε το σχόλιο
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Μέθοδος για την επιστροφή του περιεχομένου του σχολίου.
     * 
     * @return Το περιεχόμενο του σχολίου
     */
    public String getComment() {
        return this.comment;
    }
}
