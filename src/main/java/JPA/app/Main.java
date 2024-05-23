package JPA.app;

import JPA.dao.OsobaDao;
import JPA.dao.PolicistaDao;
import JPA.dao.ZlocinecDao;
import JPA.entities.Osoba;

import JPA.entities.Policista;
import JPA.entities.Zlocinec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();
        OsobaDao osobaDao = new OsobaDao();
        PolicistaDao policistaDao = new PolicistaDao();
        ZlocinecDao zlocinecDao = new ZlocinecDao();

        try {

            em.getTransaction().begin();
            // POLICISTA
            policistaDao.setEntityManager(em);
            policistaDao.create("904092190", "M", 34, LocalDate.of(1990, 11, 11), "Norton", "Joe", "FBI", "Oddeleni_020", 7, null);
            List<Policista> policiste = policistaDao.findByBiomData("904092190");
            for (Policista policista : policiste) {
                System.out.println(policista.getPrijmeni() + " " + policista.getJmeno());
            }
            policistaDao.updateNameByBiometrickeUdaje("904092190", "Jack");
            policistaDao.deleteByBiomData("904092190");

            System.out.println("---------------------TRANZAKCE-----------------------------");

            //tranzakce z CP4
            try {
                Query updateQuery = em.createQuery("UPDATE Policista p SET p.pocetLetSluzby = p.pocetLetSluzby + 1");
                updateQuery.executeUpdate();
                Query selectQuery = em.createQuery("SELECT p FROM Policista p WHERE p.specializace = :specializace");
                selectQuery.setParameter("specializace", "Kriminalistika");
                List<Policista> policisti = selectQuery.getResultList();
                System.out.println("Policisti specializace Kriminalistika:");
                System.out.println("----------------");
                for (Policista policista : policisti) {
                    System.out.println(policista.getPrijmeni() + " " + policista.getJmeno());
                }
                System.out.println("----------------");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }
            //konec tranzakce

            // OSOBA
            System.out.println("---------------------OSOBA-----------------------------");
            osobaDao.setEntityManager(em);
            osobaDao.create("129049012", "M", 34, LocalDate.of(1990, 11, 11), "Franklin", "Ben");
            List<Osoba> osoby = osobaDao.findByBiomData("129049012");
            for (Osoba osoba : osoby) {
                System.out.println(osoba.getPrijmeni() + " " + osoba.getJmeno());
            }
            osobaDao.updateNameByBiometrickeUdaje("129049012", "John");
            osobaDao.deleteByBiomData("129049012");


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
}
