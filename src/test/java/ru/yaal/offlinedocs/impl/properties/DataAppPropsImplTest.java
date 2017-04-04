package ru.yaal.offlinedocs.impl.properties;

import org.junit.Test;
import ru.yaal.offlinedocs.impl.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.io.FileMatchers.aFileWithCanonicalPath;

/**
 * @author Yablokov Aleksey
 */
public class DataAppPropsImplTest extends TestBase {
    @Test
    public void getOutputDir() {
        String expected = fileApi.getFileSeparator() + "offline_documentation";
        assertThat(dataAppProps.getOutletDir(), aFileWithCanonicalPath(endsWith(expected)));
    }

}