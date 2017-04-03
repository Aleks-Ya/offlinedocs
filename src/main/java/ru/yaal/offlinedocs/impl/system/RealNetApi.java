package ru.yaal.offlinedocs.impl.system;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.system.NetApi;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
public class RealNetApi implements NetApi {
    @Override
    @SneakyThrows
    public InputStream openUrl(URL url) {
        return url.openStream();
    }
}
