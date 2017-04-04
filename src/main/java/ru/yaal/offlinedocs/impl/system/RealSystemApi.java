package ru.yaal.offlinedocs.impl.system;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.system.SystemApi;

/**
 * @author Yablokov Aleksey
 */
@Component
public class RealSystemApi implements SystemApi {
    @Override
    public String getUserHome() {
        return System.getProperty("user.home");
    }

    @Override
    public String getTmpDir() {
        return System.getProperty("java.io.tmpdir");
    }
}
