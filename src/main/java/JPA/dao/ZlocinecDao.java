package JPA.dao;

import JPA.entities.Policista;
import JPA.entities.Trest;
import JPA.entities.Zlocinec;

import java.time.LocalDate;
import java.util.List;

public class ZlocinecDao extends BaseDao<Zlocinec, Integer> {

    public ZlocinecDao() {
        super(Zlocinec.class);
    }

    public List<Zlocinec> findByBiomData(String i) {
        if (i == null) {
            return null;
        }
        return getEntityManager().createQuery("SELECT p FROM Zlocinec p WHERE p.biometrickeUdaje = :biomData", Zlocinec.class)
                .setParameter("biomData", i)
                .getResultList();
    }

    public void create(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni, String prijmeni, String jmeno, Boolean clenstviVGangu, Integer vyska, Integer hmotnost) {
        Zlocinec zlocinec = new Zlocinec(biometrickeUdaje, pohlavi, vek, datumNarozeni, prijmeni, jmeno, clenstviVGangu, vyska, hmotnost);
        getEntityManager().persist(zlocinec);
        System.out.println("Zlocinec " + prijmeni + " " + jmeno + " was created.");
    }

    public void updateNameByBiometrickeUdaje(String biometrickeUdaje, String jmeno) {
        getEntityManager().createQuery("UPDATE Zlocinec z SET z.jmeno = :jmeno WHERE z.biometrickeUdaje = :biometrickeUdaje")
                .setParameter("jmeno", jmeno)
                .setParameter("biometrickeUdaje", biometrickeUdaje)
                .executeUpdate();
        System.out.println("Zlocinec with data: " + biometrickeUdaje + " New name is: " + jmeno);
    }

    public void deleteBySurnameAndName(String prijmeni, String jmeno) {
        getEntityManager().createQuery("DELETE FROM Zlocinec z WHERE z.prijmeni = :prijmeni AND z.jmeno = :jmeno")
                .setParameter("prijmeni", prijmeni)
                .setParameter("jmeno", jmeno)
                .executeUpdate();
        System.out.println("Zlocinec " + prijmeni + " " + jmeno + " was deleted.");
    }

    public void deleteByBiomData(String biomData) {
        getEntityManager().createQuery("DELETE FROM Zlocinec z WHERE z.biometrickeUdaje = :biomData")
                .setParameter("biomData", biomData)
                .executeUpdate();
        System.out.println("Zlocinec with data: " + biomData + " was deleted.");
    }

    public void findBySurnameAndName(String prijmeni, String jmeno) {
        List<Zlocinec> results = getEntityManager().createQuery("SELECT z FROM Zlocinec z WHERE z.prijmeni = :prijmeni AND z.jmeno = :jmeno", Zlocinec.class)
                .setParameter("prijmeni", prijmeni)
                .setParameter("jmeno", jmeno)
                .getResultList();
        for (Zlocinec zlocinec : results) {
            System.out.println("Zlocinec " + zlocinec.getPrijmeni() + " " + zlocinec.getJmeno() + " was found.");
        }
    }

    public void addTrestToZlocinec(String zlocinecBiomData, Trest trest) {
        Zlocinec zlocinec = getEntityManager().createQuery("SELECT z FROM Zlocinec z WHERE z.biometrickeUdaje = :biomData", Zlocinec.class)
                .setParameter("biomData", zlocinecBiomData)
                .getSingleResult();
        if (zlocinec != null) {
            zlocinec.getTresty().add(trest);
            getEntityManager().merge(zlocinec);
            System.out.println("Trest added to Zlocinec with data " + zlocinecBiomData);
        } else {
            System.out.println("Zlocinec not found with data " + zlocinecBiomData);
        }
    }

    public void removeTrestFromZlocinec(String zlocinecBiomData, Trest trest) {
        Zlocinec zlocinec = getEntityManager().createQuery("SELECT z FROM Zlocinec z WHERE z.biometrickeUdaje = :biomData", Zlocinec.class)
                .setParameter("biomData", zlocinecBiomData)
                .getSingleResult();
        if (zlocinec != null) {
            zlocinec.getTresty().remove(trest);
            getEntityManager().merge(zlocinec);
            System.out.println("Trest removed from Zlocinec with data " + zlocinecBiomData);
        } else {
            System.out.println("Zlocinec not found with data " + zlocinecBiomData);
        }
    }

    public List<Zlocinec> findAllBySurnameAndName(String prijmeni, String jmeno) {
        return getEntityManager().createQuery("SELECT z FROM Zlocinec z WHERE z.prijmeni = :prijmeni AND z.jmeno = :jmeno", Zlocinec.class)
                .setParameter("prijmeni", prijmeni)
                .setParameter("jmeno", jmeno)
                .getResultList();
    }
}
