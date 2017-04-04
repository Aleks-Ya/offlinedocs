package ru.yaal.offlinedocs.impl.artifact.type;

import org.junit.Test;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;
import ru.yaal.offlinedocs.impl.TestBase;

import static org.junit.Assert.assertNotNull;

/**
 * @author Yablokov Aleksey
 */
public class ArtifactTypeFactoryImplTest extends TestBase {
    @Test
    public void getTypeById() {
        ArtifactType pdfType = artifactTypeFactory.getTypeById("pdf");
        assertNotNull(pdfType);
    }

}