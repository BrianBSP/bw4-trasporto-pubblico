package brianpelinku.entities;


import brianpelinku.ENUMS.StatoDelMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;

    public StatoMezzo() {
    }

    public StatoMezzo(LocalDate dataInizio, StatoDelMezzo statoMezzo, Mezzo mezzo) {
        this.dataInizio = dataInizio;
        this.statoMezzo = statoMezzo;
        this.mezzo = mezzo;
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

//    @Override
//    public String toString() {
//        return "StatoMezzo{" +
//                "id=" + id +
//                ", dataInizio=" + dataInizio +
//                ", statoMezzo=" + statoMezzo +
//                ", mezzo=" + mezzo.getTipo() + ", capienza=" + mezzo.getCapienza() + ", IdTratta=" + mezzo.getIdTratta() +
//                '}';
//    }

    @Override
    public String toString() {
        return "StatoMezzo{" +
                "id=" + id +
                ", dataInizio=" + dataInizio +
                ", statoMezzo=" + statoMezzo +
                ", mezzo=" + mezzo.getTipo() + ", IdMezzo=" + mezzo.getId() +
                '}';
    }
}
