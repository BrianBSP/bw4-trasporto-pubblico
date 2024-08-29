package brianpelinku.dao;

import brianpelinku.entities.Utente;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UtenteDAO {

    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();

        System.out.println("L'utente " + utente.getCognome() + " è stato salvato correttamente!");
    }

    public Utente findById(String utenteId) {
        Utente found = em.find(Utente.class, UUID.fromString(utenteId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(utenteId);
        return found;
    }
    public void findByIdAndDelete(String utenteId) {
        Utente found = this.findById(utenteId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("L'utente " + found.getCognome() + " è stato eliminato correttamente!");

    }
}
