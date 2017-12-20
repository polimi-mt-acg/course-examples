package com.github.polimi_mt_acg;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
    private BufferedImage image;

    /**
     * Constructor
     *
     * @param baseName Photo file baseName (e.g. "github")
     * @param extension Photo file extension (e.g. "png")
     * @param image Photo file input stream
     * @throws IOException if {@code image} cannot be read into an Image
     */
    public Photo(String baseName, String extension, InputStream image) throws IOException {
        this.image = ImageIO.read(image);

        this.baseName = baseName;
        format = extension;
        height = this.image.getHeight();
        width = this.image.getWidth();
    }

    /**
     * Default constructor required by JAXB
     */
    public Photo() {
    }

    /**
     * @return the Photo file name (base name + extension)
     */
    public String getFileName() {
        return baseName + "." + format;
    }

    /**
     * Builds a String representation of this Photo of format:<br>
     * fileName: <basename>, format: <extension>, resolution <width> x <height>
     *
     * @return String representation of this Photo.
     * @throws FileNotFoundException the photo file can't be found at the URI passed in the constructor
     */
    public String buildRepresentation() throws FileNotFoundException {
        return "fileName: " + baseName +
                ", format: " + format +
                ", resolution: " + height + "x" + width;
    }
}
