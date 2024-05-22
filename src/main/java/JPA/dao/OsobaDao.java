package JPA.dao;

import JPA.entities.Osoba;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional
public class OsobaDao extends BaseDao<Osoba, Integer> {

    public OsobaDao() {
        super(Osoba.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    public List<Osoba> findByJmeno(String jmeno) {
        return getEntityManager().createQuery("SELECT o FROM Osoba o WHERE o.jmeno = :jmeno", Osoba.class)
                .setParameter("jmeno", jmeno)
                .getResultList();
    }

    public List<Osoba> findByPrijmeni(String prijmeni) {
        return getEntityManager().createQuery("SELECT o FROM Osoba o WHERE o.prijmeni = :prijmeni", Osoba.class)
                .setParameter("prijmeni", prijmeni)
                .getResultList();
    }



}
