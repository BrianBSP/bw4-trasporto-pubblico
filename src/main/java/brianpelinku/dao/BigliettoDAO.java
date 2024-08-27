package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.Biglietto;
import brianpelinku.entities.PuntoEmissione;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
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


    public List<Biglietto> findBigliettoPerPuntoEmissione(PuntoEmissione puntoEmissione) {
        TypedQuery<Biglietto> query = em.createQuery("SELECT a FROM Biglietto a WHERE a.idPuntoEmissione = :puntoEmissione ", Biglietto.class);
        query.setParameter("puntoEmissione", puntoEmissione);
        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono Abbonamenti in questo punto di Emissione!");
        }
        return query.getResultList();
    }

    public List<Biglietto> findBigliettiNelTempo(LocalDate data1, LocalDate data2 ) {
        TypedQuery<Biglietto> query = em.createQuery("SELECT a FROM Biglietto a WHERE a.dataEmissione >= :data1 AND a.dataEmissione <= :data2 ", Biglietto.class);
        query.setParameter("data1", data1);
        query.setParameter("data2", data2);

        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono Biglietti in questo Periodo di tempo!");
        }
        return query.getResultList();
    }
}
