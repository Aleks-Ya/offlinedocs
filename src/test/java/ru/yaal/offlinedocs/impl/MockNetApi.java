package ru.yaal.offlinedocs.impl;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.system.NetApi;
import ru.yaal.offlinedocs.spring.Profiles;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(Profiles.TEST)
public class MockNetApi implements NetApi {
    private final Map<URL, InputStream> urlToStream = new HashMap<>();

    @Override
    @SneakyThrows
    public InputStream openUrl(URL url) {
        if (!urlToStream.containsKey(url)) {
            throw new IllegalStateException("URL not found: " + url);
        }
        return urlToStream.get(url);
    }

    public void putEntry(URL url, InputStream is) {
        urlToStream.put(url, is);
    }

}
