package JPA.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DukazId implements java.io.Serializable {
    private static final long serialVersionUID = -331178619449086758L;
    @Column(name = "zlocin_id", nullable = false)
    private Integer zlocinId;

    @Column(name = "kod_dukazu", nullable = false, length = 30)
    private String kodDukazu;

    public Integer getZlocinId() {
        return zlocinId;
    }

    public void setZlocinId(Integer zlocinId) {
        this.zlocinId = zlocinId;
    }

    public String getKodDukazu() {
        return kodDukazu;
    }

    public void setKodDukazu(String kodDukazu) {
        this.kodDukazu = kodDukazu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DukazId entity = (DukazId) o;
        return Objects.equals(this.zlocinId, entity.zlocinId) &&
                Objects.equals(this.kodDukazu, entity.kodDukazu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zlocinId, kodDukazu);
    }

}