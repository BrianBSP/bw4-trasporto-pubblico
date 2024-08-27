package brianpelinku.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente idUtente;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza_validita", nullable = false)
    private LocalDate dataScadenzaValidita;

    @OneToMany(mappedBy = "idTessera")
    private Set<Abbonamento> nrAbbonamenti;

    @OneToMany(mappedBy = "idTessera")
    private Set<Biglietto> nrBiglietti;


    public Tessera(){}

    public Tessera(Utente idUtente, LocalDate dataEmissione ) {
        this.idUtente = idUtente;
        this.dataEmissione = dataEmissione;
        this.dataScadenzaValidita = dataEmissione.plusYears(1);
    }

    public UUID getId() {
        return id;
    }

    public Utente getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Utente idUtente) {
        this.idUtente = idUtente;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenzaValidita() {
        return dataScadenzaValidita;
    }

    public void setDataScadenzaValidita(LocalDate dataScadenzaValidita) {
        this.dataScadenzaValidita = dataScadenzaValidita;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", idUtente=" + idUtente +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenzaValidita=" + dataScadenzaValidita +
                '}';
    }
}
