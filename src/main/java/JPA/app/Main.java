package JPA.app;

import JPA.dao.OsobaDao;
import JPA.entities.Osoba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();



        // OSOBA

        OsobaDao osobaDao = new OsobaDao();
        osobaDao.setEntityManager(em);
        osobaDao.createWithData("555555555", "M", 34, LocalDate.ofEpochDay(1990-11-11), "Walker", "John");
        osobaDao.createWithData("3333333333", "M", 25,LocalDate.ofEpochDay(1999-3-11) ,"Walker", "John");
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
        // ZLOCINEC



        // ZLOCINEC END


        closeEntityManager(em, emf);
    }



    public static void closeEntityManager(EntityManager em, EntityManagerFactory emf) {
        em.close();
        emf.close();
    }


}
