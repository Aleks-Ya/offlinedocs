package ru.yaal.offlinedocs.impl.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.properties.StringAppProps;
import ru.yaal.offlinedocs.api.system.FileApi;
import ru.yaal.offlinedocs.api.system.SystemApi;
import ru.yaal.offlinedocs.spring.Profiles;

import java.util.HashMap;
import java.util.Map;

import static ru.yaal.offlinedocs.impl.properties.DataAppPropsImpl.*;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(Profiles.PROD)
class HardcodedStringAppProps implements StringAppProps {
    private final Map<String, String> props = new HashMap<>();

    @Autowired
    public HardcodedStringAppProps(SystemApi systemApi, FileApi fileApi) {
        String sep = fileApi.getFileSeparator();
        props.put(OUTLET_DIR_PROP, systemApi.getUserHome() + sep + "offline_documentation");
        props.put(TEMP_DIR_PROP, systemApi.getTmpDir() + sep + "offlinedocs");
        props.put(STORAGE_DIR_PROP, systemApi.getUserHome() + sep + ".offlinedocs" + sep + "storage");
    }

    @Override
    public String getProperty(String propertyName) {
        return props.get(propertyName);
    }
}
