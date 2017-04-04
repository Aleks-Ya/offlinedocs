package ru.yaal.offlinedocs.impl.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.api.properties.StringAppProps;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
class DataAppPropsImpl implements DataAppProps {
    static final String OUTLET_DIR_PROP = "dir.outlet";
    static final String TEMP_DIR_PROP = "dir.temp";
    static final String STORAGE_DIR_PROP = "dir.storage";
    private final StringAppProps props;

    @Autowired
    public DataAppPropsImpl(StringAppProps props) {
        this.props = props;
    }

    @Override
    public File getOutletDir() {
        return new File(props.getProperty(OUTLET_DIR_PROP));
    }

    @Override
    public File getArtifactStorageDir() {
        return new File(props.getProperty(STORAGE_DIR_PROP));
    }

    @Override
    public File getTempDir() {
        return new File(props.getProperty(TEMP_DIR_PROP));
    }
}
