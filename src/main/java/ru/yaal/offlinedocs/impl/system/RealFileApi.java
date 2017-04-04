package ru.yaal.offlinedocs.impl.system;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.system.FileApi;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
public class RealFileApi implements FileApi {
    @Override
    public String getFileSeparator() {
        return File.separator;
    }
}
