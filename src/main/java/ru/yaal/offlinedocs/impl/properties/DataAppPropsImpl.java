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
public class DataAppPropsImpl implements DataAppProps {
    public static final String OUTLET_DIR_PROP = "dir.outlet";
    public static final String TMP_DIR_PROP = "dir.tmp";
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
    public File getTmpDirectory() {
        return new File(props.getProperty(TMP_DIR_PROP));
    }
}
