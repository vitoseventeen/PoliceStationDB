package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "policista", schema = "public", indexes = {
        @Index(name = "idx_specializace", columnList = "specializace")
})
@PrimaryKeyJoinColumn(name = "osoba_id")
public class Policista extends Osoba {

    @Column(name = "specializace", nullable = false, length = 100)
    private String specializace;

    @Column(name = "pocet_let_sluzby", nullable = false)
    private Integer pocetLetSluzby;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nazev", referencedColumnName = "nazev")
    private Oddeleni nazev;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nadrizeny")
    private Policista idNadrizeny;

    public Policista() {

    }

    public Policista(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni, String prijmeni, String jmeno) {
        super(biometrickeUdaje, pohlavi, vek, datumNarozeni, prijmeni, jmeno);
    }


    // Getters and Setters
    public String getSpecializace() {
        return specializace;
    }

    public void setSpecializace(String specializace) {
        this.specializace = specializace;
    }

    public Integer getPocetLetSluzby() {
        return pocetLetSluzby;
    }

    public void setPocetLetSluzby(Integer pocetLetSluzby) {
        this.pocetLetSluzby = pocetLetSluzby;
    }

    public Oddeleni getNazev() {
        return nazev;
    }

    public void setNazev(Oddeleni nazev) {
        this.nazev = nazev;
    }

    public Policista getIdNadrizeny() {
        return idNadrizeny;
    }

    public void setIdNadrizeny(Policista idNadrizeny) {
        this.idNadrizeny = idNadrizeny;
    }
}
