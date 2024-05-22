package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "telefon", schema = "public", indexes = {
        @Index(name = "idx_osoba_id", columnList = "osoba_id"),
        @Index(name = "idx_telefon", columnList = "telefon")
})
public class Telefon {
    @EmbeddedId
    private TelefonId id;

    @MapsId("osobaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "osoba_id", nullable = false)
    private Osoba osoba;

    public TelefonId getId() {
        return id;
    }

    public void setId(TelefonId id) {
        this.id = id;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

}