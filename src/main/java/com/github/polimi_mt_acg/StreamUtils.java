package com.github.polimi_mt_acg;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
    public static File stream2file(InputStream in, String baseName, String suffix) throws IOException {
        final File tempFile = File.createTempFile(baseName, suffix);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }

}