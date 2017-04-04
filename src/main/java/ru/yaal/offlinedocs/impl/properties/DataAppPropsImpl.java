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
    public static final String OUTPUT_DIR = "outputdir";
    private final StringAppProps props;

    @Autowired
    public DataAppPropsImpl(StringAppProps props) {
        this.props = props;
    }

    @Override
    public File getOutputDir() {
        return new File(props.getProperty(OUTPUT_DIR));
    }
}
