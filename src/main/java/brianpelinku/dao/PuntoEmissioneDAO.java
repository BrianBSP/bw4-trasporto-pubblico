package brianpelinku.dao;

import brianpelinku.entities.PuntoEmissione;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class PuntoEmissioneDAO {

    private final EntityManager em;

    public PuntoEmissioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(PuntoEmissione punto) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(punto);
        transaction.commit();

        System.out.println("Il Punto Emissione " + punto.getNome() + " è stato salvato correttamente!");
    }

    public PuntoEmissione findById(String puntoId) {
        PuntoEmissione found = em.find(PuntoEmissione.class, UUID.fromString(puntoId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(puntoId);
        return found;
    }

    public void findByIdAndDelete(String puntoId) {
        PuntoEmissione found = this.findById(puntoId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Il Punto Emissione " + found.getNome() + " è stato eliminato correttamente!");
    }
}
