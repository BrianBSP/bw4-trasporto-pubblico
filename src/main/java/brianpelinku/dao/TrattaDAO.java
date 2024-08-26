package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.Tratta;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class TrattaDAO {
    private final EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta tratta) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tratta);
        transaction.commit();

        System.out.println("La Tratta " + tratta.getNome() + " è stata salvata correttamente!");
    }

    public Tratta findById(String trattaId) {
        Tratta found = em.find(Tratta.class, UUID.fromString(trattaId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(trattaId);
        return found;
    }

    public void findByIdAndDelete(String trattaId) {
        Tratta found = this.findById(trattaId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La Tratta " + found.getNome() + " è stato eliminato correttamente!");
    }
}
