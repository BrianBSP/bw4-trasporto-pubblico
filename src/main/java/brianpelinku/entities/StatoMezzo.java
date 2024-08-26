package brianpelinku.entities;


import brianpelinku.ENUM.StatoDelMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "stato-mezzo")

public class StatoMezzo {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "stato_mezzo", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoDelMezzo statoMezzo;

    @OneToOne(mappedBy = "idStato")
    private Mezzo mezzo;

    public StatoMezzo(){}

    public StatoMezzo(LocalDate dataInizio, StatoDelMezzo statoMezzo) {
        this.dataInizio = dataInizio;
        this.statoMezzo = statoMezzo;

    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public StatoDelMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoDelMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "StatoMezzo{" +
                "id=" + id +
                ", dataInizio=" + dataInizio +
                ", statoMezzo=" + statoMezzo +
                ", mezzo=" + mezzo.getTipo() + ", capienza=" + mezzo.getCapienza() + ", IdStato=" + mezzo.getIdStato() + ", IdTratta=" + mezzo.getIdTratta() +
                '}';
    }
}
