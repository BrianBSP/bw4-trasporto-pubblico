package brianpelinku;

import brianpelinku.ENUMS.*;
import brianpelinku.dao.*;
import brianpelinku.entities.*;
import brianpelinku.exceptions.InputErratoExceptions;
import brianpelinku.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    public static final Scanner scanner = new Scanner(System.in);
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    public static final EntityManager em = emf.createEntityManager();
    public static final UtenteDAO ud = new UtenteDAO(em);
    public static final PuntoEmissioneDAO ped = new PuntoEmissioneDAO(em);
    public static final TesseraDAO td = new TesseraDAO(em);
    public static final AbbonamentoDAO ad = new AbbonamentoDAO(em);
    public static final BigliettoDAO bd = new BigliettoDAO(em);
    public static final TrattaDAO trd = new TrattaDAO(em);
    public static final StatoMezzoDAO smd = new StatoMezzoDAO(em);
    public static final MezzoDAO md = new MezzoDAO(em);
    public static final GiroTrattaDAO gd = new GiroTrattaDAO(em);
    public static final TimbraturaDAO timbd = new TimbraturaDAO(em);
    public static Tessera tesseraUtenteLoggato = null;

    public static void main(String[] args) {
        //EntityManager em = emf.createEntityManager();

        DistributoreAutomatico distributore1 = new DistributoreAutomatico("Distributore1", "Location1", StatoDistributore.ATTIVO);
        DistributoreAutomatico distributore2 = new DistributoreAutomatico("Distributore2", "Location2", StatoDistributore.FUORI_SERVIZIO);

        RivenditoreAutorizzato rivenditore1 = new RivenditoreAutorizzato("Rivenditore1", "Location1", TipoRivenditore.EDICOLA);
        RivenditoreAutorizzato rivenditore2 = new RivenditoreAutorizzato("Rivenditore2", "Location2", TipoRivenditore.TABACCAIO);

        Utente utente1 = new Utente("Diego", "Basili");
        Utente utente2 = new Utente("Gabriel", "Azamfiri");

        Tessera tesseraUtente1 = new Tessera(utente1, LocalDate.of(2024, 5, 16));
        Tessera tesseraUtente2 = new Tessera(utente2, LocalDate.of(2024, 2, 16));

        Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2024, 8, 26), Durata.ANNUALE, tesseraUtente1, rivenditore1);
        Abbonamento abbonamento2 = new Abbonamento(LocalDate.of(2024, 7, 20), Durata.MENSILE, tesseraUtente2, distributore1);
        Abbonamento abbonamento3 = new Abbonamento(LocalDate.of(2024, 6, 12), Durata.SETTIMANALE, tesseraUtente2, distributore1);
        Abbonamento abbonamento4 = new Abbonamento(LocalDate.of(2024, 2, 8), Durata.ANNUALE, tesseraUtente2, distributore1);

        Biglietto biglietto1 = new Biglietto(LocalDate.of(2024, 6, 26), 90, false, tesseraUtente1, rivenditore2);
        Biglietto biglietto2 = new Biglietto(LocalDate.of(2024, 7, 12), 90, false, tesseraUtente2, distributore1);

        Tratta tratta1 = new Tratta("tratta1", "partenza1", "capolinea1", 60);
        Tratta tratta2 = new Tratta("tratta2", "partenza2", "capolinea2", 60);


        Mezzo mezzo1 = new Mezzo(TipoMezzo.TRAM, 100, StatoDelMezzo.SERVIZIO, trd.findById("3f2ce3aa-4117-4248-8d3a-cd68e6704f96"));
        Mezzo mezzo2 = new Mezzo(TipoMezzo.AUTOBUS, 40, StatoDelMezzo.SERVIZIO, trd.findById("4f702139-88da-44b7-9921-1c32c58ed534"));

        // StatoMezzo statoMezzo1 = new StatoMezzo(LocalDate.now(), StatoDelMezzo.SERVIZIO, md.findById("2279ba24-b8a6-48c3-b1b4-4c06fc52084a"));

        GiroTratta giro1tratta1 = new GiroTratta(mezzo1, tratta1, LocalDateTime.of(2024, 7, 12, 10, 30), LocalDateTime.of(2024, 7, 12, 11, 20));
        GiroTratta giro2tratta1 = new GiroTratta(mezzo2, tratta1, LocalDateTime.of(2024, 8, 22, 12, 0), LocalDateTime.of(2024, 8, 22, 12, 58));
        GiroTratta giro3tratta1 = new GiroTratta(mezzo2, tratta1, LocalDateTime.of(2024, 8, 22, 12, 58), LocalDateTime.of(2024, 8, 22, 14, 1));
        GiroTratta giro4tratta1 = new GiroTratta(mezzo2, tratta1, LocalDateTime.of(2024, 8, 22, 14, 2), LocalDateTime.of(2024, 8, 22, 15, 5));


        Timbratura timbratura1 = new Timbratura(LocalDateTime.of(2024, 7, 12, 10, 31), mezzo1, biglietto1);
        Timbratura timbratura2 = new Timbratura(LocalDateTime.of(2024, 8, 22, 12, 10), mezzo2, biglietto2);

        /*ped.save(distributore1);
        ped.save(distributore2);
        ped.save(rivenditore1);
        ped.save(rivenditore2);*/
