package com.github.polimi_mt_acg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class UserTest {
    // Test members
    private SessionFactory sessionFactory;
    private User testUser;

    /**
     * To test this unit on a JEE Application Server we leverage Arquillian Test Suite.
     * Within Arquillian we have to describe right in code the way the WAR file to test
     * has to be built, dependencies included. Although a little verbose, this enables
     * extreme flexibility, avoiding to bundle the entire set of build dependencies
     * to test a small portion of the whole system. This is good then. Trust me.
     * <p>
     * MavenResolver is a handy library to manage Maven dependencies in Arquillian tests.
     * By means of a list of strings in the form 'groupId:artifactId:version" you can
     * declare which dependencies this unit requires and MavenResolver will collect them
     * at testing time.
     */
    @Deployment
    public static WebArchive createDeployment() {
        File[] files = Maven.resolver()
                .resolve("org.hibernate:hibernate-core:5.2.14.Final",
                        "org.mariadb.jdbc:mariadb-java-client:2.1.2")
                .withTransitivity()
                .asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(User.class)
                .addAsResource("hibernate.cfg.xml")
                .addAsLibraries(files)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * Before each test, create a SessionFactory from the hibernate.cgf.xml config file
     * and instantiate a test User object.
     */
    @Before
    public void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }

        // Then create a fictitious User
        testUser = new User("testUserFirstName", "testUserLastName");
    }

    @After
    public void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void getId() {
        // Persist a fictitious user
        persistUser(testUser);

        // Now read from db
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User u = session.get(User.class, testUser.getId());
        assertNotNull(u);

        // Done with DB.
        session.getTransaction().commit();
        session.close();

        // Check ID is valid
        assertEquals(u.getId(), testUser.getId());
    }

    @Test
    public void getFirstName() {
        // Persist a fictitious user
        persistUser(testUser);

        // Now read from db
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User u = session.get(User.class, testUser.getId());
        assertNotNull(u);

        // Done with DB.
        session.getTransaction().commit();
        session.close();

        assertEquals(u.getFirstName(), testUser.getFirstName());
    }

    @Test
    public void setFirstName() {
        // Persist a fictitious user
        persistUser(testUser);

        // Now read from db
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User u = session.get(User.class, testUser.getId());
        assertNotNull(u);

        // Change its name and write to database
        String newName = "newTestFirstName";
        u.setFirstName(newName);
        session.flush();
        session.getTransaction().commit();
        session.close();

        // And now check if write process went fine
        session = sessionFactory.openSession();
        session.beginTransaction();

        u = session.get(User.class, testUser.getId());
        assertNotNull(u);
        session.getTransaction().commit();
        session.close();

        assertEquals(u.getFirstName(), newName);
    }

    @Test
    public void getLastName() {
        // Persist a fictitious user
        persistUser(testUser);

        // Now read from db
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User u = session.get(User.class, testUser.getId());
        assertNotNull(u);

        // Done with DB.
        session.getTransaction().commit();
        session.close();

        assertEquals(u.getLastName(), testUser.getLastName());
    }

    @Test
    public void setLastName() {
        // Persist a fictitious user
        persistUser(testUser);

        // Now read from db
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User u = session.get(User.class, testUser.getId());
        assertNotNull(u);

        // Change its name and write to database
        String newName = "newTestLastName";
        u.setLastName(newName);
        session.flush();
        session.getTransaction().commit();
        session.close();

        // And now check if write process went fine
        session = sessionFactory.openSession();
        session.beginTransaction();

        u = session.get(User.class, testUser.getId());
        assertNotNull(u);
        session.getTransaction().commit();
        session.close();

        assertEquals(u.getLastName(), newName);
    }

    private void persistUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(testUser);

        session.close();
    }
}
