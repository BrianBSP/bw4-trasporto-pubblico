package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;

import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class AbbonamentoDAO {

    private final EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Abbonamento abbonamento) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(abbonamento);
        transaction.commit();

        System.out.println("L'abbonamento " + abbonamento.getId() + " è stato salvato correttamente!");
    }

    public Abbonamento findById(String abbonamentoId) {
        Abbonamento found = em.find(Abbonamento.class, UUID.fromString(abbonamentoId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(abbonamentoId);
        return found;
    }

    public void findByIdAndDelete(String abbonamentoId) {
        Abbonamento found = this.findById(abbonamentoId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("L'abbonamento " + found.getId() + " è stato eliminato correttamente!");
    }
}
