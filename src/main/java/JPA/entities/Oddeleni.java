package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "oddeleni", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "oddeleni_nazev_key", columnNames = {"nazev"})
})
public class Oddeleni {
    @Id
    @ColumnDefault("nextval('oddeleni_oddeleni_id_seq'::regclass)")
    @Column(name = "oddeleni_id", nullable = false)
    private Integer id;

    @Column(name = "nazev", nullable = false, length = 50)
    private String nazev;

    @Column(name = "ulice", nullable = false, length = 100)
    private String ulice;

    @Column(name = "psc", nullable = false, length = 10)
    private String psc;

    @Column(name = "obec", nullable = false, length = 100)
    private String obec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getObec() {
        return obec;
    }

    public void setObec(String obec) {
        this.obec = obec;
    }

}