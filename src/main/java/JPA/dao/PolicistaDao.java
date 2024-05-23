package JPA.dao;

import JPA.entities.Osoba;
import JPA.entities.Policista;

import java.time.LocalDate;
import java.util.List;

public class PolicistaDao extends BaseDao<Policista, Integer> {

    public PolicistaDao() {
        super(Policista.class);
    }


    public void create(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni, String prijmeni, String jmeno, String specializace, String nazevOddeleni, Integer pocetLetSluzby, String idNadrizeny) {
        Policista policista = new Policista(biometrickeUdaje, pohlavi, vek, datumNarozeni, prijmeni, jmeno, specializace, nazevOddeleni, pocetLetSluzby, idNadrizeny);
        getEntityManager().persist(policista);
        System.out.println("Policista " + prijmeni + " " + jmeno + " was created.");
    }

    public List<Policista> findBySpecializace(String specializace) {
        if (specializace == null) {
            return null;
        }
        return getEntityManager().createQuery("SELECT p FROM Policista p WHERE p.specializace = :specializace", Policista.class)
                .setParameter("specializace", specializace)
                .getResultList();
    }

    public List<Policista> findByPocetLetSluzbyGreaterThan(int pocetLet) {
        if (pocetLet < 0) {
            return null;
        }
        return getEntityManager().createQuery("SELECT p FROM Policista p WHERE p.pocetLetSluzby > :pocetLet", Policista.class)
                .setParameter("pocetLet", pocetLet)
                .getResultList();
    }

    public void updateNameByBiometrickeUdaje(String biomData, String name) {
        if (biomData == null) {
            return;
        }
        getEntityManager().createQuery("UPDATE Policista p SET p.jmeno = :name WHERE p.biometrickeUdaje = :biomData")
                .setParameter("name", name)
                .setParameter("biomData", biomData)
                .executeUpdate();
        System.out.println("Policista with data: " + biomData + " New name is: " + name);
    }

    public List<Policista> findByBiomData(String i) {
        if (i == null) {
            return null;
        }
        return getEntityManager().createQuery("SELECT p FROM Policista p WHERE p.biometrickeUdaje = :biomData", Policista.class)
                .setParameter("biomData", i)
                .getResultList();
    }

    public void deleteByBiomData(String biomData) {
        if (biomData == null) {
            return;
        }
        getEntityManager().createQuery("DELETE FROM Policista p WHERE p.biometrickeUdaje = :biomData")
                .setParameter("biomData", biomData)
                .executeUpdate();
        System.out.println("Policista with data: " + biomData + " was deleted.");
    }

    // Add more custom query methods as needed
}
