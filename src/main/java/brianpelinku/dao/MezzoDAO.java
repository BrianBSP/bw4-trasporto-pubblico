package brianpelinku.dao;


import brianpelinku.ENUMS.StatoDelMezzo;
import brianpelinku.entities.Mezzo;
import brianpelinku.entities.StatoMezzo;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

import java.time.LocalDate;
import java.util.UUID;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzo) {
        StatoMezzo statoMezzo1 = new StatoMezzo(LocalDate.now(),mezzo.getStato(),mezzo);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        em.persist(statoMezzo1);
        transaction.commit();

        System.out.println("Il mezzo " + mezzo.getId() + " è stato salvato correttamente!");
    }

    public Mezzo findById(String mezzoId) {
        Mezzo found = em.find(Mezzo.class, UUID.fromString(mezzoId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(mezzoId);
        return found;
    }

    public void findByIdAndDelete(String mezzoId) {
        Mezzo found = this.findById(mezzoId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Il mezzo " + found.getId() + " è stato eliminato correttamente!");
    }

    public void updateStatoMezzo(String mezzoId, StatoDelMezzo statoDelMezzo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            em.createQuery("UPDATE Mezzo a SET a.stato = :statoDelMezzo WHERE a.id = :mezzoId")
                    .setParameter("mezzoId", UUID.fromString(mezzoId))
                    .setParameter("statoDelMezzo", statoDelMezzo)
                    .executeUpdate();

            transaction.commit();
            System.out.println("Modifica stato mezzo avvenuta con successo!");
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: L'ID del mezzo fornito non è valido. Assicurati che sia un UUID corretto.");
        } catch (PersistenceException e) {
            System.err.println("Errore di persistenza: Si è verificato un problema durante l'aggiornamento del database.");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }
    }
}
