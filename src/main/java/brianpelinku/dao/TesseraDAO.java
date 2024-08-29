package brianpelinku.dao;

import brianpelinku.entities.Tessera;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class TesseraDAO {
    private final EntityManager em;

    public TesseraDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera tessera) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tessera);
        transaction.commit();

        System.out.println("La tessera " + tessera.getId() + " è stata salvata correttamente!");
    }

    public Tessera findById(String tesseraId) {
        Tessera found = em.find(Tessera.class, UUID.fromString(tesseraId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(tesseraId);
        return found;
    }

    public void findByIdAndDelete(String tesseraId) {
        Tessera found = this.findById(tesseraId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La tessera " + found.getId() + " è stata eliminata correttamente!");
    }

    public void rinnovoValiditaTessera(String tesseraId) {
        try {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.createQuery("UPDATE Tessera a SET a.dataEmissione = LocalDate.now() WHERE a.id = :tesseraId")
                    .setParameter("tesseraId", UUID.fromString(tesseraId)).executeUpdate();
            ;

            transaction.commit();

            System.out.println("Rinnovo Validità Tessera avvenuto con successo!");
        } catch (Exception e) {
            System.err.println("Errore imprevisto: " + e.getMessage());
        }

    }

}
