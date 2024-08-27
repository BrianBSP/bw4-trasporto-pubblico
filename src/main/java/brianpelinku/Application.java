package brianpelinku;

import brianpelinku.ENUM.*;
import brianpelinku.dao.*;
import brianpelinku.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        StatoMezzoDAO smd= new StatoMezzoDAO(em);
        MezzoDAO md= new MezzoDAO(em);
        GiroTrattaDAO gd = new GiroTrattaDAO(em);
        TimbraturaDAO timbd = new TimbraturaDAO(em);


        DistributoreAutomatico distributore1 = new DistributoreAutomatico("Distributore1","Location1", StatoDistributore.ATTIVO);
        DistributoreAutomatico distributore2 = new DistributoreAutomatico("Distributore2","Location2", StatoDistributore.FUORI_SERVIZIO);

        RivenditoreAutorizzato rivenditore1 = new RivenditoreAutorizzato("Rivenditore1", "Location1", TipoRivenditore.EDICOLA);
        RivenditoreAutorizzato rivenditore2 = new RivenditoreAutorizzato("Rivenditore2", "Location2", TipoRivenditore.TABACCAIO);

        Utente utente1 = new Utente("Diego", "Basili");
        Utente utente2 = new Utente("Gabriel", "Azamfiri");

        Tessera tesseraUtente1 = new Tessera(utente1, LocalDate.of(2024,5,16));
        Tessera tesseraUtente2 = new Tessera(utente2, LocalDate.of(2024,2,16));

        Abbonamento abbonamento1= new Abbonamento(LocalDate.of(2024,8,26), Durata.ANNUALE,tesseraUtente1,rivenditore1 );
        Abbonamento abbonamento2= new Abbonamento(LocalDate.of(2024,7,20), Durata.MENSILE,tesseraUtente2,distributore1 );
        Abbonamento abbonamento3= new Abbonamento(LocalDate.of(2024,6,12), Durata.SETTIMANALE,tesseraUtente2,distributore1 );
        Abbonamento abbonamento4= new Abbonamento(LocalDate.of(2024,2,8), Durata.ANNUALE,tesseraUtente2,distributore1 );

        Biglietto biglietto1 = new Biglietto(LocalDate.of(2024,6,26),90,false,tesseraUtente1,rivenditore2);
        Biglietto biglietto2 = new Biglietto(LocalDate.of(2024,7,12),90,false,tesseraUtente2,distributore1);

        Tratta tratta1 = new Tratta("tratta1","partenza1","capolinea1",60);
        Tratta tratta2 = new Tratta("tratta2","partenza2","capolinea2",60);

       // StatoMezzo statoMezzo1= new StatoMezzo(LocalDate.of(2024,6,10), StatoDelMezzo.SERVIZIO);
       // StatoMezzo statoMezzo2= new StatoMezzo(LocalDate.of(2024,6,10), StatoDelMezzo.MANUTENZIONE);

        Mezzo mezzo1 = new Mezzo(TipoMezzo.TRAM,100,StatoDelMezzo.SERVIZIO,tratta1);
        Mezzo mezzo2 = new Mezzo(TipoMezzo.AUTOBUS,40,StatoDelMezzo.SERVIZIO,tratta2);

        StatoMezzo statoMezzo2 = new StatoMezzo(LocalDate.now(),StatoDelMezzo.MANUTENZIONE,mezzo2);

        GiroTratta giro1tratta1 = new GiroTratta(mezzo1,tratta1, LocalDateTime.of(2024,7,12,10,30),LocalDateTime.of(2024,7,12,11,20));
        GiroTratta giro2tratta1 = new GiroTratta(mezzo2,tratta1, LocalDateTime.of(2024,8,22,12,0),LocalDateTime.of(2024,8,22,12,58));
        GiroTratta giro3tratta1 = new GiroTratta(mezzo2,tratta1, LocalDateTime.of(2024,8,22,12,58),LocalDateTime.of(2024,8,22,14,1));
        GiroTratta giro4tratta1 = new GiroTratta(mezzo2,tratta1, LocalDateTime.of(2024,8,22,14,2),LocalDateTime.of(2024,8,22,15,5));



        Timbratura timbratura1 = new Timbratura(LocalDateTime.of(2024,7,12,10,31),mezzo1,biglietto1);
        Timbratura timbratura2 = new Timbratura(LocalDateTime.of(2024,8,22,12,10),mezzo2,biglietto2);

        ped.save(distributore1);
        ped.save(distributore2);
        ped.save(rivenditore1);
        ped.save(rivenditore2);
        ud.save(utente1);
        ud.save(utente2);
        td.save(tesseraUtente1);
        td.save(tesseraUtente2);
        ad.save(abbonamento3);
        ad.save(abbonamento4);
        ad.save(abbonamento1);
        ad.save(abbonamento2);
        bd.save(biglietto1);
        bd.save(biglietto2);
        trd.save(tratta1);
        trd.save(tratta2);
       // smd.save(statoMezzo1);
       // smd.save(statoMezzo2);
        md.save(mezzo1);
        md.save(mezzo2);
        smd.save(statoMezzo2);
        gd.save(giro1tratta1);
        gd.save(giro2tratta1);
        gd.save(giro3tratta1);
        gd.save(giro4tratta1);
        timbd.save(timbratura1);
        timbd.save(timbratura2);

        System.out.println("*********************findAbbonamentiPerPuntoEmissione********************************");
        ad.findAbbonamentiPerPuntoEmissione(distributore1).forEach(System.out::println);

        System.out.println("*********************findBigliettoPerPuntoEmissione********************************");
        bd.findBigliettoPerPuntoEmissione(distributore1).forEach(System.out::println);

        System.out.println("*********************findAbbonamentiNelTempo********************************");
        ad.findAbbonamentiNelTempo(LocalDate.of(2024,6,12),LocalDate.of(2024,9,12)).forEach(System.out::println);

        System.out.println("*********************findNumeroAbbonamentiNelTempo********************************");
        System.out.println( "Il numero di Abbonati del periodo richiesto è: " + ad.findNumeroAbbonamentiNelTempo(LocalDate.of(2024,6,12),LocalDate.of(2024,9,12)));

        System.out.println("*********************findBigliettiNelTempo********************************");
        bd.findBigliettiNelTempo(LocalDate.of(2024,8,12),LocalDate.of(2024,9,12)).forEach(System.out::println);

        System.out.println("*********************findValiditaAbbonamento********************************");
       ad.findValiditaAbbonamento(tesseraUtente1.getId().toString());

        System.out.println("*********************findStatiMezzo********************************");
        smd.findStatiMezzo(mezzo2).forEach(System.out::println);

        System.out.println("*********************findTimbratureNelTempo********************************");
        timbd.findTimbratureNelTempo(LocalDateTime.of(2024,7,2,10,4),LocalDateTime.of(2024,9,12,15,15)).forEach(System.out::println);

        System.out.println("*********************findTimbratureNelTempo********************************");
        timbd.findTimbratureDiUnMezzo(mezzo2.getId().toString()).forEach(System.out::println);

        System.out.println("*********************findGiriMezzo********************************");
        gd.findGiriMezzo(mezzo2).forEach(System.out::println);

        System.out.println("*********************findNumeroGiriMezzo********************************");
        gd.findNumeroGiriMezzo(mezzo2);

        System.out.println("*********************findTempoEffettivo********************************");
        gd.findTempoEffettivo(tratta1.getId().toString());

        System.out.println("*********************findMediaTempoEffettivo********************************");
        gd.findMediaTempoEffettivo(tratta1.getId().toString());
    }
}
