package ru.yaal.offlinedocs.impl.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.properties.StringAppProps;
import ru.yaal.offlinedocs.api.system.FileApi;
import ru.yaal.offlinedocs.api.system.SystemApi;
import ru.yaal.offlinedocs.spring.Profiles;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static ru.yaal.offlinedocs.impl.properties.DataAppPropsImpl.*;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(Profiles.TEST)
public class TestStringAppProps implements StringAppProps {
    private static final Logger LOG = LoggerFactory.getLogger(TestStringAppProps.class);

    private final Map<String, String> props = new HashMap<>();

    @Autowired
    public TestStringAppProps(SystemApi systemApi, FileApi fileApi) {
        File baseDir = new File(systemApi.getTmpDir(), "TestOfflineDocs");
        baseDir.deleteOnExit();
        if (baseDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            baseDir.delete();
            //noinspection ResultOfMethodCallIgnored
            baseDir.mkdirs();
        }
        LOG.info("Base test directory: " + baseDir.getAbsolutePath());
        String sep = fileApi.getFileSeparator();
        props.put(OUTLET_DIR_PROP, baseDir + sep + "offline_documentation");
        props.put(TEMP_DIR_PROP, baseDir + sep + "offlinedocs");
        props.put(STORAGE_DIR_PROP, baseDir + sep + ".offlinedocs" + sep + "storage");
    }

    @Override
    public String getProperty(String propertyName) {
        return props.get(propertyName);
    }
}
