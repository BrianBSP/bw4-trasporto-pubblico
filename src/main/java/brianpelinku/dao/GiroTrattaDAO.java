package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.GiroTratta;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.Duration;
import java.util.List;
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
    public void findTempoEffettivo(String trattaId) {

        TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idTratta.id = :trattaId  ", GiroTratta.class);
        query.setParameter("trattaId", UUID.fromString(trattaId));

        List<GiroTratta> risultati = query.getResultList();

        if (risultati.isEmpty()) {
            System.out.println("Nessun risultato trovato per l'ID tratta: " + trattaId);
        } else {
            for (GiroTratta giroTratta : risultati) {
                Duration durata = Duration.between(giroTratta.getTempoArrivo(), giroTratta.getTempoPartenza());
                System.out.println("Durata per il giro tratta ID " + giroTratta.getId() + ": " + durata);
            }
        }
    }
}
