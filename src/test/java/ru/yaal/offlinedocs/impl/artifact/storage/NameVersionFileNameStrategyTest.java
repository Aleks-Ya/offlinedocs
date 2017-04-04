package ru.yaal.offlinedocs.impl.artifact.storage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;

import java.io.File;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.io.FileMatchers.aFileWithAbsolutePath;
import static org.hamcrest.io.FileMatchers.aFileWithCanonicalPath;
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
        String sep = fileApi.getFileSeparator();
        String expected = "Hadoop" + sep + "HadoopJavadoc" + sep + "2.8.0" + sep + "HadoopJavadoc.pdf";
        assertThat(file, aFileWithAbsolutePath(endsWith(expected)));
    }

    @Test
    public void artifactToFileName() {
        Artifact artifact =
                new ArtifactImpl("Hadoop", "HadoopJavadoc", "2.8.0", artifactTypeFactory.getTypeById("pdf"), 100);
        String actual = strategy.artifactToFileName(artifact);
        String expected = "HadoopJavadoc-2.8.0.pdf";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void getTypeById() {
        File rootDir = new File("root");
        String name = "HadoopJavadoc";
        String type = "pdf";
        Artifact artifact = new ArtifactImpl("Hadoop", name, "2.8.0",
                artifactTypeFactory.getTypeById(type), 100);
        File actual = strategy.artifactToFile(rootDir, artifact);
        String expected = rootDir.getAbsolutePath() + fileApi.getFileSeparator() + "HadoopJavadoc-2.8.0.pdf";
        assertThat(actual, aFileWithCanonicalPath(equalTo(expected)));
    }
}