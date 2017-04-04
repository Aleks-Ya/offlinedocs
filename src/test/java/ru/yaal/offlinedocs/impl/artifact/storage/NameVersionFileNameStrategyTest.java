package ru.yaal.offlinedocs.impl.artifact.storage;

import org.hamcrest.io.FileMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;

import java.io.File;

import static java.io.File.separator;
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
        String expected = "Hadoop" + separator + "HadoopJavadoc" + separator + "2.8.0" + separator + "HadoopJavadoc.pdf";
        assertThat(file, FileMatchers.aFileWithAbsolutePath(endsWith(expected)));
    }

}