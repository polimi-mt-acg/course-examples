package com.github.polimi_mt_acg;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Arquillian.class)
class PhotoTest {
    Photo p = new Photo("github", "png", this.getClass().getResourceAsStream("/github.png"));

    PhotoTest() throws IOException {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClass(Photo.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setting up for test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down test...");
    }

    @Test
    void getFileName() {
        assertTrue("github.png".equals(p.getFileName()));
    }

    @Test
    void buildRepresentation() {
        assertFalse("prova".equals(p.buildRepresentation()));
    }
}