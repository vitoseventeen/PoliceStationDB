package JPA.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class VlastniId implements java.io.Serializable {
    private static final long serialVersionUID = -2047014876109074783L;
    @Column(name = "osoba_id", nullable = false)
    private Integer osobaId;

    @Column(name = "win_kod", nullable = false)
    private Integer winKod;

    public Integer getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(Integer osobaId) {
        this.osobaId = osobaId;
    }

    public Integer getWinKod() {
        return winKod;
    }

    public void setWinKod(Integer winKod) {
        this.winKod = winKod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VlastniId entity = (VlastniId) o;
        return Objects.equals(this.osobaId, entity.osobaId) &&
                Objects.equals(this.winKod, entity.winKod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(osobaId, winKod);
    }

}