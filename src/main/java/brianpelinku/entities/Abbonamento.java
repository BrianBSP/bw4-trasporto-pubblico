package brianpelinku.entities;

import brianpelinku.ENUM.Durata;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamento")
public class Abbonamento {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "durata", nullable = false)
    private Durata durata;

//    @ManyToOne
//    @JoinColumn(name = "id_tessera")
//    private Tessera idTessera;

    @ManyToOne
    @JoinColumn(name = "id_punto_emissione")
    private PuntoEmissione idPuntoEmissione;


}
