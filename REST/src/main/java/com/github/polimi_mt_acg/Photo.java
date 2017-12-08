package com.github.polimi_mt_acg;

import javafx.scene.image.Image;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;

/**
 * A Photo in the PhotoBook.
 */
@XmlRootElement(name = "Photo")
public class Photo {

    @XmlElement
    private String baseName;

    @XmlElement
    private String format;

    @XmlElement
    private double width;

    @XmlElement
    private double height;

    @XmlTransient
    private File file;

    /**
     * Constructor
     *
     * @param photoURI the URI of the photo file.
     */
    public Photo(URI photoURI) throws FileNotFoundException {
        file = new File(photoURI);
        String filePath = file.getAbsolutePath();
        Image img = buildImage();

        baseName = FilenameUtils.getBaseName(filePath);
        format = FilenameUtils.getExtension(filePath);
        height = img.getHeight();
        width = img.getWidth();
    }

    /**
     * Default constructor required by JAXB
     */
    public Photo() {
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

        sb.append("fileName: ").append(baseName)
                .append(", format: ").append(format)
                .append(", resolution: ").append(height).append("x").append(width);
        return sb.toString();
    }
}
