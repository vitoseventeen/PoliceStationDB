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

            // OSOBA
            osobaDao.setEntityManager(em);
            osobaDao.create("NEWOSOBA", "M", 34, LocalDate.of(1990, 11, 11), "Franklin", "Ben");
            List<Osoba> osoby = osobaDao.findByBiomData("NEWOSOBA");
            for (Osoba osoba : osoby) {
                System.out.println(osoba.getPrijmeni() + " " + osoba.getJmeno());
            }
            osobaDao.updateNameByBiometrickeUdaje("NEWOSOBA", "John");
            osobaDao.deleteByBiomData("NEWOSOBA");

            // POLICISTA
            policistaDao.setEntityManager(em);
            policistaDao.create("POLICE01", "M", 34, LocalDate.of(1990, 11, 11), "Norton", "Joe", "FBI", "Oddeleni_020", 7, null);
            List<Policista> policisti = policistaDao.findByBiomData("POLICE01");
            for (Policista policista : policisti) {
                System.out.println(policista.getPrijmeni() + " " + policista.getJmeno());
            }
            policistaDao.updateNameByBiometrickeUdaje("POLICE01", "John");
            policistaDao.deleteByBiomData("POLICE01");

            // ZLOCINEC
            zlocinecDao.setEntityManager(em);
            zlocinecDao.create("ZLOD1", "M", 31, LocalDate.of(1993, 12, 12), "Hilfiger", "Tommy", true, 178, 74);
            List<Zlocinec> zlocinci = zlocinecDao.findByBiomData("ZLOD1");
            for (Zlocinec zlocinec : zlocinci) {
                System.out.println(zlocinec.getPrijmeni() + " " + zlocinec.getJmeno());
            }
            zlocinecDao.updateNameByBiometrickeUdaje("ZLOD1", "Bobbie");
            zlocinecDao.deleteByBiomData("ZLOD1");

            em.getTransaction().commit();
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
