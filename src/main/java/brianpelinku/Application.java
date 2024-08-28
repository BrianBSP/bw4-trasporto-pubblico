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

    public static void main(String[] args) {
        //EntityManager em = emf.createEntityManager();

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
                if (num >= 0 || num <= 2) {
                    return num;
                } else {
                    System.out.println("Scelta non valida. Inserire un numero tra 0 e 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }

        }
    }

    public static void gestioneAmministratore() {
        while (true) {
            try {
                System.out.println("Inserisci la password: ");
                int password = Integer.parseInt(scanner.nextLine());
                if (password == 1234) {
                    System.out.println("Password CORRETTA. Accesso consentito.");
                } else {
                    System.out.println("Password ERRATA. Accesso negato.");
                }
            } catch (InputErratoExceptions e) {
                System.out.println(e.getMessage());
            }
            break;
        }
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
                    gestioneNuovoUtente();

                    break;
                default:
                    System.out.println("Inserire un numero intero tra 1 e 2.");
                    break;
            }
            break;

        }
    }

    public static void gestioneNuovoUtente() {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        }
    }

    public static List<PuntoEmissione> puntiVendita() {
        TypedQuery<PuntoEmissione> query = em.createQuery("SELECT p FROM PuntoEmissione p", PuntoEmissione.class);
        return query.getResultList();
    }

    public static void gestioneUtenteRegistrato() {
        while (true) {
            try {
                System.out.println("Inserisci il tuo ID Tessera: ");
                UUID tesseraID = UUID.fromString(scanner.nextLine());
                Tessera tessera = td.findById(String.valueOf(tesseraID));
                if (tessera != null) {
                    List<PuntoEmissione> puntiVendita = puntiVendita();
                    //puntiVendita.forEach(System.out::println);
                    for (int i = 0; i < puntiVendita.size(); i++) {
                        //puntiVendita.forEach(System.out::println);
                        System.out.println("Premi " + (i + 1) + " per " + puntiVendita.get(i).getNome());
                    }

                    try {
                        int sceltaPuntoEmis = Integer.parseInt(scanner.nextLine()) - 1;
                        if (sceltaPuntoEmis >= 0 && sceltaPuntoEmis < puntiVendita.size()) {
                            PuntoEmissione puntoScelto = puntiVendita.get(sceltaPuntoEmis);
                            System.out.println("Hai scelto il punto vendita " + puntoScelto.getNome());
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
                                    acqiustaAbbonamento(tessera, puntoScelto);
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
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input NON valido. Inserire un numero intero.");
                scanner.nextLine();
            } catch (InputErratoExceptions e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public static void acqiustaAbbonamento(Tessera tesseraID, PuntoEmissione puntoScelto) {
        while (true) {
            try {
                System.out.println("Creazione Abbonamento in corso...");
                System.out.println("Scegli la durata dell'abbonamento: ");
                System.out.println("SETTIMANALE");
                System.out.println("MENSILE");
                System.out.println("ANNUALE");
                String durataAbbonamento = scanner.nextLine();
                Durata durata = Durata.valueOf(durataAbbonamento.toUpperCase());
                Abbonamento abbonamento = new Abbonamento(LocalDate.now(), durata, tesseraID, puntoScelto);
                ad.save(abbonamento);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
            break;
        }

    }


}
