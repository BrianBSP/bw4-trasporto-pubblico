package brianpelinku.entities;

import jakarta.persistence.*;


import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tratta")
public class Tratta {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "partenza", nullable = false)
    private String partenza;

    @Column(name = "capolinea", nullable = false)
    private String capolinea;

    @Column(name = "tempo_previsto", nullable = false)
    private Integer tempoPrevisto;

    @OneToMany(mappedBy = "idTratta")
    private Set<Mezzo> idMezzo;

    public Tratta(){}

    public Tratta(String nome, String partenza, String capolinea, Integer tempoPrevisto) {
        this.nome = nome;
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
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

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public Integer getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(Integer tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPrevisto=" + tempoPrevisto +
                '}';
    }
}
