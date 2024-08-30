package brianpelinku;

import brianpelinku.ENUMS.*;
import brianpelinku.dao.*;
import brianpelinku.entities.*;
import brianpelinku.exceptions.InputErratoExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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

//        DistributoreAutomatico distributore1 = new DistributoreAutomatico("Distributore1", "Location1", StatoDistributore.ATTIVO);
//        DistributoreAutomatico distributore2 = new DistributoreAutomatico("Distributore2", "Location2", StatoDistributore.FUORI_SERVIZIO);
//        RivenditoreAutorizzato rivenditore1 = new RivenditoreAutorizzato("Rivenditore1", "Location1", TipoRivenditore.EDICOLA);
//        RivenditoreAutorizzato rivenditore2 = new RivenditoreAutorizzato("Rivenditore2", "Location2", TipoRivenditore.TABACCAIO);
//        Utente utente1 = new Utente("Diego", "Basili");
//        Utente utente2 = new Utente("Gabriel", "Azamfiri");
//        Tessera tesseraUtente1 = new Tessera(utente1, LocalDate.of(2024, 5, 16));
//        Tessera tesseraUtente2 = new Tessera(utente2, LocalDate.of(2024, 2, 16));
//        Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2024, 8, 26), Durata.ANNUALE, tesseraUtente1, rivenditore1);
//        Abbonamento abbonamento2 = new Abbonamento(LocalDate.of(2024, 7, 20), Durata.MENSILE, tesseraUtente2, distributore1);
//        Abbonamento abbonamento3 = new Abbonamento(LocalDate.of(2024, 6, 12), Durata.SETTIMANALE, tesseraUtente2, distributore1);
//        Abbonamento abbonamento4 = new Abbonamento(LocalDate.of(2024, 2, 8), Durata.ANNUALE, tesseraUtente2, distributore1);
//        Biglietto biglietto1 = new Biglietto(LocalDate.of(2024, 6, 26), 90, false, tesseraUtente1, rivenditore2);
//        Biglietto biglietto2 = new Biglietto(LocalDate.of(2024, 7, 12), 90, false, tesseraUtente2, distributore1);
//        Tratta tratta1 = new Tratta("tratta1", "partenza1", "capolinea1", 60);
//        Tratta tratta2 = new Tratta("tratta2", "partenza2", "capolinea2", 60);
//        Mezzo mezzo1 = new Mezzo(TipoMezzo.TRAM, 100, StatoDelMezzo.SERVIZIO, trd.findById("3f2ce3aa-4117-4248-8d3a-cd68e6704f96"));
//        Mezzo mezzo2 = new Mezzo(TipoMezzo.AUTOBUS, 40, StatoDelMezzo.SERVIZIO, trd.findById("4f702139-88da-44b7-9921-1c32c58ed534"));
//        // StatoMezzo statoMezzo1 = new StatoMezzo(LocalDate.now(), StatoDelMezzo.SERVIZIO, md.findById("2279ba24-b8a6-48c3-b1b4-4c06fc52084a"));
//        GiroTratta giro1tratta1 = new GiroTratta(mezzo1, tratta1, LocalDateTime.of(2024, 7, 12, 10, 30), LocalDateTime.of(2024, 7, 12, 11, 20));
//        GiroTratta giro2tratta1 = new GiroTratta(md.findById("0db1a909-1630-4c0c-993d-84e8f489afba"), trd.findById("3f2ce3aa-4117-4248-8d3a-cd68e6704f96"), LocalDateTime.of(2024, 8, 22, 12, 0), LocalDateTime.of(2024, 8, 22, 12, 58));
//        GiroTratta giro3tratta1 = new GiroTratta(mezzo2, tratta1, LocalDateTime.of(2024, 8, 22, 12, 58), LocalDateTime.of(2024, 8, 22, 14, 1));
//        GiroTratta giro4tratta1 = new GiroTratta(mezzo2, tratta1, LocalDateTime.of(2024, 8, 22, 14, 2), LocalDateTime.of(2024, 8, 22, 15, 5));
//        Timbratura timbratura1 = new Timbratura(LocalDateTime.of(2024, 7, 12, 10, 31), mezzo1, biglietto1);
//        Timbratura timbratura2 = new Timbratura(LocalDateTime.of(2024, 8, 22, 12, 10), mezzo2, biglietto2);
//        //ped.updateStatoDistributore("88a2b9f8-e79f-406d-9b26-afe52b246569", StatoDistributore.FUORI_SERVIZIO);
//        /*ped.save(distributore1);
//        ped.save(distributore2);
//        ped.save(rivenditore1);
//        ped.save(rivenditore2);*/
////        ud.save(utente1);
////        ud.save(utente2);
////        td.save(tesseraUtente1);
////        td.save(tesseraUtente2);
////        ad.save(abbonamento3);
////        ad.save(abbonamento4);
////        ad.save(abbonamento1);
////        ad.save(abbonamento2);
////        bd.save(biglietto1);
////        bd.save(biglietto2);
////        trd.save(tratta1);
////        trd.save(tratta2);
//        //  smd.save(statoMezzo1);
////        smd.save(statoMezzo2);
//        //md.save(mezzo1);
//        //md.save(mezzo2);
////        smd.save(statoMezzo2);
////        gd.save(giro1tratta1);
//        //gd.save(giro2tratta1);
////        gd.save(giro3tratta1);
////        gd.save(giro4tratta1);
////        timbd.save(timbratura1);
////        timbd.save(timbratura2);


        boolean start = true;

        trasportoPubblico:
        while (start) {
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

                    start = false;
                    System.out.println("\nGrazie per averci scelto! \nA presto!");

                    scanner.close();
                    break;
                default:
                    System.out.println("Segui le istruzioni per proseguire correttamente.");
            }

        }


        em.close();
        emf.close();
    }


    //gestione errori ok

    // queries
    public static List<Tessera> tessere() {
        TypedQuery<Tessera> query = em.createQuery("SELECT t FROM Tessera t", Tessera.class);
        return query.getResultList();
    }

    public static List<Mezzo> tuttiMezzi() {
        TypedQuery<Mezzo> query = em.createQuery("SELECT m FROM Mezzo m", Mezzo.class);
        return query.getResultList();
    }

    public static List<PuntoEmissione> puntiVendita() {
        TypedQuery<PuntoEmissione> query = em.createQuery("SELECT p FROM PuntoEmissione p", PuntoEmissione.class);
        return query.getResultList();
    }

    public static List<DistributoreAutomatico> distributoriAuto() {
        TypedQuery<DistributoreAutomatico> query = em.createQuery("SELECT d FROM DistributoreAutomatico d", DistributoreAutomatico.class);
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

        List<Biglietto> resultList = query.getResultList();

        boolean ciSonoBigliettiNonTimbrati = resultList.stream().anyMatch(biglietto -> !biglietto.getTimbrato());

        if (resultList.isEmpty() || !ciSonoBigliettiNonTimbrati) {
            System.out.println("Non ci sono Biglietti su questa tessera!");
            System.out.println("Compra dei biglietti!");
            sceltaPuntoEmissioneEOperazione(tessera);
        }
        return resultList;
    }

    //  query mezzi x tratta
    public static List<Mezzo> mezzi(Tratta tratta) {
        TypedQuery<Mezzo> query = em.createQuery("SELECT m FROM Mezzo m WHERE m.idTratta = :tratta", Mezzo.class);
        query.setParameter("tratta", tratta);
        System.out.println("Mezzi disponibili per questa tratta: " + tratta.getNome());
        return query.getResultList();
    }

    // gestione Applicazione
    public static void inizioGestione() {
        System.out.println("-- Benvenuto --");
        System.out.println("Segui le istruzioni per proseguire: ");
        System.out.println("Premi 1 se sei un amministratore");
        System.out.println("Premi 2 se sei un utente");
        System.out.println("Premi 0 per uscire");

    }

    //gestione errori ok
    public static int gestioneInputScanner() {
        while (true) {
            try {
                int num = Integer.parseInt(scanner.nextLine());

                if (num >= 0 && num <= 2) {
                    return num;
                } else {
                    System.out.println("Scelta non valida. Inserire un numero tra 0 e 2.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserire un numero intero tra 0 e 2.");
            } catch (Exception e) {
                System.err.println("Errore imprevisto: " + e.getMessage());
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
                    break; // Esce dal ciclo dopo l'accesso riuscito
                } else {
                    System.out.println("Password ERRATA. Accesso negato.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserire un numero intero valido.");
            } catch (InputErratoExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void scegliOpzioneAdmin() {
        while (true) {
            try {
                System.out.println("\n--> Scegli un'opzione per proseguire: ");
                System.out.println("Premi 1 per Trovare abbonamenti venduti per ogni punto vendita");
                System.out.println("Premi 2 per Trovare biglietti venduti per ogni punto vendita");
                System.out.println("Premi 3 per Trovare abbonamenti venduti in un certo periodo");
                System.out.println("Premi 4 per Trovare biglietti venduti in un certo periodo");
                System.out.println("Premi 5 per Controllare tipo di validità dell'abbonamento");
                System.out.println("Premi 6 per Controllare lo stato di attività dei mezzi");
                System.out.println("Premi 7 per Trovare i biglietti timbrati in un certo periodo");
                System.out.println("Premi 8 per Trovare i biglietti timbrati in un certo mezzo");
                System.out.println("Premi 9 per Trovare tutti i giri fatti da un mezzo");
                System.out.println("Premi 10 per Trovare il tempo effettivo di percorrenza di una tratta da un determinato mezzo");
                System.out.println("Premi 11 per Trovare il tempo medio di percorrenza della tratta scelta");
                System.out.println("Premi 12 per Cambiare stato di attività distributori automatici. ATTIVO/FUORI SERVIZIO");
                System.out.println("Premi 13 per Creare una nuova tratta");
                System.out.println("Premi 14 per Creare un nuovo mezzo");
                System.out.println("Premi 15 per Cambiare stato del mezzo. SERVIZIO/MANUTENZIONE");
                System.out.println("Premi 16 per Creare un nuovo giro-tratta");
                System.out.println("Premi 17 per Creare un nuovo distributore automatico");
                System.out.println("Premi 18 per Creare un nuovo rivenditore autorizzato");
                System.out.println("Premi 0 per USCIRE");

                int sceltaAdmin = Integer.parseInt(scanner.nextLine());

                if (sceltaAdmin == 0) {
                    System.out.println("Uscita dal menu amministratore.");
                    break; // Esce dal ciclo e quindi termina il metodo
                }

                switch (sceltaAdmin) {
                    case 1:
                        scegliPuntoVenditaTrovaAbbon();
                        break;
                    case 2:
                        scegliPuntoVenditaTrovaBiglietti();
                        break;
                    case 3:
                        trovaAbbonamentiVendutiInPeriodo();
                        break;
                    case 4:
                        trovaBigliettiVendutiInPeriodo();
                        break;
                    case 5:
                        controllaValiditaAbbonam();
                        break;
                    case 6:
                        controlloStatoAttivitaMezzo();
                        break;
                    case 7:
                        trovaBigliettiTimbratiInPeriodo();
                        break;
                    case 8:
                        trovaBigliettiTimbratiInUnMezzo();
                        break;
                    case 9:
                        trovaGiriFattiDaUnMezzo();
                        break;
                    case 10:
                        trovaTempoEffDiUnaTratta();
                        break;
                    case 11:
                        trovaTempoMedioMezzoTratta();
                        break;
                    case 12:
                        cambiaStatoAttivitaDistributori();
                        break;
                    case 13:
                        creaNuovaTratta();
                        break;
                    case 14:
                        creaNuovoMezzo();
                        break;
                    case 15:
                        cambiaStatoMezzo();
                        break;
                    case 16:
                        creaNuovoGiroTratta();
                        break;
                    case 17:
                        creaNuovoDistributore();
                        break;
                    case 18:
                        creaRivenditore();
                        break;
                    default:
                        System.out.println("Scegli correttamente un'opzione valida per proseguire.");
                        break;
                }

                esciContinuaAdmin();

            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserire un numero intero valido.");
            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
            }
        }
    }


    public static void esciContinuaAdmin() {
        while (true) {
            try {
                System.out.println("\n--> Scegli un'opzione per proseguire: ");
                System.out.println("Premi 1 per tornare al menu principale");
                System.out.println("Premi 2 per uscire dall'applicazione");

                int sceltaAdmin = Integer.parseInt(scanner.nextLine());

                switch (sceltaAdmin) {
                    case 1:
                        scegliOpzioneAdmin(); // Torna al menu principale
                        return; // Termina il metodo per evitare un loop infinito
                    case 2:
                        System.out.println("\nArrivederci e grazie");
                        scanner.close();
                        System.exit(0); // Termina l'applicazione
                    default:
                        System.out.println("Seleziona correttamente un'opzione.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: inserisci un numero intero valido.");
            }
        }
    }


    // 1
    public static void scegliPuntoVenditaTrovaAbbon() {
        System.out.println("\n--> Scegli un Punto vendita per proseguire con l'operazione scelta: ");
        List<PuntoEmissione> puntiVendita = puntiVendita();
        for (int i = 0; i < puntiVendita.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
        }
        try {
            int sceltaPunta = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaPunta >= 0 && sceltaPunta < puntiVendita.size()) {
                PuntoEmissione puntoScelto = puntiVendita.get(sceltaPunta);
                ad.findAbbonamentiPerPuntoEmissione(puntoScelto).forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // 2
    public static void scegliPuntoVenditaTrovaBiglietti() {
        System.out.println("\n--> Scegli un Punto vendita per proseguire con l'operazione scelta: ");
        List<PuntoEmissione> puntiVendita = puntiVendita();
        for (int i = 0; i < puntiVendita.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
        }
        try {
            int sceltaPunta = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaPunta >= 0 && sceltaPunta < puntiVendita.size()) {
                PuntoEmissione puntoScelto = puntiVendita.get(sceltaPunta);
                bd.findBigliettoPerPuntoEmissione(puntoScelto).forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 3
    public static void trovaAbbonamentiVendutiInPeriodo() {
        try {
            System.out.println("\n--> Inserisci data di inizio periodo: ");
            System.out.println("ATTENZIONE: FORMATO ---> AAAA-MM-GG");
            String dataInizio = scanner.nextLine();
            LocalDate dataInizioControllo = LocalDate.parse(dataInizio);

            System.out.println("\n--> Inserisci data di fine periodo: ");
            System.out.println("ATTENZIONE: FORMATO ---> AAAA-MM-GG");
            String dataFine = scanner.nextLine();
            LocalDate dataFineControllo = LocalDate.parse(dataFine);

            ad.findAbbonamentiNelTempo(dataInizioControllo, dataFineControllo).forEach(System.out::println);

            System.out.println("\nIl numero di Abbonati del periodo richiesto è: " + ad.findNumeroAbbonamentiNelTempo(dataInizioControllo, dataFineControllo));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 4
    public static void trovaBigliettiVendutiInPeriodo() {
        try {
            System.out.println("Inserisci data di inizio periodo: ");
            System.out.println("ATTENZIONE: FORMATO ---> AAAA-MM-GG");
            String dataInizio = scanner.nextLine();
            LocalDate dataInizioControllo = LocalDate.parse(dataInizio);

            System.out.println("Inserisci data di fine periodo: ");
            System.out.println("ATTENZIONE: FORMATO ---> AAAA-MM-GG");
            String dataFine = scanner.nextLine();
            LocalDate dataFineControllo = LocalDate.parse(dataFine);

            bd.findBigliettiNelTempo(dataInizioControllo, dataFineControllo).forEach(System.out::println);

            System.out.println("\nIl numero di Biglietti venduti del periodo richiesto è: " + bd.findNumeroBigliettiNelTempo(dataInizioControllo, dataFineControllo));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 5
    public static void controllaValiditaAbbonam() {
        try {
            List<Tessera> tessere = tessere();
            System.out.println("\n--> Seleziona una Tessera per proseguire con il controllo: ");
            for (int i = 0; i < tessere.size(); i++) {
                System.out.println("Premi " + (i + 1) + " per Tessera " + tessere.get(i).getId());
            }
            try {
                int sceltaTessera = Integer.parseInt(scanner.nextLine()) - 1;
                if (sceltaTessera >= 0 && sceltaTessera < tessere.size()) {
                    Tessera tesseraScelta = tessere.get(sceltaTessera);
                    ad.findValiditaAbbonamento(tesseraScelta.getId().toString());
                } else {
                    System.out.println("Inserimento NON valido. Inserisci un numero valido.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 6
    public static void controlloStatoAttivitaMezzo() {
        List<Mezzo> mezzi = tuttiMezzi();
        System.out.println("\n--> Seleziona un Mezzo per proseguire con il controllo: ");
        for (int i = 0; i < mezzi.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per Mezzo " + mezzi.get(i).getId());
        }
        try {
            int sceltaMezzo = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaMezzo >= 0 && sceltaMezzo < mezzi.size()) {
                Mezzo mezzoScelto = mezzi.get(sceltaMezzo);
                smd.findStatiMezzo(mezzoScelto).forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // 7
    public static void trovaBigliettiTimbratiInPeriodo() {
        try {
            System.out.println("\n--> Inserisci data di inizio periodo: ");
            System.out.println("ATTENZIONE: FORMATO ---> AAAA-MM-GGTHH:MM:SS");
            String dataInizio = scanner.nextLine();
            LocalDateTime dataInizioControllo = LocalDateTime.parse(dataInizio);
            System.out.println("\n--> Inserisci data di fine periodo: ");
            System.out.println("ATTENZIONE: FORMATO ---> AAAA-MM-GGTHH:MM:SS");
            String dataFine = scanner.nextLine();
            LocalDateTime dataFineControllo = LocalDateTime.parse(dataFine);

            timbd.findTimbratureNelTempo(dataInizioControllo, dataFineControllo).forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 8
    public static void trovaBigliettiTimbratiInUnMezzo() {
        List<Mezzo> mezzi = tuttiMezzi();
        System.out.println("\n--> Seleziona il mezzo per continuare con l'operazione di controllo timbratura biglietti: ");
        for (int i = 0; i < mezzi.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per Mezzo " + mezzi.get(i).getId());
        }
        try {
            int sceltaMezzo = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaMezzo >= 0 && sceltaMezzo < mezzi.size()) {
                Mezzo mezzoScelto = mezzi.get(sceltaMezzo);
                timbd.findTimbratureDiUnMezzo(mezzoScelto.getId().toString()).forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 9
    public static void trovaGiriFattiDaUnMezzo() {
        List<Mezzo> mezzi = tuttiMezzi();
        System.out.println("\n--> Seleziona il mezzo per continuare con l'operazione di controllo dei giri fatti: ");
        for (int i = 0; i < mezzi.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per Mezzo " + mezzi.get(i).getId());
        }
        try {
            int sceltaMezzo = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaMezzo >= 0 && sceltaMezzo < mezzi.size()) {
                Mezzo mezzoScelto = mezzi.get(sceltaMezzo);
                gd.findGiriMezzo(mezzoScelto).forEach(System.out::println);

                System.out.println("\nIl numero di giri fatti dal Mezzo: ");
                gd.findNumeroGiriMezzo(mezzoScelto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 10
    public static void trovaTempoEffDiUnaTratta() {
        List<Tratta> tratte = tratte();
        System.out.println("\n--> Seleziona la tratta per calcolare il tempo effettivo di percorrenza di ogni mezzo: ");
        for (int i = 0; i < tratte.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per Tratta " + tratte.get(i).getNome());
        }
        try {
            int sceltaTratta = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaTratta >= 0 && sceltaTratta < tratte.size()) {
                Tratta trattaScelta = tratte.get(sceltaTratta);
                gd.findTempoEffettivo(trattaScelta.getId().toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // 11
    public static void trovaTempoMedioMezzoTratta() {

        List<Mezzo> mezzi = tuttiMezzi();
        System.out.println("\n--> Seleziona un Mezzo per calcolare il tempo medio effettivo: ");
        for (int i = 0; i < mezzi.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per Mezzo " + mezzi.get(i).getId());
        }
        try {
            int sceltaMezzo = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaMezzo >= 0 && sceltaMezzo < mezzi.size()) {
                Mezzo mezzoScelto = mezzi.get(sceltaMezzo);
                //Tratta idTratta = mezzoScelto.getIdTratta();
                gd.findMediaTempoEffettivo(mezzoScelto.getIdTratta().getId().toString(), mezzoScelto.getId().toString());
            } else {
                System.out.println("Errore: inserisci un numero valido.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // 12
    public static void cambiaStatoAttivitaDistributori() {
        List<DistributoreAutomatico> distributori = distributoriAuto();
        System.out.println("\nSeleziona il Distributore Automatico al quale vuoi cambiare lo stato di attività. ATTIVO/FUORI SERVIZIO");
        for (int i = 0; i < distributori.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per Distributore Automatico " + distributori.get(i).getNome() + " - " + distributori.get(i).getStato());
        }
        try {
            int sceltaDistributore = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaDistributore >= 0 && sceltaDistributore < distributori.size()) {
                DistributoreAutomatico distributoreScelto = distributori.get(sceltaDistributore);
                System.out.println("Inserisci nuovo stato di attività del distributore: ATTIVO/FUORI SERVIZIO");
                String stato = scanner.nextLine();
                StatoDistributore statoDistributore = StatoDistributore.valueOf(stato.toUpperCase());
                ped.updateStatoDistributore(distributoreScelto.getId().toString(), statoDistributore);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 13 Creare una nuova tratta
    public static void creaNuovaTratta() {
        System.out.println("\n--> Crea una nuova tratta:");
        try {
            System.out.println("inserisci il nome della nuova tratta:");
            String nomeTratta = scanner.nextLine();
            System.out.println("Inserisci luogo di partenza: ");
            String luogoPartenza = scanner.nextLine();
            System.out.println("Inserisci luogo del capolinea: ");
            String luogoCapolinea = scanner.nextLine();
            System.out.println("Inserisci il tempo previsto: ");
            Integer tempoPrevisto = Integer.parseInt(scanner.nextLine());

            Tratta tratta = new Tratta(nomeTratta, luogoPartenza, luogoCapolinea, tempoPrevisto);
            trd.save(tratta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 14 crea mezzo
    public static void creaNuovoMezzo() {
        System.out.println("\n--> Crea un nuovo mezzo: ");
        try {
            List<Tratta> tratte = tratte();
            for (int i = 0; i < tratte.size(); i++) {
                System.out.println("Premi " + (i + 1) + " per Tratta " + tratte.get(i).getNome());
            }
            int sceltaTratta = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaTratta >= 0 && sceltaTratta < tratte.size()) {
                Tratta trattaScelta = tratte.get(sceltaTratta);
                System.out.println("Inserisci Tipo di mezzo: \nTRAM/AUTOBUS");
                String tipoMezzo = scanner.nextLine();
                TipoMezzo tipoDiMezzo = TipoMezzo.valueOf(tipoMezzo.toUpperCase());
                System.out.println("Inserisci capienza del mezzo: ");
                Integer capienza = Integer.parseInt(scanner.nextLine());
                System.out.println("Inserisci stato del mezzo: \nSERVIZIO/MANUTENZIONE");
                String statoMezzo = scanner.nextLine();
                StatoDelMezzo statoDelMezzo = StatoDelMezzo.valueOf(statoMezzo.toUpperCase());
                Mezzo mezzo = new Mezzo(tipoDiMezzo, capienza, statoDelMezzo, trattaScelta);
                md.save(mezzo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 15 cambia stato mezzo
    public static void cambiaStatoMezzo() {

        try {

            List<Mezzo> mezzi = tuttiMezzi();
            for (int i = 0; i < mezzi.size(); i++) {
                System.out.println("Premi " + (i + 1) + " per Mezzo " + mezzi.get(i).getId() + " - " + mezzi.get(i).getStato());
            }
            int sceltaMezzo = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaMezzo >= 0 && sceltaMezzo < mezzi.size()) {
                Mezzo mezzoScelto = mezzi.get(sceltaMezzo);
                System.out.println("Inserisci il nuovo stato del mezzo: \nSERVIZIO/MANUTENZIONE");
                String statoDelMezzo = scanner.nextLine();
                StatoDelMezzo stato = StatoDelMezzo.valueOf(statoDelMezzo.toUpperCase());
                StatoMezzo statoMezzo = new StatoMezzo(LocalDate.now(), stato, mezzoScelto);
                smd.save(statoMezzo);
                //md.updateStatoMezzo(mezzoScelto.getId().toString(), mezzoScelto.getStato());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // 16 crea giro tratta
    public static void creaNuovoGiroTratta() {
        try {

            System.out.println("Seleziona il mezzo che vuoi far girare: ");
            List<Mezzo> mezzi = tuttiMezzi();
            for (int i = 0; i < mezzi.size(); i++) {
                System.out.println("Premi " + (i + 1) + " per Mezzo " + mezzi.get(i).getId());
            }
            int sceltaMezzo = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaMezzo >= 0 && sceltaMezzo < mezzi.size()) {
                Mezzo mezzoScelto = mezzi.get(sceltaMezzo);
                System.out.println("Inserisci DATA e ORA della partenza:");
                System.out.println("ATTENZIONE: FORMATO --> AAAA/MM/GGTHH:MM:SS");
                String dataPartenza = scanner.nextLine();
                LocalDateTime dataPartenzaGiro = LocalDateTime.parse(dataPartenza);
                System.out.println("Inserisci DATA e ORA della arrivo:");
                System.out.println("ATTENZIONE: FORMATO --> AAAA/MM/GGTHH:MM:SS");
                String dataArrivo = scanner.nextLine();
                LocalDateTime dataArrivoGiro = LocalDateTime.parse(dataArrivo);
                GiroTratta giroTratta = new GiroTratta(mezzoScelto, mezzoScelto.getIdTratta(), dataPartenzaGiro, dataArrivoGiro);
                gd.save(giroTratta);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 17 crea distributore
    public static void creaNuovoDistributore() {
        System.out.println("Crea un nuovo distributore automatico: ");
        try {
            System.out.println("Inserisci un nome: ");
            String nome = scanner.nextLine();
            System.out.println("Inserisci un luogo: ");
            String luogo = scanner.nextLine();
            System.out.println("Inserisci lo stato di attività del distributore: \nATTIVO/FUORI SERVIZIO");
            String stato = scanner.nextLine();
            StatoDistributore statoDistributore = StatoDistributore.valueOf(stato.toUpperCase());
            DistributoreAutomatico distributoreAutomatico = new DistributoreAutomatico(nome, luogo, statoDistributore);
            ped.save(distributoreAutomatico);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 18 crea rivenditore
    public static void creaRivenditore() {
        System.out.println("Crea un nuovo rivenditore autorizzato: ");
        try {
            System.out.println("Inserisci un nome: ");
            String nome = scanner.nextLine();
            System.out.println("Inserisci un luogo: ");
            String luogo = scanner.nextLine();
            System.out.println("Inserisci il tipo di rivenditore: \nTABACCAIO/EDICOLA");
            String tipo = scanner.nextLine();
            TipoRivenditore tipoRivenditore = TipoRivenditore.valueOf(tipo.toUpperCase());
            RivenditoreAutorizzato rivenditoreAutorizzato = new RivenditoreAutorizzato(nome, luogo, tipoRivenditore);
            ped.save(rivenditoreAutorizzato);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // gestione utente
    //gestione errori ok
    public static void gestioneUtente() {
        while (true) {
            try {
                System.out.println("Premi 1 per ACCEDERE e PROCEDI all'acquisto.");
                System.out.println("Premi 2 per REGISTRARTI.");
                System.out.println("Premi 0 per TORNARE al menu principale.");
                int sceltaUtente = Integer.parseInt(scanner.nextLine());

                switch (sceltaUtente) {
                    case 1:
                        gestioneUtenteRegistrato();
                        break;
                    case 2:
                        Tessera tesseraUtente = gestioneNuovoUtente();
                        break;
                    case 0:
                        System.out.println("Tornando al menu principale...");
                        return; // Esce dal metodo e torna al menu principale
                    default:
                        System.out.println("Inserire un numero intero tra 0 e 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserire un numero intero tra 0 e 2.");
            } catch (Exception e) {
                System.err.println("Errore imprevisto: " + e.getMessage());
            }
        }
    }

    //gestione errori ok
    public static void gestioneUtenteRegistrato() {
        while (true) {
            try {
                UUID tesseraID = null;
                // Richiede l'ID tessera finché non viene inserito un valore valido
                while (tesseraID == null) {

                    try {
                        System.out.println("Inserisci il tuo ID Tessera: ");
                        String inputTesseraID = scanner.nextLine().trim();
                        tesseraID = UUID.fromString(inputTesseraID);
                    } catch (IllegalArgumentException e) {
                        System.err.println("ID Tessera non valido. Assicurati di inserire un UUID corretto.");
                    }
                }

                // Ricerca della tessera
                Tessera tessera = td.findById(tesseraID.toString());
                if (tessera == null) {
                    System.out.println("Tessera non trovata. Verifica l'ID inserito.");
                    continue; // Torna a richiedere l'ID tessera
                }

                // Scelta dell'operazione da eseguire
                while (true) {
                    System.out.println("Quale operazione vuoi eseguire? ");
                    System.out.println("Premi 1 per fare un viaggio. ");
                    System.out.println("Premi 2 per comprare biglietti o abbonamenti.");

                    String operazioneScelta = scanner.nextLine().trim();
                    if (operazioneScelta.equals("1")) {
                        scegliTratta(tessera);
                        break; // Esce dal ciclo dopo aver scelto l'operazione
                    } else if (operazioneScelta.equals("2")) {
                        sceltaPuntoEmissioneEOperazione(tessera);
                        break; // Esce dal ciclo dopo aver scelto l'operazione
                    } else {
                        System.out.println("Scelta NON valida. Inserisci un numero tra 1 e 2.");
                    }
                }

            } catch (Exception e) {
                System.err.println("Si è verificato un errore: " + e.getMessage());
            }
            break; // Esce dal ciclo principale una volta completata l'operazione
        }
    }

    //gestione errori ok
    public static void sceltaPuntoEmissioneEOperazione(Tessera tessera) {
        if (tessera != null) {
            List<PuntoEmissione> puntiVendita = puntiVendita();
            System.out.println("\n--> Scegli un punto vendita.");
            for (int i = 0; i < puntiVendita.size(); i++) {
                System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
            }

            PuntoEmissione puntoScelto = null;
            while (puntoScelto == null) {
                try {
                    int sceltaPuntoEmis = Integer.parseInt(scanner.nextLine()) - 1;
                    if (sceltaPuntoEmis >= 0 && sceltaPuntoEmis < puntiVendita.size()) {
                        puntoScelto = puntiVendita.get(sceltaPuntoEmis);
                        System.out.println("Hai scelto il punto vendita " + puntoScelto.getNome());
                    } else {
                        System.out.println("Scelta NON valida. Inserire un numero tra 1 e " + puntiVendita.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero valido.");
                }
            }

            int scelta = -1;
            while (scelta != 1 && scelta != 2) {

                try {
                    System.out.println("\n--> Scegli un'opzione per proseguire.");
                    System.out.println("Premi 1 se vuoi comprare uno o più biglietti");
                    System.out.println("Premi 2 se vuoi comprare un abbonamento");
                    scelta = Integer.parseInt(scanner.nextLine());
                    switch (scelta) {
                        case 1:
                            acquistoBiglietti(tessera, puntoScelto);
                            break;
                        case 2:
                            acquistaAbbonamento(tessera, puntoScelto);
                            break;
                        default:
                            System.out.println("Scelta NON valida. Inserire un numero intero tra 1 e 2.");
                            scelta = -1; // Reset scelta per rimanere nel ciclo
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero valido.");
                }
            }

        } else {
            System.out.println("Tessera NON trovata.");
        }
    }

    //gestione errori ok
    public static void acquistoBiglietti(Tessera tesseraID, PuntoEmissione puntoScelto) {

        try {
            int num = 0;
            while (num <= 0) {
                try {
                    System.out.println("Quanti biglietti vuoi comprare?");
                    num = Integer.parseInt(scanner.nextLine());
                    if (num <= 0) {
                        System.err.println("Il numero di biglietti deve essere un intero positivo.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Input NON valido. Inserire un numero intero.");
                }
            }

            for (int i = 0; i < num; i++) {
                Biglietto biglietto = new Biglietto(LocalDate.now(), 90, false, tesseraID, puntoScelto);
                bd.save(biglietto);
            }

            Tratta trattaScelta = scegliTratta(tesseraID);

        } catch (InputErratoExceptions e) {
            System.err.println(e.getMessage());
        }


    }

    //gestione errori ok

    public static void acquistaAbbonamento(Tessera tesseraID, PuntoEmissione puntoScelto) {
        while (true) {
            try {
                System.out.println("Creazione Abbonamento in corso...");

                // Gestione della durata dell'abbonamento
                Durata durata = null;
                while (durata == null) {
                    try {
                        System.out.println("Scegli la durata dell'abbonamento: ");
                        System.out.println("SETTIMANALE");
                        System.out.println("MENSILE");
                        System.out.println("ANNUALE");
                        String durataAbbonamento = scanner.nextLine().trim().toUpperCase();
                        durata = Durata.valueOf(durataAbbonamento);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Durata non valida. Inserisci una delle opzioni: SETTIMANALE, MENSILE, ANNUALE.");
                    }
                }

                // Gestione della data di emissione
                LocalDate dataEmissione = null;
                while (dataEmissione == null) {
                    try {
                        System.out.println("Scegli la data di emissione (formato AAAA-MM-GG): ");
                        String dataEmiss = scanner.nextLine().trim();
                        dataEmissione = LocalDate.parse(dataEmiss);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato data non valido. Inserisci la data nel formato AAAA-MM-GG.");
                    }
                }

                // Creazione dell'abbonamento
                Abbonamento abbonamento = new Abbonamento(dataEmissione, durata, tesseraID, puntoScelto);
                ad.save(abbonamento);

                // Scelta della tratta
                scegliTratta(tesseraID);

            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
            }
            break; // Esce dal ciclo dopo l'esecuzione
        }
    }

    //gestione errori ok
    public static Tratta scegliTratta(Tessera tesseraID) {

        Tratta trattoScelto = null;

        while (true) {
            try {
                System.out.println("\n--> Scegli la tratta: ");
                List<Tratta> tratte = tratte();
                for (int i = 0; i < tratte.size(); i++) {
                    System.out.println("Premi " + (i + 1) + " per " + tratte.get(i).getNome());
                }

                // Scegli tratta
                int scegliTratta = Integer.parseInt(scanner.nextLine()) - 1;
                if (scegliTratta >= 0 && scegliTratta < tratte.size()) {
                    trattoScelto = tratte.get(scegliTratta);
                    System.out.println("Hai scelto la tratta " + trattoScelto.getNome() + " \n");
                    Mezzo mezzoScelto = scegliMezzo(trattoScelto);

                    bigliettoAbbonamento(mezzoScelto, tesseraID);

                    break; // Esci dal ciclo dopo una selezione valida
                } else {
                    System.err.println("Scelta NON valida. Inserire un numero tra 1 e " + tratte.size());
                }

            } catch (NumberFormatException e) {
                System.err.println("Input non valido. Inserire un numero intero.");
            } catch (Exception e) {
                System.err.println("Errore durante la scelta della tratta: " + e.getMessage());
            }
        }

        return trattoScelto;
    }

    //gestione errori ok
    public static Mezzo scegliMezzo(Tratta tratta) {
        Mezzo mezzoScelto = null;

        while (mezzoScelto == null) {
            try {
                System.out.println("\n--> Scegli il mezzo per questa tratta: " + tratta.getNome());
                List<Mezzo> mezzi = mezzi(tratta);

                // Controlla se ci sono mezzi disponibili
                if (mezzi.isEmpty()) {
                    System.out.println("Nessun mezzo disponibile per questa tratta.");
                    return null; // Torna se non ci sono mezzi disponibili
                }

                // Mostra le opzioni di mezzo
                for (int i = 0; i < mezzi.size(); i++) {
                    System.out.println("Premi " + (i + 1) + " per " + mezzi.get(i).getTipo());
                }

                // Scegli mezzo
                int sceltaMezzo = Integer.parseInt(scanner.nextLine()) - 1;
                if (sceltaMezzo >= 0 && sceltaMezzo < mezzi.size()) {
                    mezzoScelto = mezzi.get(sceltaMezzo);
                    System.out.println("Hai scelto il mezzo: " + mezzoScelto.getTipo());
                    break; // Esci dal ciclo dopo una scelta valida
                } else {
                    System.out.println("Scelta NON valida. Inserisci un numero tra 1 e " + mezzi.size() + ".");
                }

            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserire un numero intero.");
            } catch (Exception e) {
                System.out.println("Errore durante la selezione del mezzo: " + e.getMessage());
            }
        }

        return mezzoScelto;
    }


    //gestione errori ok

    public static Tessera gestioneNuovoUtente() {
        while (true) {
            try {
                System.out.println("Inserisci il tuo Nome: ");
                String nomeUtente = scanner.nextLine().trim();
                if (nomeUtente.isEmpty()) {
                    System.out.println("Il nome non può essere vuoto. Riprova.");
                    continue;  // Torna all'inizio del ciclo per riprovare
                }

                System.out.println("Inserisci il tuo Cognome: ");
                String cognomeUtente = scanner.nextLine().trim();
                if (cognomeUtente.isEmpty()) {
                    System.out.println("Il cognome non può essere vuoto. Riprova.");
                    continue;  // Torna all'inizio del ciclo per riprovare
                }

                Utente utente = new Utente(nomeUtente, cognomeUtente);
                ud.save(utente);
                System.out.println("Utente creato correttamente.");

                Tessera tesseraUtente = new Tessera(utente, LocalDate.now());
                td.save(tesseraUtente);
                System.out.println("Tessera creata correttamente: " + tesseraUtente);

                gestioneUtenteRegistrato();

                tesseraUtenteLoggato = td.findById(tesseraUtente.getId().toString());
                return tesseraUtenteLoggato;  // Ritorna la tessera dopo averla trovata

            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
                System.out.println("Riprova a inserire i dati.");
            }
        }
    }


    //gestione errori ok
    public static Biglietto scegliBiglietto(Tessera tesseraID) {
        Biglietto bigliettoScelto = null;
        while (true) {
            try {
                System.out.println("\n--> Scegli il biglietto che vuoi timbrare: ");
                for (int i = 0; i < bigliettiUtente(tesseraID).size(); i++) {
                    System.out.println("Premi " + (i + 1) + " per " + bigliettiUtente(tesseraID).get(i).getId() + " -  biglietto già timbrato? " + bigliettiUtente(tesseraID).get(i).getTimbrato());
                }
                // scegli biglietto
                int scegliBiglietto = Integer.parseInt(scanner.nextLine()) - 1;
                if (scegliBiglietto >= 0 && scegliBiglietto < bigliettiUtente(tesseraID).size()) {
                    bigliettoScelto = bigliettiUtente(tesseraID).get(scegliBiglietto);
                    System.out.println("Hai scelto il biglietto " + bigliettoScelto.getId());
                    break;
                } else {
                    System.err.println("Scelta NON valida. Riprova.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input non valido. Inserire un numero intero.");
            } catch (Exception e) {
                System.err.println("Errore durante la scelta del biglietto: " + e.getMessage());
            }
        }

        return bigliettoScelto;
    }

    //gestione errori ok
    public static void timbraBiglietto(Mezzo mezzo, Biglietto biglietto) {
        boolean timbratoCorrettamente = false;

        while (!timbratoCorrettamente) {
            try {
                if (biglietto.getTimbrato()) {
                    // throw new Error("Il biglietto scelto è gia stato timbrato!") ;
                    System.err.println("Il biglietto scelto è gia stato timbrato!");

                    System.out.println("Scegli un altro biglietto non timbrato!");
                    biglietto = scegliBiglietto(biglietto.getIdTessera());  // Cambia biglietto
                } else {
                    Timbratura timbratura = new Timbratura(LocalDateTime.now(), mezzo, biglietto);
                    timbd.save(timbratura);
                    esciContinua(biglietto.getIdTessera());
                    timbratoCorrettamente = true;  // Uscire dal ciclo dopo una timbratura riuscita
                }


            } catch (Exception e) {
                System.err.println("Errore durante la timbratura: " + e.getMessage());
                System.out.println("Vuoi riprovare con un altro biglietto? (s/n)");

                String risposta = scanner.nextLine().trim().toLowerCase();
                if (risposta.equals("s")) {
                    biglietto = scegliBiglietto(biglietto.getIdTessera());  // Cambia biglietto
                } else {
                    System.err.println("Operazione annullata. Il biglietto non è stato timbrato.");
                    break;  // Esce dal ciclo senza riprovare
                }
            }
        }
    }

    //gestione errori ok
    public static void esciContinua(Tessera tesseraID) {
        boolean sceltaValida = false;

        while (!sceltaValida) {
            try {
                System.out.println("\n--> Scegli opzione:");
                System.out.println("Premi 0 per USCIRE");
                System.out.println("Premi 1 se vuoi continuare ad acquistare.");
                int sceltaOpzione = Integer.parseInt(scanner.nextLine());

                switch (sceltaOpzione) {
                    case 1:
                        sceltaPuntoEmissioneEOperazione(tesseraID);
                        sceltaValida = true; // Esci dal ciclo dopo una scelta valida
                        break;
                    case 0:
                        System.out.println("\nGrazie per averci scelto! \nA presto!");
                        scanner.close();
                        System.exit(0); // Termina l'applicazione
                        break;
                    default:
                        System.err.println("Opzione NON valida. Inserisci 0 per uscire o 1 per continuare.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Input non valido. Inserire un numero intero.");
            } catch (Exception e) {
                System.err.println("Si è verificato un errore: " + e.getMessage());
            }
        }
    }

    //gestione errori ok
    public static void bigliettoAbbonamento(Mezzo mezzoScelto, Tessera tesseraID) {
        while (true) {
            try {
                int scelta = -1;
                while (scelta < 0 || scelta > 1) {
                    System.out.println("Premi 1 per timbrare il biglietto");
                    System.out.println("Premi 2 per usare un abbonamento");

                    try {
                        scelta = Integer.parseInt(scanner.nextLine()) - 1;
                        if (scelta < 0 || scelta > 1) {
                            System.err.println("Scelta NON valida. Inserire un numero intero tra 1 e 2.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Input non valido. Inserire un numero intero.");
                    }
                }

                switch (scelta) {
                    case 0: // Corrisponde a "Premi 1 per timbrare il biglietto"
                        Biglietto bigliettoScelto = scegliBiglietto(tesseraID);
                        timbraBiglietto(mezzoScelto, bigliettoScelto);
                        return; // Esce dal ciclo dopo aver timbrato il biglietto

                    case 1: // Corrisponde a "Premi 2 per usare un abbonamento"
                        List<Abbonamento> abbonamentiTessera = ad.findAbbonamentiTessera(tesseraID.getId().toString());

                        if (abbonamentiTessera.isEmpty()) {
                            System.out.println("Nessun abbonamento disponibile. Premi 1 per comprare un nuovo abbonamento o un biglietto.");
                            int opzioneScelta = -1;
                            while (opzioneScelta < 0 || opzioneScelta > 1) {
                                try {
                                    opzioneScelta = Integer.parseInt(scanner.nextLine()) - 1;
                                    if (opzioneScelta < 0 || opzioneScelta > 1) {
                                        System.err.println("Scelta NON valida. Inserire 1 per comprare un nuovo abbonamento o biglietto.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.err.println("Input non valido. Inserire un numero intero.");
                                }
                            }
                            if (opzioneScelta == 0) {
                                sceltaPuntoEmissioneEOperazione(tesseraID);
                                return; // Esce dal ciclo dopo aver acquistato un nuovo abbonamento o biglietto
                            }
                            continue;
                        }

                        for (int i = 0; i < abbonamentiTessera.size(); i++) {
                            System.out.println("Premi " + (i + 1) + " per " + abbonamentiTessera.get(i).getId() + " con scadenza il " + abbonamentiTessera.get(i).getDataScadenza());
                        }

                        int nrAbbonamentoScelto = -1;
                        while (nrAbbonamentoScelto < 0 || nrAbbonamentoScelto >= abbonamentiTessera.size()) {
                            try {
                                nrAbbonamentoScelto = Integer.parseInt(scanner.nextLine()) - 1;
                                if (nrAbbonamentoScelto < 0 || nrAbbonamentoScelto >= abbonamentiTessera.size()) {
                                    System.err.println("Scelta NON valida. Inserire un numero tra 1 e " + abbonamentiTessera.size());
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Input non valido. Inserire un numero intero.");
                            }
                        }

                        Abbonamento abbonamentoScelto = abbonamentiTessera.get(nrAbbonamentoScelto);

                        if (abbonamentoScelto.getDataScadenza().isAfter(LocalDate.now())) {
                            System.out.println("L'abbonamento scelto è ancora valido! Buon viaggio!");
                            esciContinua(tesseraID);
                           // return; // Esce dal ciclo dopo aver scelto un abbonamento valido
                        } else {
                            System.out.println("L'abbonamento scelto è scaduto il " + abbonamentoScelto.getDataScadenza());
                            System.out.println("Scegli un altro abbonamento oppure comprane uno nuovo!");

                            int opzioneScelta = -1;
                            while (opzioneScelta < 0 || opzioneScelta > 1) {
                                System.out.println("Premi 1 per scegliere un altro abbonamento");
                                System.out.println("Premi 2 per comprare un nuovo abbonamento o un biglietto");

                                try {
                                    opzioneScelta = Integer.parseInt(scanner.nextLine()) - 1;
                                    if (opzioneScelta < 0 || opzioneScelta > 1) {
                                        System.err.println("Scelta NON valida. Inserire 1 o 2.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.err.println("Input non valido. Inserire un numero intero.");
                                }
                            }

                            if (opzioneScelta == 0) {
                                continue; // Ritorna al ciclo principale per scegliere un altro abbonamento
                            } else if (opzioneScelta == 1) {
                                sceltaPuntoEmissioneEOperazione(tesseraID);
                                return; // Esce dal ciclo dopo aver acquistato un nuovo abbonamento o biglietto
                            }
                        }
                        break;

                    default:
                        System.err.println("Scelta NON valida. Inserire un numero intero tra 1 e 2.");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Si è verificato un errore: " + e.getMessage());
            }
        }
    }

}
