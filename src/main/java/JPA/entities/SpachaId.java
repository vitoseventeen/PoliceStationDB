package JPA.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class SpachaId implements java.io.Serializable {
    private static final long serialVersionUID = -1796160290133631954L;
    @Column(name = "zlocin_id", nullable = false)
    private Integer zlocinId;

    @Column(name = "osoba_id", nullable = false)
    private Integer osobaId;

    public Integer getZlocinId() {
        return zlocinId;
    }

    public void setZlocinId(Integer zlocinId) {
        this.zlocinId = zlocinId;
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
        SpachaId entity = (SpachaId) o;
        return Objects.equals(this.zlocinId, entity.zlocinId) &&
                Objects.equals(this.osobaId, entity.osobaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zlocinId, osobaId);
    }

}