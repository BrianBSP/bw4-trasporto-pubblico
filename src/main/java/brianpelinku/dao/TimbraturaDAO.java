package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.Timbratura;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class TimbraturaDAO {
    private final EntityManager em;

    public TimbraturaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Timbratura timbratura) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(timbratura);
        transaction.commit();

        System.out.println("La timbratura " + timbratura.getId() + " è stata salvata correttamente!");
    }

    public Timbratura findById(String timbraturaId) {
        Timbratura found = em.find(Timbratura.class, UUID.fromString(timbraturaId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(timbraturaId);
        return found;
    }

    public void findByIdAndDelete(String timbraturaId) {
        Timbratura found = this.findById(timbraturaId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La timbratura " + found.getId() + " è stata eliminata correttamente!");
    }
}
