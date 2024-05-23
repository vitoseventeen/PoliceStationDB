package JPA.app;

import JPA.dao.OsobaDao;
import JPA.dao.PolicistaDao;
import JPA.dao.ZlocinecDao;
import JPA.entities.Osoba;
import JPA.entities.Policista;
import JPA.entities.Zlocin;
import JPA.entities.Zlocinec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        try {
            System.out.println("---------------------START-----------------------------");
            tranzakceCP4();
            printCriminalsAndCrimes();
            crudPolicista();
            crudOsoba();
            crudZlocinec();

            System.out.println("---------------------END-----------------------------");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            closeEntityManager(em, emf);
        }
    }

    public static void closeEntityManager(EntityManager em, EntityManagerFactory emf) {
        em.close();
        emf.close();
    }

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        return emf.createEntityManager();
    }

    public static void crudZlocinec() {
        em.getTransaction().begin();

        ZlocinecDao zlocinecDao = new ZlocinecDao();
        System.out.println("---------------------ZLOCINEC-----------------------------");
        // ZLOCINEC
        zlocinecDao.setEntityManager(em);
        zlocinecDao.create("9430289991", "M", 31, LocalDate.of(1993, 12, 12), "Hilfiger", "Tommy", true, 178, 74);
        List<Zlocinec> zlocinci = zlocinecDao.findByBiomData("9430289991");
        for (Zlocinec zlocinec : zlocinci) {
            System.out.println(zlocinec.getPrijmeni() + " " + zlocinec.getJmeno());
        }
        zlocinecDao.updateNameByBiometrickeUdaje("9430289991", "Bobbie");
        zlocinecDao.deleteByBiomData("9430289991");

        em.getTransaction().commit();
    }

    public static void crudOsoba() {
        em.getTransaction().begin();

        OsobaDao osobaDao = new OsobaDao();
        System.out.println("---------------------OSOBA-----------------------------");
        osobaDao.setEntityManager(em);
        osobaDao.create("129049012", "M", 34, LocalDate.of(1990, 11, 11), "Franklin", "Ben");
        List<Osoba> osoby = osobaDao.findByBiomData("129049012");
        for (Osoba osoba : osoby) {
            System.out.println(osoba.getPrijmeni() + " " + osoba.getJmeno());
        }
        osobaDao.updateNameByBiometrickeUdaje("129049012", "John");
        osobaDao.deleteByBiomData("129049012");

        em.getTransaction().commit();
    }

    public static void tranzakceCP4() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("---------------------TRANZAKCE-----------------------------");
        try {
            Query updateQuery = em.createQuery("UPDATE Policista p SET p.pocetLetSluzby = p.pocetLetSluzby + 1");
            updateQuery.executeUpdate();
            Query selectQuery = em.createQuery("SELECT p FROM Policista p WHERE p.specializace = :specializace");
            selectQuery.setParameter("specializace", "Kriminalistika");
            List<Policista> policisti = selectQuery.getResultList();
            System.out.println("Policisti specializace Kriminalistika:");
            for (Policista policista : policisti) {
                System.out.println(policista.getPrijmeni() + " " + policista.getJmeno());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        em.getTransaction().commit();
    }

    public static void crudPolicista() {
        em.getTransaction().begin();

        System.out.println("---------------------POLICISTA-----------------------------");
        PolicistaDao policistaDao = new PolicistaDao();
        policistaDao.setEntityManager(em);
        policistaDao.create("31313141", "M", 34, LocalDate.of(1990, 11, 11), "Norton", "Joe", "FBI", "Oddeleni_020", 7, null);
        List<Policista> policiste = policistaDao.findByBiomData("31313141");
        for (Policista policista : policiste) {
            System.out.println(policista.getPrijmeni() + " " + policista.getJmeno());
        }
        policistaDao.updateNameByBiometrickeUdaje("31313141", "Jack");
        policistaDao.deleteByBiomData("31313141");

        em.getTransaction().commit();
    }

    public static void printCriminalsAndCrimes() {

        System.out.println("---------------------CRIMINALS WITH CRIMES-----------------------------");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ZlocinecDao zlocinecDao = new ZlocinecDao();
        zlocinecDao.setEntityManager(em);

        List<Zlocinec> zlocinci = zlocinecDao.findAllWithCrimes();

        System.out.println("Criminals and their crimes:");
        for (Zlocinec zlocinec : zlocinci) {
            if (!zlocinec.getZlociny().isEmpty()) {
                System.out.printf(zlocinec.getPrijmeni() + " " + zlocinec.getJmeno() + ".");
                for (Zlocin zlocin : zlocinec.getZlociny()) {
                    System.out.println(" Zlocin: " + zlocin.getPopis());
                }
            }
        }

        em.getTransaction().commit();
    }
}
