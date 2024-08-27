package brianpelinku;

import brianpelinku.entities.Mezzo;
import brianpelinku.entities.StatoMezzo;
import brianpelinku.entities.Tratta;
import brianpelinku.enums.Stato;

import java.util.Date;

public class ApplicationTest {
    public static void main(String[] args) {

        // creazione tratta
        Tratta tratta1 = new Tratta("Linea 1", "Stazione Centrale", "Piazza del Duomo", 30);

        // creazione stato mezzo usando l'enum Stato
        StatoMezzo statoMezzo1 = new StatoMezzo(Stato.IN_SERVIZIO, new Date());

        // creazione di un mezzo associato alla tratta
        Mezzo mezzo1 = new Mezzo(100, tratta1);

        System.out.println(mezzo1);
        System.out.println(statoMezzo1);
    }
}
