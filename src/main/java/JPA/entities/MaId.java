package JPA.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class MaId implements java.io.Serializable {
    private static final long serialVersionUID = 7088572950245341796L;
    @Column(name = "trest_id", nullable = false)
    private Integer trestId;

    @Column(name = "osoba_id", nullable = false)
    private Integer osobaId;

    public Integer getTrestId() {
        return trestId;
    }

    public void setTrestId(Integer trestId) {
        this.trestId = trestId;
    }

    public Integer getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(Integer osobaId) {
        this.osobaId = osobaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaId entity = (MaId) o;
        return Objects.equals(this.trestId, entity.trestId) &&
                Objects.equals(this.osobaId, entity.osobaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trestId, osobaId);
    }

}