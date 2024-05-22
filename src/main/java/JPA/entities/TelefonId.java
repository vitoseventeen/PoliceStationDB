package JPA.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class TelefonId implements java.io.Serializable {
    private static final long serialVersionUID = -2793208358222950198L;
    @Column(name = "osoba_id", nullable = false)
    private Integer osobaId;

    @Column(name = "telefon", nullable = false, length = 20)
    private String telefon;

    public Integer getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(Integer osobaId) {
        this.osobaId = osobaId;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TelefonId entity = (TelefonId) o;
        return Objects.equals(this.telefon, entity.telefon) &&
                Objects.equals(this.osobaId, entity.osobaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefon, osobaId);
    }

}