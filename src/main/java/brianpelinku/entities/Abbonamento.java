package brianpelinku.entities;

import brianpelinku.ENUMS.Durata;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamento")
public class Abbonamento {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "durata", nullable = false)
    @Enumerated(EnumType.STRING)
    private Durata durata;

    @ManyToOne
    @JoinColumn(name = "id_tessera")
    private Tessera idTessera;

    @ManyToOne
    @JoinColumn(name = "id_punto_emissione")
    private PuntoEmissione idPuntoEmissione;

    public Abbonamento() {
    }

    public Abbonamento(LocalDate dataEmissione, Durata durata, Tessera idTessera, PuntoEmissione idPuntoEmissione) {
        this.dataEmissione = dataEmissione;
        this.durata = durata;
        this.idTessera = idTessera;
        this.idPuntoEmissione = idPuntoEmissione;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public Durata getDurata() {
        return durata;
    }

    public void setDurata(Durata durata) {
        this.durata = durata;
    }

    public Tessera getIdTessera() {
        return idTessera;
    }

    public void setIdTessera(Tessera idTessera) {
        this.idTessera = idTessera;
    }

    public PuntoEmissione getIdPuntoEmissione() {
        return idPuntoEmissione;
    }

    public void setIdPuntoEmissione(PuntoEmissione idPuntoEmissione) {
        this.idPuntoEmissione = idPuntoEmissione;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                ", durata=" + durata +
                ", idTessera=" + idTessera.getId() +
                ", PuntoEmissione=" + idPuntoEmissione.getNome() +
                '}';
    }
}
