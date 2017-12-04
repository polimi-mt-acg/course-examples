package com.github.polimi_mt_acg;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A PhotoBook. This class is a Singleton.
 */
public class PhotoBook {
    private static PhotoBook ourInstance = new PhotoBook();
    private final List<Photo> pics;

    /**
     * PhotoBook constructor.
     * It adds some default Photos to the PhotoBook.
     */
    private PhotoBook() {
        pics = new ArrayList<>();
        addDefaultImages();
    }

    /**
     * @return the PhotoBook instance.
     */
    public static PhotoBook getInstance() {
        return ourInstance;
    }

    /**
     * Adds a Photo to the PhotoBook
     *
     * @param photo Photo to add.
     */
    public void add(Photo photo) {
        pics.add(photo);
    }

    /**
     * @return an immutable view of the PhotoBook
     */
    public List<Photo> getPics() {
        return Collections.unmodifiableList(pics);
    }

    private void addDefaultImages() {
        try {
            pics.add(new Photo(this.getClass().getResource("/github.png").toURI()));
            pics.add(new Photo(this.getClass().getResource("/google.png").toURI()));
            pics.add(new Photo(this.getClass().getResource("/microsoft.png").toURI()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}