package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.Mezzo;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzo) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        transaction.commit();

        System.out.println("Il mezzo " + mezzo.getId() + " è stato salvato correttamente!");
    }

    public Mezzo findById(String mezzoId) {
        Mezzo found = em.find(Mezzo.class, UUID.fromString(mezzoId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(mezzoId);
        return found;
    }

    public void findByIdAndDelete(String mezzoId) {
        Mezzo found = this.findById(mezzoId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Il mezzo " + found.getId() + " è stato eliminato correttamente!");
    }
}
