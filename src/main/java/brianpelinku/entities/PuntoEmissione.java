package brianpelinku.entities;


import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "punto_emissione")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoEmissione {
    @Id
    @GeneratedValue
    protected UUID id;

    @Column(name = "nome", nullable = false)
    protected String nome;

    @Column(name = "location", nullable = false)
    protected String location;

    @OneToMany(mappedBy = "idPuntoEmissione")
    protected Set<Abbonamento> nrAbbonamenti;

    @OneToMany(mappedBy = "idPuntoEmissione")
    protected Set<Biglietto> nrBiglietti;

    public PuntoEmissione(){}

    public PuntoEmissione(String nome, String location) {
        this.nome = nome;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "PuntoEmissione{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
