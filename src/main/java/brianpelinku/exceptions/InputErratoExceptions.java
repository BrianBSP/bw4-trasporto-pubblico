package brianpelinku.exceptions;

import java.util.InputMismatchException;

public class InputErratoExceptions extends InputMismatchException {
    public InputErratoExceptions() {
        super("Inserimento non valido. Inserisci un numero intero compreso tra 0 e 2.");
    }
}
