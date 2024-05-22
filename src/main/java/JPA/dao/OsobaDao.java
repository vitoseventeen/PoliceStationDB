package JPA.dao;

import JPA.entities.Osoba;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public class OsobaDao extends BaseDao<Osoba, Integer> {

    public OsobaDao() {
        super(Osoba.class);
    }



    public void createWithData(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni, String prijmeni, String jmeno) {
        Osoba osoba = new Osoba(biometrickeUdaje, pohlavi, vek, datumNarozeni, prijmeni, jmeno);
        getEntityManager().persist(osoba);
        System.out.println("Osoba " + prijmeni + jmeno + " was created.");
    }

    public void updateNameByBiometrickeUdaje(String biometrickeUdaje, String jmeno) {
        getEntityManager().createQuery("UPDATE Osoba o SET o.jmeno = :jmeno WHERE o.biometrickeUdaje = :biometrickeUdaje")
                .setParameter("jmeno", jmeno)
                .setParameter("biometrickeUdaje", biometrickeUdaje)
                .executeUpdate();
        System.out.println("Osoba with data: " + biometrickeUdaje + " New name is: " + jmeno);
    }

    public void deleteBySurnameAndName(String prijmeni, String jmeno) {
        getEntityManager().createQuery("DELETE FROM Osoba o WHERE o.prijmeni = :prijmeni AND o.jmeno = :jmeno")
                .setParameter("prijmeni", prijmeni)
                .setParameter("jmeno", jmeno)
                .executeUpdate();
        System.out.println("Osoba " + prijmeni + jmeno + " was deleted.");
    }

    public List<Osoba> findAllBySurnameAndName(String prijmeni, String jmeno) {
        return getEntityManager().createQuery("SELECT o FROM Osoba o WHERE o.prijmeni = :prijmeni AND o.jmeno = :jmeno", Osoba.class)
                .setParameter("prijmeni", prijmeni)
                .setParameter("jmeno", jmeno)
                .getResultList();
    }



}
