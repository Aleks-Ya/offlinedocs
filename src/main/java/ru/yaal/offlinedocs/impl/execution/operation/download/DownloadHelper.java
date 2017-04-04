package ru.yaal.offlinedocs.impl.execution.operation.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public class DownloadHelper {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadHelper.class);

    private DownloadHelper() {
    }

    public static byte[] inputStreamToByteArray(InputStream is, int logEveryBytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        long loaded = 0;
        int b;
        while ((b = bis.read()) != -1) {
            loaded++;
            if (loaded % logEveryBytes == 0) {
                LOG.debug("Downloaded {} bytes", loaded);
            }
            out.write(b);
        }
        return out.toByteArray();
    }
}
