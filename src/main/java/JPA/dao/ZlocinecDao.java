package JPA.dao;


import JPA.entities.Zlocinec;

import java.time.LocalDate;

public class ZlocinecDao extends BaseDao<Zlocinec, Integer> {

        public ZlocinecDao() {
            super(Zlocinec.class);
        }

        public void create(String biometrickeUdaje, String pohlavi, Integer vek, LocalDate datumNarozeni, String prijmeni, String jmeno, Boolean clenstviVGangu, Integer vyska, Integer hmotnost) {
            Zlocinec zlocinec = new Zlocinec(biometrickeUdaje, pohlavi, vek, datumNarozeni, prijmeni, jmeno, clenstviVGangu, vyska, hmotnost);
            getEntityManager().persist(zlocinec);
            System.out.println("Zlocinec " + prijmeni + jmeno + " was created.");
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
            System.out.println("Zlocinec " + prijmeni + jmeno + " was deleted.");
        }

        public void deleteByBiomData(String biomData) {
            getEntityManager().createQuery("DELETE FROM Zlocinec z WHERE z.biometrickeUdaje = :biomData")
                    .setParameter("biomData", biomData)
                    .executeUpdate();
            System.out.println("Zlocinec with data: " + biomData + " was deleted.");
        }

        public void findBySurnameAndName(String prijmeni, String jmeno) {
            getEntityManager().createQuery("SELECT z FROM Zlocinec z WHERE z.prijmeni = :prijmeni AND z.jmeno = :jmeno", Zlocinec.class)
                    .setParameter("prijmeni", prijmeni)
                    .setParameter("jmeno", jmeno)
                    .getResultList();
            System.out.println("Zlocinec " + prijmeni + jmeno + " was found.");
        }



}
