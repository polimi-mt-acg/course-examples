package com.github.polimi_mt_acg;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
public class PhotoTest {
    private Photo p;

    public PhotoTest() throws IOException {
        p = new Photo("github", "png", this.getClass().getResourceAsStream("/github.png"));
    }

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClass(Photo.class)
                .addAsResource("github.png")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Before
    public void setUp() {
        System.out.println("Setting up for test...");
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down test...");
    }

    @Test
    public void getFileName() {
        Assert.assertTrue("github.png".equals(p.getFileName()));
    }

    @Test
    public void buildRepresentation() {
        Assert.assertFalse("prova".equals(p.buildRepresentation()));
    }
}