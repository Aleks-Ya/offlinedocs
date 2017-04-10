package ru.yaal.offlinedocs.impl.storage;

import org.hamcrest.io.FileMatchers;
import org.junit.Test;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;

import java.io.File;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class OutletStorageImplTest extends TestBase {

    @Test
    public void artifactToFileName() {
        Artifact artifact = new ArtifactImpl("Hadoop", "HadoopJavadoc", "2.8.0",
                artifactTypeFactory.getTypeById("pdf"));
        File actual = outletStorage.getArtifactFile(artifact);
        String expected = "HadoopJavadoc-2.8.0.pdf";
        assertThat(actual, FileMatchers.aFileWithCanonicalPath(endsWith(expected)));
    }

    @Test
    public void getArtifactDir() {
        String sep = fileApi.getFileSeparator();
        Artifact artifact = new ArtifactImpl("Hadoop", "HadoopJavadoc", "2.8.0",
                artifactTypeFactory.getTypeById("pdf"));
        File actual = outletStorage.getArtifactDir(artifact);
        String expected = "Hadoop" + sep + "HadoopJavadoc" + sep + "2.8.0";
        assertThat(actual, FileMatchers.aFileWithCanonicalPath(endsWith(expected)));
    }
}