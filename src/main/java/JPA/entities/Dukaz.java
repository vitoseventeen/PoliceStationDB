package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "dukaz", schema = "public")
public class Dukaz {
    @EmbeddedId
    private DukazId id;

    @MapsId("zlocinId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "zlocin_id", nullable = false)
    private Zlocin zlocin;

    @Column(name = "datum_nalezeni", nullable = false)
    private LocalDate datumNalezeni;

    @Column(name = "popis", nullable = false, length = 200)
    private String popis;

    @Column(name = "misto_nalezeni", nullable = false, length = 200)
    private String mistoNalezeni;

    public DukazId getId() {
        return id;
    }

    public void setId(DukazId id) {
        this.id = id;
    }

    public Zlocin getZlocin() {
        return zlocin;
    }

    public void setZlocin(Zlocin zlocin) {
        this.zlocin = zlocin;
    }

    public LocalDate getDatumNalezeni() {
        return datumNalezeni;
    }

    public void setDatumNalezeni(LocalDate datumNalezeni) {
        this.datumNalezeni = datumNalezeni;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getMistoNalezeni() {
        return mistoNalezeni;
    }

    public void setMistoNalezeni(String mistoNalezeni) {
        this.mistoNalezeni = mistoNalezeni;
    }

}