package JPA.app;

import JPA.dao.OsobaDao;
import JPA.dao.PolicistaDao;
import JPA.dao.ZlocinecDao;
import JPA.entities.Osoba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();
        // OSOBA
        OsobaDao osobaDao = new OsobaDao();
        osobaDao.setEntityManager(em);
        try {
            em.getTransaction().begin();
            osobaDao.create("5555555551", "M", 34, LocalDate.ofEpochDay(1990 - 11 - 11), "Walker", "John");
            osobaDao.create("33333333331", "M", 25, LocalDate.ofEpochDay(1999 - 3 - 11), "Walker", "John");
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Osoba with the specified data already exists.");
        }
        List<Osoba> osoby = osobaDao.findAllBySurnameAndName("Walker", "John");
        for (Osoba osoba : osoby) {
            System.out.println(osoba.getPrijmeni() + " " + osoba.getJmeno());
        }

        try {
            em.getTransaction().begin();
            osobaDao.updateNameByBiometrickeUdaje("555555555", "newName");
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No osoba found with the specified data.");
        }
        try {
            em.getTransaction().begin();
            osobaDao.deleteBySurnameAndName("Walker", "John");
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No osoba found with the specifieddata.");
        }

        // OSOBA END

        // POLICISTA

        PolicistaDao policistaDao = new PolicistaDao();
        policistaDao.setEntityManager(em);
        try {
            em.getTransaction().begin();
            policistaDao.create(new JPA.entities.Policista("4343432", "M", 34, LocalDate.ofEpochDay(1990 - 11 - 11), "Norton", "Joe", "K9", "Oddeleni_020", 22, null));
            policistaDao.create(new JPA.entities.Policista("2121232", "M", 34, LocalDate.ofEpochDay(1990 - 11 - 11), "Tyson", "Mike", "Agent", "Oddeleni_013", 44, null));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Policista with the specified data already exists.");
        }
        try {
            em.getTransaction().begin();
            policistaDao.updateNameByBiometrickeUdaje("222222", "Bob");
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No policista found with the specified data.");
        }

        try {
            em.getTransaction().begin();
            String biomData = "222222";
            policistaDao.deleteByBiomData(biomData);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No policista found with the specified data.");
        }

        policistaDao.findByPocetLetSluzbyGreaterThan(1).forEach(policista -> {
            System.out.println(policista.getPrijmeni() + " " + policista.getJmeno() + " " + policista.getPocetLetSluzby());
        });

        policistaDao.findBySpecializace("Agent").forEach(policista -> {
            System.out.println(policista.getPrijmeni() + " " + policista.getJmeno() + " " + policista.getSpecializace());
        });

        // POLICISTA END

        // ZLOCINEC

        ZlocinecDao zlocinecDao = new ZlocinecDao();
        zlocinecDao.setEntityManager(em);
        try {
            em.getTransaction().begin();
            zlocinecDao.create("1111939", "F", 22, LocalDate.ofEpochDay(2002 - 12 - 12), "Hilfiger", "Anna", true, 165, 52);
            zlocinecDao.create("2222299", "M", 33, LocalDate.ofEpochDay(1990 - 11 - 11), "Walker", "John", false, 180, 80);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Zlocinec with the specified data already exists.");
        }

        zlocinecDao.findBySurnameAndName("Walker", "John");

        zlocinecDao.findBySurnameAndName("Hilfiger", "Anna");
        em.getTransaction().commit();


        try {
            em.getTransaction().begin();
            zlocinecDao.updateNameByBiometrickeUdaje("2222299", "Bobbie");
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No zlocinec found with the specified data.");
        }

        try {
            em.getTransaction().begin();
            zlocinecDao.deleteBySurnameAndName("Hilfiger", "Anna");
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No zlocinec found with the specified data.");
        }


        // ZLOCINEC END


        closeEntityManager(em, emf);
    }


    public static void closeEntityManager(EntityManager em, EntityManagerFactory emf) {
        em.close();
        emf.close();
    }


    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        return emf.createEntityManager();
    }
}
