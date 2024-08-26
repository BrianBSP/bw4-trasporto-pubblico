package brianpelinku.entities;


import brianpelinku.ENUM.StatoDistributore;
import jakarta.persistence.*;

@Entity
@Table(name = "distributore")
public class DistributoreAutomatico extends PuntoEmissione{
    @Column(name = "stato")
    @Enumerated(EnumType.STRING)
    private StatoDistributore stato;

    public DistributoreAutomatico(){}

    public DistributoreAutomatico(String nome, String location, StatoDistributore stato) {
        super(nome, location);
        this.stato = stato;
    }

    public StatoDistributore getStato() {
        return stato;
    }

    public void setStato(StatoDistributore stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{" +
                "stato=" + stato +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
