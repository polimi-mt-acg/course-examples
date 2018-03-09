package com.github.polimi_mt_acg;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class PersistentUnit {
    @PersistenceUnit
    private EntityManagerFactory emf;

    public static void main(String[] args) {
        System.out.println("Creating PersistentUnit...");
        PersistentUnit pu = new PersistentUnit();
        System.out.println("Running test...");
        pu.test();
        System.out.println("Done!");
    }

    public void test() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new User("test", "mysql"));
        em.getTransaction().commit();
        em.close();
    }
}
