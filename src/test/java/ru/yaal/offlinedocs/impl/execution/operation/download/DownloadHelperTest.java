package ru.yaal.offlinedocs.impl.execution.operation.download;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class DownloadHelperTest {
    @Test
    public void copyInputStreamToOutputStream() {
        byte[] bytes = new byte[]{1, 2, 3};
        InputStream is = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        DownloadHelper.copyInputStreamToOutputStream(is, os, 1);
        assertThat(os.toByteArray(), equalTo(bytes));
    }

}