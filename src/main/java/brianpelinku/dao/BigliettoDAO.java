package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.Biglietto;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class BigliettoDAO {
    private final EntityManager em;

    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Biglietto biglietto) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();

        System.out.println("Il Biglietto " + biglietto.getId() + " è stato salvato correttamente!");
    }

    public Biglietto findById(String bigliettoId) {
        Biglietto found = em.find(Biglietto.class, UUID.fromString(bigliettoId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(bigliettoId);
        return found;
    }

    public void findByIdAndDelete(String bigliettoId) {
        Biglietto found = this.findById(bigliettoId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Il Biglietto " + found.getId() + " è stato eliminato correttamente!");
    }
}
