package brianpelinku.dao;


import brianpelinku.entities.Timbratura;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;


import java.time.LocalDateTime;
import java.util.ArrayList;
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
        List<Timbratura> timbrature = new ArrayList<>();
        try {
            TypedQuery<Timbratura> query = em.createQuery(
                    "SELECT a FROM Timbratura a WHERE a.dataTimbro >= :data1 AND a.dataTimbro <= :data2",
                    Timbratura.class);
            query.setParameter("data1", data1);
            query.setParameter("data2", data2);

            timbrature = query.getResultList();

            if (timbrature.isEmpty()) {
                System.out.println("Non ci sono timbrature in questo periodo di tempo!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: I parametri della data forniti non sono validi.");
        } catch (PersistenceException e) {
            System.err.println("Errore di persistenza: Si è verificato un problema durante l'interrogazione del database.");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }

        return timbrature;
    }


    public List<Timbratura> findTimbratureDiUnMezzo(String mezzoId) {
        List<Timbratura> timbrature = new ArrayList<>();

        try {
            // Creazione della query per trovare le timbrature associate al mezzo specificato
            TypedQuery<Timbratura> query = em.createQuery(
                    "SELECT a FROM Timbratura a WHERE a.idMezzo.id = :mezzoId",
                    Timbratura.class);
            query.setParameter("mezzoId", UUID.fromString(mezzoId));

            // Ottenere i risultati dalla query
            timbrature = query.getResultList();

            // Verifica se la lista è vuota e stampa il messaggio appropriato
            if (timbrature.isEmpty()) {
                System.out.println("Non ci sono timbrature per questo mezzo!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: L'ID del mezzo fornito non è valido. Assicurati che sia un UUID corretto.");
        } catch (PersistenceException e) {
            System.err.println("Errore di persistenza: Si è verificato un problema durante l'interrogazione del database.");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }

        return timbrature;
    }
}
