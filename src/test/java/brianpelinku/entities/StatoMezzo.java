package brianpelinku.entities;

import brianpelinku.enums.Stato;

import java.util.Date;
import java.util.UUID;

public class StatoMezzo {

    private UUID id;
    private Stato stato;
    private Date dataInizio;

    public StatoMezzo(Stato stato, Date dataInizio) {
        this.id = UUID.randomUUID();
        this.stato = stato;
        this.dataInizio = dataInizio;
    }

    public UUID getId() {
        return id;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    @Override
    public String toString() {
        return "StatoMezzo{" +
                "id=" + id +
                ", stato=" + stato +
                ", dataInizio=" + dataInizio +
                '}';
    }
}
