package brianpelinku.entities;

import java.util.UUID;

public class Mezzo {

    private UUID id;
    private long capienza;
    private Tratta tratta;

    public Mezzo(long capienza, Tratta tratta) {
        this.id = UUID.randomUUID();
        this.capienza = capienza;
        this.tratta = tratta;
    }



    public UUID getId() {
        return id;
    }

    public long getCapienza() {
        return capienza;
    }

    public void setCapienza(long capienza) {
        this.capienza = capienza;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", tratta=" + tratta +
                '}';
    }
}
