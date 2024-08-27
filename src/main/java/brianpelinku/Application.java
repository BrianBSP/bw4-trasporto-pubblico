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


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            System.out.println("---- Benvenuto! Scegli una di queste opzioni ----");
            System.out.println("1 --> Inserisci utente");
            System.out.println("2 --> Inserisci  punto di emissione");



            int scelta = scanner.nextInt();
            scanner.nextLine();

           switch (scelta){

               case 1:
                   System.out.println("Inserire Nome ");
                   String nomeUtente = scanner.nextLine();
                   System.out.println("Inserire Cognome");
                   String cognomeUtente = scanner.nextLine();

                   Utente utente = new Utente(nomeUtente, cognomeUtente);
                   ud.save(utente);
                   System.out.println("Utente salvato con successo!");
                   break;





               case 2:
                   System.out.println("1 --> Distributore Automatico");
                   System.out.println("2 --> Rivenditore Autorizzato");

                   int sceltaPuntoEmissione = scanner.nextInt();
                   scanner.nextLine();

                   switch (sceltaPuntoEmissione){
                       case 1:
                           System.out.println("Scegli distributore automatico");
                           System.out.println("1 --> Distributore 1");
                           System.out.println("2 --> Distributore 2");

                           int sceltaDistributore = scanner.nextInt();

                           switch (sceltaDistributore){

                               case 1:
                                   System.out.println("Hai scelto " + distributore1.getNome() );
                                   break;

                               case 2:
                                   System.out.println("Hai scelto " + distributore2.getNome() );
                                   break;



                           }
                           break;

                       case 2:
                           System.out.println("Scegli rivenditore autorizzato");
                           System.out.println("1 --> Rivenditore 1 " + rivenditore1.getTipo());
                           System.out.println("2 --> Rivenditore 2 " + rivenditore2.getTipo());

                           int sceltaRivenditore = scanner.nextInt();

                           switch (sceltaRivenditore){

                               case 1:
                                   System.out.println("Hai scelto " + rivenditore1.getNome() );

                               case 2:
                                   System.out.println("Hai scelto " + rivenditore2.getNome() );



                           }



                   }





           }


        }
        scanner.close();
        em.close();
        emf.close();
    }
}
