package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "spacha", schema = "public", indexes = {
        @Index(name = "idx_spacha_osoba_id", columnList = "osoba_id")
})
public class Spacha {
    @EmbeddedId
    private SpachaId id;

    @MapsId("zlocinId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "zlocin_id", nullable = false)
    private Zlocin zlocin;

    @MapsId("osobaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "osoba_id", nullable = false)
    private Zlocinec osoba;

    public SpachaId getId() {
        return id;
    }

    public void setId(SpachaId id) {
        this.id = id;
    }

    public Zlocin getZlocin() {
        return zlocin;
    }

    public void setZlocin(Zlocin zlocin) {
        this.zlocin = zlocin;
    }

    public Zlocinec getOsoba() {
        return osoba;
    }

    public void setOsoba(Zlocinec osoba) {
        this.osoba = osoba;
    }

}