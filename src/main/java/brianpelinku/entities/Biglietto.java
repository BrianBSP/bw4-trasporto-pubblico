package brianpelinku.entities;


import brianpelinku.ENUM.Durata;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietto")
public class Biglietto {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "validita", nullable = false)
    private Integer validita;

    @Column(name = "timbrato", nullable = false)
    private Boolean timbrato;

//    @ManyToOne
//    @JoinColumn(name = "id_tessera")
//    private Tessera idTessera;

    @ManyToOne
    @JoinColumn(name = "id_punto_emissione")
    private PuntoEmissione idPuntoEmissione;
}
