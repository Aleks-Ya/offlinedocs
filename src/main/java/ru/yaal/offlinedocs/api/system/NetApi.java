package ru.yaal.offlinedocs.api.system;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public interface NetApi {
    InputStream openUrl(URL url);
}
