package brianpelinku.dao;

import brianpelinku.ENUM.StatoDelMezzo;
import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.PuntoEmissione;
import brianpelinku.entities.Timbratura;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TimbraturaDAO {
    private final EntityManager em;

    public TimbraturaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Timbratura timbratura) {
        BigliettoDAO bd = new BigliettoDAO(em);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(timbratura);
        transaction.commit();

        if (!timbratura.getIdBiglietto().getTimbrato()) {
            bd.updateTimbraturaBiglietto(timbratura.getIdBiglietto().getId().toString(),true);
            System.out.println("Timbratura biglietto " + timbratura.getIdBiglietto().getId() + " cambiato in true");
        } else {
            //bd.updateTimbraturaBiglietto(timbratura.getIdBiglietto().getId().toString(),false);
            System.out.println("Il biglietto " + timbratura.getIdBiglietto().getId() + " è già stato timbrato!");

        }
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

    public List<Timbratura> findTimbratureNelTempo(LocalDateTime data1, LocalDateTime data2 ) {
        TypedQuery<Timbratura> query = em.createQuery("SELECT a FROM Timbratura a WHERE a.dataTimbro >= :data1 AND a.dataTimbro <= :data2 ", Timbratura.class);
        query.setParameter("data1", data1);
        query.setParameter("data2", data2);

        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono timbrature in questo Periodo di tempo!");
        }
        return query.getResultList();
    }
    public List<Timbratura> findTimbratureDiUnMezzo(String mezzoId) {
        TypedQuery<Timbratura> query = em.createQuery("SELECT a FROM Timbratura a WHERE a.idMezzo.id = :mezzoId ", Timbratura.class);
        query.setParameter("mezzoId", UUID.fromString(mezzoId));
        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono Timbratura su questo mezzo!");
        }
        return query.getResultList();
    }
}
