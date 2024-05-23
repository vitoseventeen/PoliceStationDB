package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "osoba", schema = "public", indexes = {
        @Index(name = "idx_osoba_datum_narozeni", columnList = "datum_narozeni")
}, uniqueConstraints = {
        @UniqueConstraint(name = "osoba_biometricke_udaje_key", columnNames = {"biometricke_udaje"})
})
public class Osoba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "osoba_id", nullable = false)
    private Integer id;

    @Column(name = "biometricke_udaje", nullable = false, length = 20)
    private String biometrickeUdaje;

    @Column(name = "pohlavi", nullable = false, length = Integer.MAX_VALUE)
    private String pohlavi;

    @Column(name = "vek", nullable = false)
    private Integer vek;

    @Column(name = "datum_narozeni", nullable = false)
    private LocalDate datumNarozeni;

    @Column(name = "prijmeni", nullable = false, length = 50)
    private String prijmeni;

    @Column(name = "jmeno", nullable = false, length = 50)
    private String jmeno;

    public Osoba() {
    }

    public Osoba(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni , String prijmeni, String jmeno) {
        this.biometrickeUdaje = biometrickeUdaje;
        this.pohlavi = pohlavi;
        this.datumNarozeni = datumNarozeni;
        this.vek = vek;
        this.prijmeni = prijmeni;
        this.jmeno = jmeno;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBiometrickeUdaje() {
        return biometrickeUdaje;
    }

    public void setBiometrickeUdaje(String biometrickeUdaje) {
        this.biometrickeUdaje = biometrickeUdaje;
    }

    public String getPohlavi() {
        return pohlavi;
    }

    public void setPohlavi(String pohlavi) {
        this.pohlavi = pohlavi;
    }

    public Integer getVek() {
        return vek;
    }

    public void setVek(Integer vek) {
        this.vek = vek;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(LocalDate datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

}
