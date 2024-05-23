package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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


    @ManyToMany(mappedBy = "spachatele")
    @JoinTable(
            name = "spacha",
            joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "zlocin_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Zlocin> spachaneZlociny = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "ma",
            joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "trest_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Trest> tresty = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "spacha",
            joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "zlocin_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Zlocin> zlociny = new HashSet<>();

    public Zlocinec() {
    }

    public Zlocinec(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni, String prijmeni, String jmeno, Boolean clenstviVGangu, Integer vyska, Integer hmotnost) {
        super(biometrickeUdaje, pohlavi, vek, datumNarozeni, prijmeni, jmeno);
        this.clenstviVGangu = clenstviVGangu;
        this.vyska = vyska;
        this.hmotnost = hmotnost;
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

    public Set<Zlocin> getZlociny() {
        return zlociny;
    }

    public void setZlociny(Set<Zlocin> zlociny) {
        this.zlociny = zlociny;
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

    public Set<Trest> getTresty() {
        return tresty;
    }

    public void setTresty(Set<Trest> tresty) {
        this.tresty = tresty;
    }

    public Set<Zlocin> getSpachaneZlociny() {
        return spachaneZlociny;
    }

    public void setSpachaneZlociny(Set<Zlocin> spachaneZlociny) {
        this.spachaneZlociny = spachaneZlociny;
    }
}
