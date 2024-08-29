package brianpelinku.dao;

import brianpelinku.ENUMS.StatoDelMezzo;
import brianpelinku.entities.*;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.*;

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

        if (giroTratta.getIdMezzo().getStato() == StatoDelMezzo.SERVIZIO) {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(giroTratta);
            transaction.commit();

            System.out.println("Il giro " + giroTratta.getId() + " della tratta " +  giroTratta.getIdTratta().getNome() + " è stato salvato correttamente!");
        }else {
            System.err.println("Giro tratta non creato! Il Mezzo  " + giroTratta.getIdMezzo().getId() + " scelto per questo giro è in MANUTENZIONE!");
        }

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
        try {
            // Prova a creare la query e ad eseguirla
            TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idTratta.id = :trattaId", GiroTratta.class);
            query.setParameter("trattaId", UUID.fromString(trattaId));

            List<GiroTratta> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun risultato trovato per l'ID tratta: " + trattaId);
            } else {
                for (GiroTratta giroTratta : risultati) {
                    Duration durata = Duration.between(giroTratta.getTempoPartenza(), giroTratta.getTempoArrivo());
                    System.out.println("Durata per il giro tratta ID " + giroTratta.getId() + " del mezzo "+ giroTratta.getIdMezzo().getId() + " è di: " + durata.toMinutes() + " minuti");
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: L'ID tratta fornito non è valido. Assicurati che sia un UUID corretto.");
        } catch (NoResultException e) {
            System.err.println("Errore: Nessun risultato trovato per l'ID tratta specificato.");
        } catch (PersistenceException e) {
            System.err.println("Errore di persistenza: Si è verificato un problema durante l'interrogazione del database.");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }
    }
    public void findMediaTempoEffettivo(String trattaId , String mezzoId) {
        try {

            TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idMezzo.id = :mezzoId AND a.idTratta = :trattaId", GiroTratta.class);
            query.setParameter("mezzoId", UUID.fromString(mezzoId));
            query.setParameter("trattaId", UUID.fromString(trattaId));

            List<GiroTratta> risultati = query.getResultList();
            List<Long> tempiEffettivi = new ArrayList<>(risultati.size());

            if (risultati.isEmpty()) {
                System.out.println("Nessun risultato trovato per l'ID mezzo: " + mezzoId);
            } else {
                for (GiroTratta giroTratta : risultati) {
                    Duration durata = Duration.between(giroTratta.getTempoPartenza(), giroTratta.getTempoArrivo());
                    tempiEffettivi.add(durata.toMinutes());
                }

                long somma = tempiEffettivi.stream().mapToLong(Long::longValue).sum();
                double media = (double) somma / tempiEffettivi.size();
                System.out.println("La Media Tempo Effettivo del mezzo " + mezzoId + " sulla tratta " + trattaId +  " è " + media + " minuti.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: L'ID tratta fornito non è valido. Assicurati che sia un UUID corretto.");
        } catch (PersistenceException e) {
            System.err.println("Errore di persistenza: Si è verificato un problema durante l'interrogazione del database.");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }
    }



    public List<GiroTratta> findGiriMezzo(Mezzo mezzo) {
        List<GiroTratta> risultati = new ArrayList<>();

        try {
            TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idMezzo = :mezzo", GiroTratta.class);
            query.setParameter("mezzo", mezzo);

            risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Non ci sono giri per questo mezzo!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: Il parametro fornito non è valido.");
        } catch (PersistenceException e) {
            System.err.println("Errore di persistenza: Si è verificato un problema durante l'interrogazione del database.");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }

        return risultati;
    }

    public void findNumeroGiriMezzo(Mezzo mezzo) {
        try {
            TypedQuery<GiroTratta> query = em.createQuery("SELECT a FROM GiroTratta a WHERE a.idMezzo = :mezzo", GiroTratta.class);
            query.setParameter("mezzo", mezzo);

            List<GiroTratta> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Non ci sono stati giri per questo mezzo!");
            } else {
                System.out.println("Il numero di giri che il mezzo " + mezzo.getId() + " ha fatto sulla tratta " +
                        mezzo.getIdTratta().getPartenza() + " per " + mezzo.getIdTratta().getCapolinea() + " è " + risultati.size() + " giri.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: Il parametro fornito non è valido.");
        } catch (PersistenceException e) {
            System.err.println("Errore di persistenza: Si è verificato un problema durante l'interrogazione del database.");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }
    }


}
