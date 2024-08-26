package brianpelinku.entities;

import brianpelinku.ENUM.StatoDistributore;
import brianpelinku.ENUM.TipoRivenditore;
import jakarta.persistence.*;

@Entity
@Table(name = "rivenditore")
public class RivenditoreAutorizzato extends PuntoEmissione{

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoRivenditore tipo;

    public RivenditoreAutorizzato() {

    }

    public RivenditoreAutorizzato(String nome, String location, TipoRivenditore tipo) {
        super(nome, location);
        this.tipo = tipo;
    }

    public TipoRivenditore getTipo() {
        return tipo;
    }

    public void setTipo(TipoRivenditore tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "RivenditoreAutorizzato{" +
                "tipo=" + tipo +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
