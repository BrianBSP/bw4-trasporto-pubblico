package brianpelinku.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "timbratura")
public class Timbratura {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "data_timbro", nullable = false)
    private LocalDateTime dataTimbro;

    @Column(name = "data_scadenza_timbro", nullable = false)
    private LocalDateTime dataScadenzaTimbro;


    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo idMezzo;

    @OneToOne
    @JoinColumn(name = "id_biglietto")
    private Biglietto idBiglietto;

    public Timbratura(){}

    public Timbratura(LocalDateTime dataTimbro, Mezzo idMezzo, Biglietto idBiglietto) {
        this.dataTimbro = dataTimbro;
        this.dataScadenzaTimbro = dataTimbro.plusMinutes(idBiglietto.getValidita()) ;
        this.idMezzo = idMezzo;
        this.idBiglietto = idBiglietto;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDataTimbro() {
        return dataTimbro;
    }

    public void setDataTimbro(LocalDateTime dataTimbro) {
        this.dataTimbro = dataTimbro;
    }

    public Mezzo getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Mezzo idMezzo) {
        this.idMezzo = idMezzo;
    }

    public LocalDateTime getDataScadenzaTimbro() {
        return dataScadenzaTimbro;
    }

    public void setDataScadenzaTimbro(LocalDateTime dataScadenzaTimbro) {
        this.dataScadenzaTimbro = dataScadenzaTimbro;
    }

    public Biglietto getIdBiglietto() {
        return idBiglietto;
    }

    public void setIdBiglietto(Biglietto idBiglietto) {
        this.idBiglietto = idBiglietto;
    }

    @Override
    public String toString() {
        return "Timbratura{" +
                "id=" + id +
                ", dataTimbro=" + dataTimbro +
                ", dataScadenzaTimbro=" + dataScadenzaTimbro +
                ", idMezzo=" + idMezzo.getId() + ", tipoMezzo=" + idMezzo.getTipo() +
                ", idBiglietto=" + idBiglietto.getId() +
                '}';
    }
}
