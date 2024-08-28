package brianpelinku.dao;

import brianpelinku.ENUM.StatoDelMezzo;
import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.Biglietto;
import brianpelinku.entities.PuntoEmissione;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.*;

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
        List<Biglietto> biglietti = null;

        try {
            TypedQuery<Biglietto> query = em.createQuery(
                    "SELECT a FROM Biglietto a WHERE a.idPuntoEmissione = :puntoEmissione",
                    Biglietto.class
            );
            query.setParameter("puntoEmissione", puntoEmissione);
            biglietti = query.getResultList();

            if (biglietti.isEmpty()) {
                System.out.println("Non ci sono Biglietti in questo punto di Emissione!");
            }
        } catch (IllegalArgumentException e) {
            // Gestione di errori relativi ai parametri della query
            System.err.println("Errore nei parametri della query: " + e.getMessage());
        } catch (NoResultException e) {
            // Gestisce il caso in cui non ci sono risultati per la query
            System.err.println("Nessun risultato trovato per il punto di emissione specificato.");
        } catch (PersistenceException e) {
            // Gestione di errori generali di persistenza
            System.err.println("Errore durante l'accesso al database: " + e.getMessage());
        } catch (Exception e) {
            // Gestione di eventuali altre eccezioni non previste
            System.err.println("Errore imprevisto: " + e.getMessage());
        }

        // Ritorna la lista dei biglietti, anche se potrebbe essere null o vuota
        return biglietti;
    }

    public List<Biglietto> findBigliettiNelTempo(LocalDate data1, LocalDate data2 ) {
        List<Biglietto> biglietti = null;

        try {
            TypedQuery<Biglietto> query = em.createQuery(
                    "SELECT a FROM Biglietto a WHERE a.dataEmissione >= :data1 AND a.dataEmissione <= :data2",
                    Biglietto.class
            );
            query.setParameter("data1", data1);
            query.setParameter("data2", data2);
            biglietti = query.getResultList();

            if (biglietti.isEmpty()) {
                System.out.println("Non ci sono Biglietti in questo periodo di tempo!");
            }

        } catch (IllegalArgumentException e) {
            // Gestione di errori relativi ai parametri della query
            System.err.println("Errore nei parametri della query: " + e.getMessage());
        } catch (PersistenceException e) {
            // Gestione di errori generali di persistenza
            System.err.println("Errore durante l'accesso al database: " + e.getMessage());
        } catch (Exception e) {
            // Gestione di eventuali altre eccezioni non previste
            System.err.println("Errore imprevisto: " + e.getMessage());
        }
        // Ritorna la lista dei biglietti, anche se potrebbe essere null o vuota
        return biglietti;
    }

    public void updateTimbraturaBiglietto(String bigliettoId, boolean bool) {
    try {
    EntityTransaction transaction = em.getTransaction();

    transaction.begin();

    em.createQuery("UPDATE Biglietto a SET a.timbrato = :bool WHERE a.id = :bigliettoId")
            .setParameter("bigliettoId", UUID.fromString(bigliettoId)).setParameter("bool", bool).executeUpdate();;

    transaction.commit();

    System.out.println("Timbratura biglietto avvenuta con successo!");
    }
    catch  (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }

    }
}
