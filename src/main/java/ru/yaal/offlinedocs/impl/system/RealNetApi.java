package ru.yaal.offlinedocs.impl.system;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.system.NetApi;
import ru.yaal.offlinedocs.spring.Profiles;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(Profiles.PROD)
public class RealNetApi implements NetApi {
    @Override
    @SneakyThrows
    public InputStream openUrl(URL url) {
        return url.openStream();
    }
}
