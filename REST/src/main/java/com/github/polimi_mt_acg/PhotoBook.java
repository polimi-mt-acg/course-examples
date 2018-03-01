package com.github.polimi_mt_acg;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A PhotoBook. This class is a Singleton.
 */
@XmlRootElement(name = "PhotoBook")
public class PhotoBook {
    @XmlTransient
    private static PhotoBook ourInstance = new PhotoBook();

    @XmlElement
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
            pics.add(new Photo("github", "png", this.getClass().getResourceAsStream("/github.png")));
            pics.add(new Photo("google", "png", this.getClass().getResourceAsStream("/google.png")));
            pics.add(new Photo("microsoft", "png", this.getClass().getResourceAsStream("/microsoft.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}