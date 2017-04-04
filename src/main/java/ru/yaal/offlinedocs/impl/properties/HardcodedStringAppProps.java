package ru.yaal.offlinedocs.impl.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.properties.StringAppProps;
import ru.yaal.offlinedocs.api.system.FileApi;
import ru.yaal.offlinedocs.api.system.SystemApi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yablokov Aleksey
 */
@Component
public class HardcodedStringAppProps implements StringAppProps {
    private final Map<String, String> props = new HashMap<>();

    @Autowired
    public HardcodedStringAppProps(SystemApi systemApi, FileApi fileApi) {
        props.put(DataAppPropsImpl.OUTLET_DIR_PROP,
                systemApi.getUserHome() + fileApi.getFileSeparator() + "offline_documentation");
        props.put(DataAppPropsImpl.TMP_DIR_PROP,
                systemApi.getTmpDir() + fileApi.getFileSeparator() + "offlinedocs");
    }

    @Override
    public String getProperty(String propertyName) {
        return props.get(propertyName);
    }
}
