package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.StatoMezzo;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class StatoMezzoDAO {
    private final EntityManager em;

    public StatoMezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(StatoMezzo stato) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(stato);
        transaction.commit();

        System.out.println("Lo stato " + stato.getStatoMezzo() + " è stato salvato correttamente!");
    }

    public StatoMezzo findById(String statoId) {
        StatoMezzo found = em.find(StatoMezzo.class, UUID.fromString(statoId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(statoId);
        return found;
    }

    public void findByIdAndDelete(String statoId) {
        StatoMezzo found = this.findById(statoId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Lo stato " + found.getStatoMezzo() + " è stato eliminato correttamente!");
    }
}
