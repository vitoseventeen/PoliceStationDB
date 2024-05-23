package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "zlocin", schema = "public", indexes = {
        @Index(name = "idx_cislo_pripadu", columnList = "cislo_pripadu")
}, uniqueConstraints = {
        @UniqueConstraint(name = "zlocin_cislo_pripadu_key", columnNames = {"cislo_pripadu"})
})
public class Zlocin {
    @Id
    @ColumnDefault("nextval('zlocin_zlocin_id_seq'::regclass)")
    @Column(name = "zlocin_id", nullable = false)
    private Integer id;

    @Column(name = "cislo_pripadu", nullable = false)
    private Integer cisloPripadu;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "popis", nullable = false, length = 200)
    private String popis;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nazev", nullable = false, referencedColumnName = "nazev")
    private Oddeleni nazev;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCisloPripadu() {
        return cisloPripadu;
    }

    public void setCisloPripadu(Integer cisloPripadu) {
        this.cisloPripadu = cisloPripadu;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public Oddeleni getNazev() {
        return nazev;
    }

    public void setNazev(Oddeleni nazev) {
        this.nazev = nazev;
    }

}