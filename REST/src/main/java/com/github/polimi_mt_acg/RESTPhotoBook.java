package com.github.polimi_mt_acg;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class RESTPhotoBook extends ResourceConfig {
    public RESTPhotoBook() {
        packages("com.github.polimi_mt_acg");
    }
}
