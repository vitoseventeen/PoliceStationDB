package JPA.dao;

import JPA.entities.Oddeleni;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional
public class OddeleniDao extends BaseDao<Oddeleni, Integer> {

    @PersistenceContext
    protected EntityManager entityManager;

    public OddeleniDao() {
        super(Oddeleni.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Oddeleni> findByNazev(String nazev) {
        return entityManager.createQuery("SELECT o FROM Oddeleni o WHERE o.nazev = :nazev", Oddeleni.class)
                .setParameter("nazev", nazev)
                .getResultList();
    }

}