//        ud.save(utente1);
//        ud.save(utente2);
//        td.save(tesseraUtente1);
//        td.save(tesseraUtente2);
//        ad.save(abbonamento3);
//        ad.save(abbonamento4);
//        ad.save(abbonamento1);
//        ad.save(abbonamento2);
//        bd.save(biglietto1);
//        bd.save(biglietto2);
//        trd.save(tratta1);
//        trd.save(tratta2);
        //  smd.save(statoMezzo1);
//       // smd.save(statoMezzo2);
        //md.save(mezzo1);
        // md.save(mezzo2);
//        smd.save(statoMezzo2);
//        gd.save(giro1tratta1);
//        gd.save(giro2tratta1);
//        gd.save(giro3tratta1);
//        gd.save(giro4tratta1);
//        timbd.save(timbratura1);
//        timbd.save(timbratura2);


//        System.out.println("*********************findAbbonamentiPerPuntoEmissione********************************");
//        ad.findAbbonamentiPerPuntoEmissione(distributore1).forEach(System.out::println);
//
//        System.out.println("*********************findBigliettoPerPuntoEmissione********************************");
//        bd.findBigliettoPerPuntoEmissione(distributore1).forEach(System.out::println);
//
//        System.out.println("*********************findAbbonamentiNelTempo********************************");
//        ad.findAbbonamentiNelTempo(LocalDate.of(2024,6,12),LocalDate.of(2024,9,12)).forEach(System.out::println);
//
//        System.out.println("*********************findNumeroAbbonamentiNelTempo********************************");
//        System.out.println( "Il numero di Abbonati del periodo richiesto è: " + ad.findNumeroAbbonamentiNelTempo(LocalDate.of(2024,6,12),LocalDate.of(2024,9,12)));
//
//        System.out.println("*********************findBigliettiNelTempo********************************");
//        bd.findBigliettiNelTempo(LocalDate.of(2024,6,12),LocalDate.of(2024,9,12)).forEach(System.out::println);
//
//        System.out.println("*********************findValiditaAbbonamento********************************");
//        ad.findValiditaAbbonamento(tesseraUtente2.getId().toString());
//
//        System.out.println("*********************findStatiMezzo********************************");
//        smd.findStatiMezzo(md.findById("2279ba24-b8a6-48c3-b1b4-4c06fc52084a")).forEach(System.out::println);
//
//        System.out.println("*********************findTimbratureNelTempo********************************");
//        timbd.findTimbratureNelTempo(LocalDateTime.of(2024,7,2,10,4),LocalDateTime.of(2024,9,12,15,15)).forEach(System.out::println);
//
//        System.out.println("*********************findTimbratureDiUnMezzo********************************");
//        timbd.findTimbratureDiUnMezzo(mezzo2.getId().toString()).forEach(System.out::println);
//
//        System.out.println("*********************findGiriMezzo********************************");
//        gd.findGiriMezzo(mezzo2).forEach(System.out::println);
//
//        System.out.println("*********************findNumeroGiriMezzo********************************");
//        gd.findNumeroGiriMezzo(mezzo2);
//
//        System.out.println("*********************findTempoEffettivo********************************");
//        gd.findTempoEffettivo(tratta1.getId().toString());
//
//        System.out.println("*********************findMediaTempoEffettivo********************************");
//        gd.findMediaTempoEffettivo("a75e415a-6b18-4b0f-a5c3-9b1fa839315b");


        trasportoPubblico:
        while (true) {
            inizioGestione();
            int scelta = gestioneInputScanner();
            switch (scelta) {
                case 1:
                    // amministratore
                    gestioneAmministratore();
                    break;
                case 2:
                    // utente
                    gestioneUtente();
                    break;

                case 0:
                    // esci
                    scanner.close();
                    return;
                default:
                    System.out.println("Segui le istruzioni per proseguire correttamente.");
            }
            break;
        }


        em.close();
        emf.close();
    }

    public static void inizioGestione() {
        System.out.println("-- Benvenuto --");
        System.out.println("Segui le istruzioni per proseguire: ");
        System.out.println("Premi 1 se sei un amministratore");
        System.out.println("Premi 2 se sei un utente");
        System.out.println("Premi 0 per uscire");

    }

    public static int gestioneInputScanner() {
        while (true) {
            try {
                int num = Integer.parseInt(scanner.nextLine());
                return num;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }

        }
    }

    // gestione amministratore
    public static void gestioneAmministratore() {
        while (true) {
            try {
                System.out.println("Inserisci la password: ");
                int password = Integer.parseInt(scanner.nextLine());
                if (password == 1234) {
                    System.out.println("Password CORRETTA. Accesso consentito.");
                    scegliOpzioneAdmin();

                } else {
                    System.out.println("Password ERRATA. Accesso negato.");
                }
            } catch (InputErratoExceptions e) {
                System.out.println(e.getMessage());
            }
            break;
        }
    }

    public static void scegliOpzioneAdmin() {
        try {

            System.out.println("\n--> Scegli un opzione per proseguire: ");
            System.out.println("Premi 1 per Trovare abbonamenti venduti per ogni punto vendita");
            System.out.println("Premi 2 per Trovare biglietti venduti per ogni punto vendita");
            System.out.println("Premi 3 per Trovare abbonamenti venduti in un certo periodo");
            System.out.println("Premi 4 per Trovare  il numero degli abbonamenti venduti in un certo periodo");

            System.out.println("Premi 5 per Trovare biglietti venduti in un certo periodo");
            System.out.println("Premi 6 per Trovare il numero di biglietti venduti in un certo periodo");


            System.out.println("Premi 7 per Controllare tipo di validità dell'abbonamento");
            System.out.println("Premi 8 per Controllare lo stato di attività dei mezzi");
            System.out.println("Premi 9 per Trovare i biglietti timbrati in un certo periodo");
            System.out.println("Premi 10 per Trovare i biglietti timbrati in un certo mezzo");
            System.out.println("Premi 11 per Trovare tutti i giri fatti da un mezzo");
            System.out.println("Premi 12 per Trovare il numero dei giri fatti da un mezzo");
            System.out.println("Premi 13 per trovare il tempo effettivo di percorrenza di un tratta da un determinato mezzo");
            System.out.println("Premi 14 per Trovare il tempo medio di percorrenza della tratta scelta");
            System.out.println("Premi 0 per USCIRE");

            int sceltaAdmin = Integer.parseInt(scanner.nextLine());

            switch (sceltaAdmin) {
                case 1:
                    scegliPuntoVenditaTrovaAbbon();
                    break;
                case 2:
                    scegliPuntoVenditaTrovaBiglietti();
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    trovaBigliettiVendutiInPeriodo();
                    break;
                default:
                    System.out.println("Scegli correttamente un opzione per proseguire.");
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void scegliPuntoVenditaTrovaAbbon() {
        System.out.println("\n--> Scegli un Punto vendita per proseguire con l'operazione scelta: ");
        List<PuntoEmissione> puntiVendita = puntiVendita();
        for (int i = 0; i < puntiVendita.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
        }
        try {
            int sceltaPunta = Integer.parseInt(scanner.nextLine());
            if (sceltaPunta >= 0 && sceltaPunta < puntiVendita.size()) {
                PuntoEmissione puntoScelto = puntiVendita.get(sceltaPunta);
                ad.findAbbonamentiPerPuntoEmissione(puntoScelto).forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void scegliPuntoVenditaTrovaBiglietti() {
        System.out.println("\n--> Scegli un Punto vendita per proseguire con l'operazione scelta: ");
        List<PuntoEmissione> puntiVendita = puntiVendita();
        for (int i = 0; i < puntiVendita.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
        }
        try {
            int sceltaPunta = Integer.parseInt(scanner.nextLine());
            if (sceltaPunta >= 0 && sceltaPunta < puntiVendita.size()) {
                PuntoEmissione puntoScelto = puntiVendita.get(sceltaPunta);
                bd.findBigliettoPerPuntoEmissione(puntoScelto).forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void trovaBigliettiVendutiInPeriodo() {
        try {
            System.out.println("Inserisci data di inizio periodo: ");
            String dataInizio = scanner.nextLine();
            LocalDate dataInizioControllo = LocalDate.parse(dataInizio);

            System.out.println("Inserisci data di fine periodo: ");
            String dataFine = scanner.nextLine();
            LocalDate dataFineControllo = LocalDate.parse(dataFine);

            bd.findBigliettiNelTempo(dataInizioControllo, dataFineControllo).forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // gestione utente
    public static void gestioneUtenteRegistrato() {
        while (true) {
            try {
                System.out.println("Inserisci il tuo ID Tessera: ");
                UUID tesseraID = UUID.fromString(scanner.nextLine());
                Tessera tessera = td.findById(String.valueOf(tesseraID));
                if (tessera != null) {
                    List<PuntoEmissione> puntiVendita = puntiVendita();
                    //puntiVendita.forEach(System.out::println);
                    System.out.println("\n--> Scegli un punto vendita.");
                    for (int i = 0; i < puntiVendita.size(); i++) {
                        //puntiVendita.forEach(System.out::println);
                        System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
                    }

                    try {
                        int sceltaPuntoEmis = Integer.parseInt(scanner.nextLine()) - 1;
                        if (sceltaPuntoEmis >= 0 && sceltaPuntoEmis < puntiVendita.size()) {
                            PuntoEmissione puntoScelto = puntiVendita.get(sceltaPuntoEmis);
                            System.out.println("Hai scelto il punto vendita " + puntoScelto.getNome());
                            System.out.println("\n--> Scegli un opzione per proseguire.");
                            System.out.println("Premi 1 se vuoi comprare uno o più biglietti");
                            System.out.println("Premi 2 se vuoi comprare un abbonamento");
                            int scelta = Integer.parseInt(scanner.nextLine());
                            switch (scelta) {
                                case 1:
                                    // acquisto biglietti
                                    acquistoBiglietti(tessera, puntoScelto);
                                    break;
                                case 2:
                                    // acquisto abbonamento
                                    acquistaAbbonamento(tessera, puntoScelto);
                                    break;
                                default:
                                    System.out.println("Inserire un numero intero tra 1 e 2.");
                                    break;
                            }
                        } else {
                            System.out.println("Scelta NON valida. Inserire un numero tra 1 e " + puntiVendita.size());
                            scanner.nextLine();
                        }
                    } catch (InputErratoExceptions e) {
                        System.out.println(e.getMessage());
                    }


                } else {
                    System.out.println("Tessera NON trovata.");
                }

            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
            break;
        }
    }

    public static List<PuntoEmissione> puntiVendita() {
        TypedQuery<PuntoEmissione> query = em.createQuery("SELECT p FROM PuntoEmissione p", PuntoEmissione.class);
        return query.getResultList();
    }

    public static List<Tratta> tratte() {
        TypedQuery<Tratta> query = em.createQuery("SELECT t FROM Tratta t", Tratta.class);
        return query.getResultList();
    }

    public static List<Biglietto> bigliettiUtente(Tessera tessera) {
        TypedQuery<Biglietto> query = em.createQuery("SELECT b FROM Biglietto b WHERE b.idTessera = :tessera", Biglietto.class);
        query.setParameter("tessera", tessera);
        //System.out.println(tessera);
        return query.getResultList();
    }

    public static List<Mezzo> mezzi(Tratta tratta) {
        TypedQuery<Mezzo> query = em.createQuery("SELECT m FROM Mezzo m WHERE m.idTratta = :tratta", Mezzo.class);
        query.setParameter("tratta", tratta);
        System.out.println("Mezzi disponibili per questa tratta: " + tratta.getNome());
        query.getResultList().forEach(System.out::println);
        return query.getResultList();
    }

    public static void acquistoBiglietti(Tessera tesseraID, PuntoEmissione puntoScelto) {
        while (true) {
            try {
                System.out.println("Quanti biglietti vuoi comprare?");
                int num = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < num; i++) {
                    Biglietto biglietto = new Biglietto(LocalDate.now(), 90, false, tesseraID, puntoScelto);
                    bd.save(biglietto);
                    System.out.println("Biglietto " + (i + 1) + " creato: " + biglietto);
                }
                Tratta trattaScelta = scegliTratta(tesseraID);


            } catch (NumberFormatException e) {
                System.out.println("Input NON valido. Inserire un numero intero.");
                scanner.nextLine();
            } catch (InputErratoExceptions e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
            break;
        }
    }

    public static void acquistaAbbonamento(Tessera tesseraID, PuntoEmissione puntoScelto) {
        while (true) {
            try {
                System.out.println("Creazione Abbonamento in corso...");
                System.out.println("Scegli la durata dell'abbonamento: ");
                System.out.println("SETTIMANALE");
                System.out.println("MENSILE");
                System.out.println("ANNUALE");
                String durataAbbonamento = scanner.nextLine();
                Durata durata = Durata.valueOf(durataAbbonamento.toUpperCase());
                System.out.println("Scegli la data di emissione (formato AAAA-MM-GG): ");
                String dataEmiss = scanner.nextLine();
                LocalDate dataEmissione = LocalDate.parse(dataEmiss);
                Abbonamento abbonamento = new Abbonamento(dataEmissione, durata, tesseraID, puntoScelto);
                System.out.println("Abbonamento " + abbonamento.toString() + " salvato correttamente.");
                ad.save(abbonamento);
                // scegli tratta
                System.out.println("Scegli la tratta: ");
                List<Tratta> tratte = tratte();
                for (int i = 0; i < tratte().size(); i++) {
                    System.out.println("Premi " + (i + 1) + " per " + tratte().get(i).getNome());
                }
                try {
                    int scegliTratta = Integer.parseInt(scanner.nextLine()) - 1;
                    if (scegliTratta >= 0 && scegliTratta < tratte().size()) {
                        Tratta trattoScelto = tratte.get(scegliTratta);
                        System.out.println("Hai scelto la tratta " + trattoScelto.getNome());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
            break;
        }


    }

    public static Tratta scegliTratta(Tessera tesseraID) {
        Tratta trattoScelto = null;
        try {
            System.out.println("\n--> Scegli la tratta: ");
            List<Tratta> tratte = tratte();
            for (int i = 0; i < tratte().size(); i++) {
                System.out.println("Premi " + (i + 1) + " per " + tratte().get(i).getNome());
            }

            // scegli tratta
            int scegliTratta = Integer.parseInt(scanner.nextLine()) - 1;
            if (scegliTratta >= 0 && scegliTratta < tratte().size()) {
                trattoScelto = tratte.get(scegliTratta);
                System.out.println("Hai scelto la tratta " + trattoScelto.getNome() + " \n");
                Mezzo mezzoScelto = scegliMezzo(trattoScelto);
                Biglietto bigliettoScelto = scegliBiglietto(tesseraID);
                // timbratura
                timbraBiglietto(mezzoScelto, bigliettoScelto);
            }

        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserire un numero intero.");
        } catch (Exception e) {
            System.out.println("Errore durante la scelta della tratta: " + e.getMessage());
        }

        return trattoScelto;
    }

    public static Mezzo scegliMezzo(Tratta tratta) {
        // scegli mezzo

        Mezzo mezzoScelto = null;
        try {
            System.out.println("\n--> Scegli il mezzo per questa tratta: " + tratta.getNome());
            List<Mezzo> mezzi = mezzi(tratta);
            for (int i = 0; i < mezzi.size(); i++) {
                System.out.println("Premi " + (i + 1) + " per " + mezzi.get(i).getTipo());
            }
            int scegliMezzo = Integer.parseInt(scanner.nextLine()) - 1;
            if (scegliMezzo >= 0 && scegliMezzo < mezzi.size()) {
                mezzoScelto = mezzi.get(scegliMezzo);
                System.out.println("Hai scelto il mezzo: " + mezzoScelto.getTipo());

            } else {
                System.out.println("Scelta NON valida. Riprova.");
                scanner.nextLine();
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserire un numero intero.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mezzoScelto;
    }

    public static void gestioneUtente() {
        while (true) {
            System.out.println("Premi 1 per ACCEDERE e PROCEDI all'acquisto.");
            System.out.println("Premi 2 per REGISTRARTI");
            int sceltaUtente = Integer.parseInt(scanner.nextLine());
            switch (sceltaUtente) {
                case 1:
                    gestioneUtenteRegistrato();

                    break;
                case 2:
                    Tessera tesseraUtente = gestioneNuovoUtente();

                    break;
                default:
                    System.out.println("Inserire un numero intero tra 1 e 2.");
                    break;
            }
            break;

        }
    }

    public static Tessera gestioneNuovoUtente() {
        while (true) {
            try {
                System.out.println("Inserisci il tuo Nome: ");
                String nomeUtente = scanner.nextLine();
                System.out.println("Inserisci il tuo Cognome: ");
                String cognomeUtente = scanner.nextLine();

                // creo il nuovo utente
                Utente utente = new Utente(nomeUtente, cognomeUtente);
                ud.save(utente);
                System.out.println("Utente creato correttamente.");
                // creo tessera di conseguenza
                Tessera tesseraUtente = new Tessera(utente, LocalDate.now());
                td.save(tesseraUtente);
                System.out.println(tesseraUtente);
                gestioneUtenteRegistrato();
                tesseraUtenteLoggato = td.findById(tesseraUtente.getId().toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        return tesseraUtenteLoggato;
    }

    public static Biglietto scegliBiglietto(Tessera tesseraID) {
        Biglietto bigliettoScelto = null;
        try {
            System.out.println("\n--> Scegli il biglietto che vuoi timbrare: ");
            for (int i = 0; i < bigliettiUtente(tesseraID).size(); i++) {
                System.out.println("Premi " + (i + 1) + " per " + bigliettiUtente(tesseraID).get(i).getId());
            }
            // scegli biglietto
            int scegliBiglietto = Integer.parseInt(scanner.nextLine()) - 1;
            if (scegliBiglietto >= 0 && scegliBiglietto < bigliettiUtente(tesseraID).size()) {
                bigliettoScelto = bigliettiUtente(tesseraID).get(scegliBiglietto);
                System.out.println("Hai scelto il biglietto " + bigliettoScelto.getId());
            } else {
                System.out.println("Scelta NON valida. Riprova.");
                scanner.nextLine();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bigliettoScelto;
    }

    public static void timbraBiglietto(Mezzo mezzo, Biglietto biglietto) {

        try {
            Timbratura timbratura = new Timbratura(LocalDateTime.now(), mezzo, biglietto);
            timbd.save(timbratura);
            System.out.println("Il biglietto " + biglietto.getId() + " é stato timbrato correttamente.");
            esciContinua(biglietto.getIdTessera());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void esciContinua(Tessera tesseraID) {
        try {
            System.out.println("\n--> Scegli opzione");
            System.out.println("Premi 0 per USCIRE");
            System.out.println("Premi 1 se vuoi continuare ad acquistare.");
            int sceltaOpzione = Integer.parseInt(scanner.nextLine());
            esci:
            switch (sceltaOpzione) {
                case 1:
                    continuaAdAcquistare(tesseraID);
                    break;
                case 0:
                    System.out.println("\nGrazie per averci scelto! \nA presto!");
                    scanner.close();
                    break esci;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void continuaAdAcquistare(Tessera tesseraID) {
        if (tesseraID != null) {
            List<PuntoEmissione> puntiVendita = puntiVendita();
            System.out.println("\n--> Scegli un punto vendita.");

            for (int i = 0; i < puntiVendita.size(); i++) {
                System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
            }

            try {
                int sceltaPuntoEmis = Integer.parseInt(scanner.nextLine()) - 1;
                if (sceltaPuntoEmis >= 0 && sceltaPuntoEmis < puntiVendita.size()) {
                    PuntoEmissione puntoScelto = puntiVendita.get(sceltaPuntoEmis);
                    System.out.println("Hai scelto il punto vendita " + puntoScelto.getNome());
                    System.out.println("\n--> Scegli un opzione per proseguire.");
                    System.out.println("Premi 1 se vuoi comprare uno o più biglietti");
                    System.out.println("Premi 2 se vuoi comprare un abbonamento");
                    int scelta = Integer.parseInt(scanner.nextLine());
                    switch (scelta) {
                        case 1:
                            // acquisto biglietti
                            acquistoBiglietti(tesseraID, puntoScelto);
                            break;
                        case 2:
                            // acquisto abbonamento
                            acquistaAbbonamento(tesseraID, puntoScelto);
                            break;
                        default:
                            System.out.println("Inserire un numero intero tra 1 e 2.");
                            break;
                    }
                } else {
                    System.out.println("Scelta NON valida. Inserire un numero tra 1 e " + puntiVendita.size());
                    scanner.nextLine();
                }
            } catch (InputErratoExceptions e) {
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Tessera NON trovata.");
        }

    }
}




