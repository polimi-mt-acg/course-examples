package com.github.polimi_mt_acg;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class PhotoBook {
    private static PhotoBook ourInstance = new PhotoBook();
    private List<Image> pics;


    private PhotoBook() {
        pics = new ArrayList<Image>();
        addDefaultImages();
    }

    public static PhotoBook getInstance() {
        return ourInstance;
    }

    public void add(Image img) {
        pics.add(img);
    }

    private void addDefaultImages() {
        pics.add(new Image(this.getClass().getResourceAsStream("github.png")));
        pics.add(new Image(this.getClass().getResourceAsStream("google.png")));
        pics.add(new Image(this.getClass().getResourceAsStream("microsoft.png")));
    }
}