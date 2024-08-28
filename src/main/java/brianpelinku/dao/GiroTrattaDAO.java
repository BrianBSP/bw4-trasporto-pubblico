package brianpelinku.dao;

import brianpelinku.entities.Abbonamento;
import brianpelinku.entities.GiroTratta;
import brianpelinku.entities.Mezzo;
import brianpelinku.entities.StatoMezzo;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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
                Duration durata = Duration.between(giroTratta.getTempoPartenza(), giroTratta.getTempoArrivo());
                System.out.println("Durata per il giro tratta ID " + giroTratta.getId() + ": " + durata.toMinutes() + " minuti");
            }
        }
    }
    public void findMediaTempoEffettivo(String trattaId) {

        TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idTratta.id = :trattaId  ", GiroTratta.class);
        query.setParameter("trattaId", UUID.fromString(trattaId));

        List<GiroTratta> risultati = query.getResultList();
        List<Long> tempiEffettivi = new ArrayList<>(risultati.size());

        if (risultati.isEmpty()) {
            System.out.println("Nessun risultato trovato per l'ID tratta: " + trattaId);
        } else {
            for (GiroTratta giroTratta : risultati) {
                Duration durata = Duration.between(giroTratta.getTempoPartenza(), giroTratta.getTempoArrivo());
                tempiEffettivi.add(durata.toMinutes());
            }
            int somma = tempiEffettivi.stream().mapToInt(Long::intValue).sum();
            System.out.println("La Media Tempo Effettivo della tratta " + trattaId + " è " + somma/tempiEffettivi.size() + " minuti" );

        }
    }



    public List<GiroTratta> findGiriMezzo(Mezzo mezzo) {
        TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idMezzo = :mezzo ", GiroTratta.class);
        query.setParameter("mezzo", mezzo);

        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono giri per questo mezzo!");
        }
        return query.getResultList();
    }

    public void findNumeroGiriMezzo(Mezzo mezzo) {
        TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idMezzo = :mezzo ", GiroTratta.class);
        query.setParameter("mezzo", mezzo);

        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono stati per questo mezzo!");
        }
        System.out.println("Il numero di giri che il mezzo " + mezzo.getId() + " ha fatto sulla tratta " + mezzo.getIdTratta().getPartenza() + " per " + mezzo.getIdTratta().getCapolinea() + " è " + query.getResultList().size() + " giri"); ;
    }


}
