package brianpelinku;

import brianpelinku.ENUMS.*;
import brianpelinku.dao.*;
import brianpelinku.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        PuntoEmissioneDAO ped = new PuntoEmissioneDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        TesseraDAO td = new TesseraDAO(em);
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        BigliettoDAO bd = new BigliettoDAO(em);
        TrattaDAO trd = new TrattaDAO(em);
        StatoMezzoDAO smd = new StatoMezzoDAO(em);
        MezzoDAO md = new MezzoDAO(em);
        GiroTrattaDAO gd = new GiroTrattaDAO(em);
        TimbraturaDAO timbd = new TimbraturaDAO(em);


        Scanner scanner = new Scanner(System.in);


        DistributoreAutomatico distributore1 = new DistributoreAutomatico("Distributore1", "Location1", StatoDistributore.ATTIVO);
        DistributoreAutomatico distributore2 = new DistributoreAutomatico("Distributore2", "Location2", StatoDistributore.FUORI_SERVIZIO);
        /*ped.save(distributore1);
        ped.save(distributore2);*/

        RivenditoreAutorizzato rivenditore1 = new RivenditoreAutorizzato("Rivenditore1", "Location1", TipoRivenditore.EDICOLA);
        RivenditoreAutorizzato rivenditore2 = new RivenditoreAutorizzato("Rivenditore2", "Location2", TipoRivenditore.TABACCAIO);
        /*ped.save(rivenditore1);
        ped.save(rivenditore2);*/

        Utente utente1 = new Utente("Diego", "Basili");
        Utente utente2 = new Utente("Gabriel", "Azamfiri");
        /*ud.save(utente1);
        ud.save(utente2);*/

        Tessera tesseraUtente1 = new Tessera(utente1, LocalDate.of(2024, 5, 16));
        Tessera tesseraUtente2 = new Tessera(utente2, LocalDate.of(2024, 2, 16));
        /*td.save(tesseraUtente1);
        td.save(tesseraUtente2);*/

        Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2024, 8, 26), Durata.ANNUALE, tesseraUtente1, rivenditore1);
        Abbonamento abbonamento2 = new Abbonamento(LocalDate.of(2024, 7, 20), Durata.MENSILE, tesseraUtente2, distributore1);
        /*ad.save(abbonamento1);
        ad.save(abbonamento2);*/

        Biglietto biglietto1 = new Biglietto(LocalDate.of(2024, 8, 26), 90, false, tesseraUtente1, rivenditore2);
        Biglietto biglietto2 = new Biglietto(LocalDate.of(2024, 7, 12), 90, false, tesseraUtente2, distributore1);
        /*bd.save(biglietto1);
        bd.save(biglietto2);*/

        Tratta tratta1 = new Tratta("tratta1", "partenza1", "capolinea1", 60);
        Tratta tratta2 = new Tratta("tratta2", "partenza2", "capolinea2", 60);
        /*trd.save(tratta1);
        trd.save(tratta2);*/

        StatoMezzo statoMezzo1 = new StatoMezzo(LocalDate.of(2024, 6, 10), StatoDelMezzo.SERVIZIO);
        StatoMezzo statoMezzo2 = new StatoMezzo(LocalDate.of(2024, 6, 10), StatoDelMezzo.MANUTENZIONE);
        /*smd.save(statoMezzo1);
        smd.save(statoMezzo2);*/

        Mezzo mezzo1 = new Mezzo(TipoMezzo.TRAM, 100, statoMezzo1, tratta1);
        Mezzo mezzo2 = new Mezzo(TipoMezzo.AUTOBUS, 100, statoMezzo2, tratta2);
        /*md.save(mezzo1);
        md.save(mezzo2);*/

        GiroTratta giro1tratta1 = new GiroTratta(mezzo1, tratta1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(55));
        GiroTratta giro2tratta1 = new GiroTratta(mezzo2, tratta1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(58));
        /*gd.save(giro1tratta1);
        gd.save(giro2tratta1);*/

        Timbratura timbratura1 = new Timbratura(LocalDateTime.now(), mezzo1, biglietto1);
        Timbratura timbratura2 = new Timbratura(LocalDateTime.now(), mezzo2, biglietto2);
        /*timbd.save(timbratura1);
        timbd.save(timbratura2);*/

        /*    ciao*/


        scanner.close();
        em.close();
        emf.close();
    }
}
