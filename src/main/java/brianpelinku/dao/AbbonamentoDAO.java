package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;

import brianpelinku.entities.PuntoEmissione;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.*;

import java.time.LocalDate;

import java.util.List;
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

    public List<Abbonamento> findAbbonamentiPerPuntoEmissione(PuntoEmissione puntoEmissione) {
        TypedQuery<Abbonamento> query = em.createQuery("SELECT a FROM Abbonamento a WHERE a.idPuntoEmissione = :puntoEmissione ", Abbonamento.class);
        query.setParameter("puntoEmissione", puntoEmissione);
        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono Abbonamenti in questo punto di Emissione!");
        }
        return query.getResultList();
    }

    public List<Abbonamento> findAbbonamentiNelTempo(LocalDate data1, LocalDate data2 ) {
        TypedQuery<Abbonamento> query = em.createQuery("SELECT a FROM Abbonamento a WHERE a.dataEmissione >= :data1 AND a.dataEmissione <= :data2 ", Abbonamento.class);
        query.setParameter("data1", data1);
        query.setParameter("data2", data2);

        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono Abbonamenti in questo Periodo di tempo!");
        }
        return query.getResultList();
    }
    public void findValiditaAbbonamento(String tesseraId) {

        try {
            TypedQuery<Abbonamento> query = em.createQuery("SELECT a FROM Abbonamento a WHERE a.idTessera.id = :tesseraId", Abbonamento.class);
            query.setParameter("tesseraId", UUID.fromString(tesseraId));
            Abbonamento abbonamento = query.getSingleResult();
            System.out.println("La validita del Abbonamento " + abbonamento.getId() + " è " + abbonamento.getDurata());

        } catch (IllegalArgumentException e) {
            // Gestisce errori relativi ai parametri della query, come un formato UUID non valido
            System.err.println("Errore nei parametri della query: " + e.getMessage());
        } catch (NoResultException e) {
            // Gestisce il caso in cui non ci sono risultati per la query
            System.err.println("Nessun abbonamento trovato per l'ID tessera: " + tesseraId);
        } catch (PersistenceException e) {
            // Gestisce errori generali di persistenza come problemi con la connessione al database
            System.err.println("Errore durante l'accesso al database: " + e.getMessage());
        } catch (Exception e) {
            // Gestisce eventuali altre eccezioni
            System.err.println("Errore imprevisto: " + e.getMessage());
        }

    }
}
