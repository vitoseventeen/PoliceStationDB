package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "zlociny")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Zlocinec> zlocinci = new HashSet<>();

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

    public Set<Zlocinec> getZlocinci() {
        return zlocinci;
    }

    public void setZlocinci(Set<Zlocinec> zlocinci) {
        this.zlocinci = zlocinci;
    }

}