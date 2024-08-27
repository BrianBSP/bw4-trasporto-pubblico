package brianpelinku.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "giro_tratta")
public class GiroTratta {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo idMezzo;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta idTratta;

    @Column(name = "tempo_partenza", nullable = false)
    private LocalDateTime tempoPartenza;

    @Column(name = "tempo_arrivo", nullable = false)
    private LocalDateTime tempoArrivo;

    public GiroTratta(){}

    public GiroTratta(Mezzo idMezzo, Tratta idTratta, LocalDateTime tempoPartenza, LocalDateTime tempoArrivo) {
        this.idMezzo = idMezzo;
        this.idTratta = idTratta;
        this.tempoPartenza = tempoPartenza;
        this.tempoArrivo = tempoArrivo;
    }

    public UUID getId() {
        return id;
    }

    public Mezzo getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Mezzo idMezzo) {
        this.idMezzo = idMezzo;
    }

    public LocalDateTime getTempoPartenza() {
        return tempoPartenza;
    }

    public void setTempoPartenza(LocalDateTime tempoPartenza) {
        this.tempoPartenza = tempoPartenza;
    }

    public Tratta getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(Tratta idTratta) {
        this.idTratta = idTratta;
    }

    public LocalDateTime getTempoArrivo() {
        return tempoArrivo;
    }

    public void setTempoArrivo(LocalDateTime tempoArrivo) {
        this.tempoArrivo = tempoArrivo;
    }

    @Override
    public String toString() {
        return "GiroTratta{" +
                "id=" + id +
                ", idMezzo=" + idMezzo.getTipo() +
                ", idTratta=" + idTratta.getNome() +
                ", tempoPartenza=" + tempoPartenza +
                ", tempoArrivo=" + tempoArrivo +
                '}';
    }
}
