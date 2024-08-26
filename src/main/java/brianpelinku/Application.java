package brianpelinku;

import brianpelinku.ENUM.StatoDistributore;
import brianpelinku.ENUM.TipoRivenditore;
import brianpelinku.dao.PuntoEmissioneDAO;
import brianpelinku.entities.DistributoreAutomatico;
import brianpelinku.entities.RivenditoreAutorizzato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        PuntoEmissioneDAO ped = new PuntoEmissioneDAO(em);

        DistributoreAutomatico distributore1 = new DistributoreAutomatico("Distributore1","Location1", StatoDistributore.ATTIVO);
        DistributoreAutomatico distributore2 = new DistributoreAutomatico("Distributore2","Location2", StatoDistributore.FUORI_SERVIZIO);
        ped.save(distributore1);
        ped.save(distributore2);

        RivenditoreAutorizzato rivenditore1 = new RivenditoreAutorizzato("Rivenditore1", "Location1", TipoRivenditore.EDICOLA);
        RivenditoreAutorizzato rivenditore2 = new RivenditoreAutorizzato("Rivenditore2", "Location2", TipoRivenditore.TABACCAIO);
        ped.save(rivenditore1);
        ped.save(rivenditore2);






    }
}
