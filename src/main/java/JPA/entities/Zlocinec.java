package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "zlocinec", schema = "public", indexes = {
        @Index(name = "idx_vyska", columnList = "vyska")
})
@PrimaryKeyJoinColumn(name = "osoba_id")
public class Zlocinec extends Osoba {

    @Column(name = "clenstvi_v_gangu")
    private Boolean clenstviVGangu;

    @Column(name = "vyska", nullable = false)
    private Integer vyska;

    @Column(name = "hmotnost", nullable = false)
    private Integer hmotnost;

    public Zlocinec() {

    }

    public Zlocinec(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni, String prijmeni, String jmeno) {
        super(biometrickeUdaje, pohlavi, vek, datumNarozeni, prijmeni, jmeno);
    }

    // Getters and Setters
    public Boolean getClenstviVGangu() {
        return clenstviVGangu;
    }

    public void setClenstviVGangu(Boolean clenstviVGangu) {
        this.clenstviVGangu = clenstviVGangu;
    }

    public Integer getVyska() {
        return vyska;
    }

    public void setVyska(Integer vyska) {
        this.vyska = vyska;
    }

    public Integer getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(Integer hmotnost) {
        this.hmotnost = hmotnost;
    }
}
