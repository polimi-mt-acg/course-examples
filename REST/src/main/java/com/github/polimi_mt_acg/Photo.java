package com.github.polimi_mt_acg;

import javafx.scene.image.Image;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;

/**
 * A Photo in the PhotoBook.
 */
public class Photo {
    private File file;

    /**
     * Constructor
     *
     * @param photoURI the URI of the photo file.
     */
    public Photo(URI photoURI) {
        file = new File(photoURI);
    }

    /**
     * @return a JavaFX Image showing this Photo.
     * @throws FileNotFoundException if the photo file can't be found at the URI passed in the constructor
     */
    public Image buildImage() throws FileNotFoundException {
        return new Image(new FileInputStream(file));
    }

    /**
     * @return the Photo file name (base name + extension)
     */
    public String getFileName() {
        return FilenameUtils.getName(file.getAbsolutePath());
    }

    /**
     * Builds a String representation of this Photo of format:<br>
     * fileName: <basename>, format: <extension>, resolution <width> x <height>
     *
     * @return String representation of this Photo.
     * @throws FileNotFoundException the photo file can't be found at the URI passed in the constructor
     */
    public String buildRepresentation() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        String filePath = file.getAbsolutePath();
        Image img = buildImage();

        sb.append("fileName: ").append(FilenameUtils.getBaseName(filePath))
                .append(", format: ").append(FilenameUtils.getExtension(filePath))
                .append(", resolution: ").append(img.getHeight()).append("x").append(img.getWidth());
        return sb.toString();
    }
}
