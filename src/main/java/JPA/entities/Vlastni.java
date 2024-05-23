package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "vlastni", schema = "public")
public class Vlastni {
    @EmbeddedId
    private VlastniId id;

    @MapsId("osobaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "osoba_id", nullable = false)
    private Policista osoba;

    @MapsId("winKod")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "win_kod", nullable = false, referencedColumnName = "win_kod")
    private SluzebniAuto winKod;

    public VlastniId getId() {
        return id;
    }

    public void setId(VlastniId id) {
        this.id = id;
    }

    public Policista getOsoba() {
        return osoba;
    }

    public void setOsoba(Policista osoba) {
        this.osoba = osoba;
    }

    public SluzebniAuto getWinKod() {
        return winKod;
    }

    public void setWinKod(SluzebniAuto winKod) {
        this.winKod = winKod;
    }

}