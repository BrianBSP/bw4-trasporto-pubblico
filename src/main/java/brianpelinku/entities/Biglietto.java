package brianpelinku.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietto")
public class Biglietto {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "validita", nullable = false)
    private Integer validita;

    @Column(name = "timbrato", nullable = false)
    private Boolean timbrato;

    @ManyToOne
    @JoinColumn(name = "id_tessera")
    private Tessera idTessera;

    @ManyToOne
    @JoinColumn(name = "id_punto_emissione")
    private PuntoEmissione idPuntoEmissione;

    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, Integer validita, Boolean timbrato, Tessera idTessera, PuntoEmissione idPuntoEmissione) {
        this.dataEmissione = dataEmissione;
        this.validita = validita;
        this.timbrato = timbrato;
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

    public Integer getValidita() {
        return validita;
    }

    public void setValidita(Integer validita) {
        this.validita = validita;
    }

    public Boolean getTimbrato() {
        return timbrato;
    }

    public void setTimbrato(Boolean timbrato) {
        this.timbrato = timbrato;
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
        return "Biglietto{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                ", validita=" + validita +
                ", timbrato=" + timbrato +
                ", idTessera=" + idTessera.getId() +
                ", idPuntoEmissione=" + idPuntoEmissione.getNome() +
                '}';
    }
}
