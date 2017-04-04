package ru.yaal.offlinedocs.impl.artifact.storage;

import org.hamcrest.io.FileMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;

import java.io.File;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class NameVersionFileNameStrategyTest extends TestBase {

    @Autowired
    private NameVersionFileNameStrategy strategy;

    @Test
    public void toFile() throws Exception {
        File rootDir = new File("root");
        Artifact artifact = new ArtifactImpl("Hadoop", "HadoopJavadoc", "2.8.0",
                artifactTypeFactory.getTypeById("pdf"), 100);
        File file = strategy.toFile(rootDir, artifact);
        String expected = "HadoopJavadoc" + File.separator + "2.8.0" + File.separator + "object.bin";
        assertThat(file, FileMatchers.aFileWithAbsolutePath(endsWith(expected)));
    }

}