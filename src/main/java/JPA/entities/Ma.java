package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ma", schema = "public")
public class Ma {
    @EmbeddedId
    private MaId id;

    @MapsId("trestId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "trest_id", nullable = false)
    private Trest trest;

    @MapsId("osobaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "osoba_id", nullable = false)
    private Zlocinec osoba;

    public MaId getId() {
        return id;
    }

    public void setId(MaId id) {
        this.id = id;
    }

    public Trest getTrest() {
        return trest;
    }

    public void setTrest(Trest trest) {
        this.trest = trest;
    }

    public Zlocinec getOsoba() {
        return osoba;
    }

    public void setOsoba(Zlocinec osoba) {
        this.osoba = osoba;
    }

}