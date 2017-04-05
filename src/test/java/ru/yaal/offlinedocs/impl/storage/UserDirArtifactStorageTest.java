package ru.yaal.offlinedocs.impl.storage;

import org.junit.Test;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;

import java.io.ByteArrayInputStream;
import java.io.File;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.io.FileMatchers.aFileWithAbsolutePath;
import static org.junit.Assert.assertThat;

/**
 * TODO download file to temp dir and then copy to artifact storage
 * TODO unpack file to temp dir and then copy to outlet
 *
 * @author Yablokov Aleksey
 */
public class UserDirArtifactStorageTest extends TestBase {
    @Test
    public void subDirInStorage() throws Exception {
        //TODO factory class contains "hadoop artifact" and "hadoop artifact data"
        Artifact artifact = new ArtifactImpl("Hadoop", "HadoopJavadoc", "2.8.0",
                artifactTypeFactory.getTypeById("pdf"));
        ByteArrayInputStream is = new ByteArrayInputStream(new byte[]{1, 2, 3});
        File artifactFile = artifactStorage.save(artifact, is).getFile();
        String sep = fileApi.getFileSeparator();
        String expected = "Hadoop" + sep + "HadoopJavadoc" + sep + "2.8.0" + sep + "HadoopJavadoc-2.8.0.pdf";
        assertThat(artifactFile, aFileWithAbsolutePath(endsWith(expected)));
    }

}