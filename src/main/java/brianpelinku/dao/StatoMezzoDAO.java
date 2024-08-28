package brianpelinku.dao;

import brianpelinku.ENUMS.StatoDelMezzo;

import brianpelinku.entities.Mezzo;
import brianpelinku.entities.StatoMezzo;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;


import java.util.List;
import java.util.UUID;

public class StatoMezzoDAO {
    private final EntityManager em;

    public StatoMezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(StatoMezzo stato) {
        MezzoDAO md = new MezzoDAO(em);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(stato);
        transaction.commit();
        if (stato.getStatoMezzo() == StatoDelMezzo.MANUTENZIONE) {
            md.updateStatoMezzo(stato.getMezzo().getId().toString(),StatoDelMezzo.MANUTENZIONE);
            System.out.println("Stato del mezzo " + stato.getMezzo().getId() + " cambiato in MANUTENZIONE");
        } else {
            md.updateStatoMezzo(stato.getMezzo().getId().toString(),StatoDelMezzo.SERVIZIO);
            System.out.println("Stato del mezzo " + stato.getMezzo().getId() + " cambiato in SERVIZIO");
        }
        System.out.println("Lo stato " + stato.getStatoMezzo() + " è stato salvato correttamente!");
    }

    public StatoMezzo findById(String statoId) {
        StatoMezzo found = em.find(StatoMezzo.class, UUID.fromString(statoId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(statoId);
        return found;
    }

    public void findByIdAndDelete(String statoId) {
        StatoMezzo found = this.findById(statoId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Lo stato " + found.getStatoMezzo() + " è stato eliminato correttamente!");
    }

    public List<StatoMezzo> findStatiMezzo(Mezzo mezzo) {
        TypedQuery<StatoMezzo> query = em.createQuery("SELECT a FROM StatoMezzo a WHERE a.mezzo = :mezzo ", StatoMezzo.class);
        query.setParameter("mezzo", mezzo);

        if (query.getResultList().isEmpty()) {
            System.out.println("Non ci sono stati per questo mezzo!");
        }
        return query.getResultList();
    }
}
