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
        Random random = new Random();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();


        // OSOBA
        OsobaDao osobaDao = new OsobaDao();
        osobaDao.setEntityManager(em);
        em.getTransaction().begin();
        String biometricData = String.format("%09d", random.nextInt(1_000_000_000));
        Osoba newOsoba = new Osoba(biometricData, "M", 25, LocalDate.of(1996, 5, 15), "Walker", "Vinnie");
        osobaDao.create(newOsoba);
        em.getTransaction().commit();
        osobaDao.findByPrijmeni("Walker").forEach(osoba -> System.out.println("Old name: " + osoba.getJmeno()));
        // Update operation
        List<Osoba> osoby = osobaDao.findByPrijmeni("Walker");
        if (!osoby.isEmpty()) {
            em.getTransaction().begin();
            Osoba osobaToUpdate = osoby.get(0);
            osobaToUpdate.setJmeno("Johny");
            osobaDao.update(osobaToUpdate);
            em.getTransaction().commit();
        } else {
            System.out.println("No Osoby found with the specified biometric data.");
        }
        // Output updated Osoba
        osobaDao.findByPrijmeni("Walker").forEach(osoba -> System.out.println("New name: " + osoba.getJmeno()));
        //Delete all Osoba with the specified prijmeni
        osobaDao.findByPrijmeni("Walker").forEach(osoba -> {
            em.getTransaction().begin();
            osobaDao.delete(osoba);
            em.getTransaction().commit();
            System.out.println("Osoby with prijmeni " + osoba.getPrijmeni() + " deleted.");
        });
        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
