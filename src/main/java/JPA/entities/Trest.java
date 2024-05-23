package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trest", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "trest_cislo_veznice_key", columnNames = {"cislo_veznice"})
})
public class Trest {
    @Id
    @ColumnDefault("nextval('trest_trest_id_seq'::regclass)")
    @Column(name = "trest_id", nullable = false)
    private Integer id;

    @Column(name = "cislo_veznice", nullable = false)
    private Integer cisloVeznice;

    @Column(name = "delka_vezeni", nullable = false)
    private Integer delkaVezeni;

    @ManyToMany(mappedBy = "tresty")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Zlocinec> zlocinci = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCisloVeznice() {
        return cisloVeznice;
    }

    public void setCisloVeznice(Integer cisloVeznice) {
        this.cisloVeznice = cisloVeznice;
    }

    public Integer getDelkaVezeni() {
        return delkaVezeni;
    }

    public void setDelkaVezeni(Integer delkaVezeni) {
        this.delkaVezeni = delkaVezeni;
    }

    public Set<Zlocinec> getZlocinci() {
        return zlocinci;
    }

    public void setZlocinci(Set<Zlocinec> zlocinci) {
        this.zlocinci = zlocinci;
    }

}
