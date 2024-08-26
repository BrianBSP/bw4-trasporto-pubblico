package brianpelinku.entities;

import brianpelinku.ENUM.StatoDelMezzo;
import brianpelinku.ENUM.TipoMezzo;
import jakarta.persistence.*;

import java.awt.image.TileObserver;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "mezzo")
public class Mezzo {
    @Id
    @GeneratedValue
    private UUID id;


    @Column(name = "tipo_mezzo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMezzo tipo;

    @Column(name = "capienza", nullable = false)
    private Integer capienza;

    @OneToOne
    @JoinColumn(name = "id_stato")
    private StatoMezzo idStato;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta idTratta;

    public Mezzo(){}

    public Mezzo(TipoMezzo tipo, Integer capienza, StatoMezzo idStato, Tratta idTratta) {
        this.tipo = tipo;
        this.capienza = capienza;
        this.idStato = idStato;
        this.idTratta = idTratta;
    }

    public UUID getId() {
        return id;
    }

    public TipoMezzo getTipo() {
        return tipo;
    }

    public void setTipo(TipoMezzo tipo) {
        this.tipo = tipo;
    }

    public Integer getCapienza() {
        return capienza;
    }

    public void setCapienza(Integer capienza) {
        this.capienza = capienza;
    }

    public StatoMezzo getIdStato() {
        return idStato;
    }

    public void setIdStato(StatoMezzo idStato) {
        this.idStato = idStato;
    }

    public Tratta getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(Tratta idTratta) {
        this.idTratta = idTratta;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", capienza=" + capienza +
                ", idStato=" + idStato +
                ", idTratta=" + idTratta +
                '}';
    }
}
