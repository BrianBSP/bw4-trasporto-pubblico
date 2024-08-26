package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.GiroTratta;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class GiroTrattaDAO {

    private final EntityManager em;

    public GiroTrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(GiroTratta giroTratta) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(giroTratta);
        transaction.commit();

        System.out.println("Il giro " + giroTratta.getId() + " della tratta " +  giroTratta.getIdTratta().getNome() + " è stato salvato correttamente!");
    }

    public GiroTratta findById(String giroTrattaId) {
        GiroTratta found = em.find(GiroTratta.class, UUID.fromString(giroTrattaId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(giroTrattaId);
        return found;
    }

    public void findByIdAndDelete(String giroTrattaId) {
        GiroTratta found = this.findById(giroTrattaId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Il giro " + found.getId() + " della tratta " +  found.getIdTratta().getNome() + " è stato eliminato correttamente!");

    }
}
