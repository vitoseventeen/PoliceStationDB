package JPA.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sluzebni_auto", schema = "public", indexes = {
        @Index(name = "idx_auto_win_kod", columnList = "win_kod")
}, uniqueConstraints = {
        @UniqueConstraint(name = "sluzebni_auto_win_kod_key", columnNames = {"win_kod"}),
        @UniqueConstraint(name = "sluzebni_auto_znacka_model_rok_vyroby_key", columnNames = {"znacka", "model", "rok_vyroby"})
})
public class SluzebniAuto {
    @Id
    @ColumnDefault("nextval('sluzebni_auto_auto_id_seq'::regclass)")
    @Column(name = "auto_id", nullable = false)
    private Integer id;

    @Column(name = "win_kod", nullable = false)
    private Integer winKod;

    @Column(name = "znacka", nullable = false, length = 50)
    private String znacka;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "rok_vyroby", nullable = false)
    private Integer rokVyroby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWinKod() {
        return winKod;
    }

    public void setWinKod(Integer winKod) {
        this.winKod = winKod;
    }

    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRokVyroby() {
        return rokVyroby;
    }

    public void setRokVyroby(Integer rokVyroby) {
        this.rokVyroby = rokVyroby;
    }

}