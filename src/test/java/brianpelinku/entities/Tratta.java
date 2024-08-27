package brianpelinku.entities;

import java.util.UUID;

public class Tratta {

    private UUID id;
    private String nome;
    private String partenza;
    private String capolinea;
    private int tempoPrevisto;

    public Tratta(String nome, String partenza, String capolinea, int tempoPrevisto) {
        this.id = UUID.randomUUID();
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

    public int getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(int tempoPrevisto) {
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
