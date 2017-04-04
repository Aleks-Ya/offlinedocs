package ru.yaal.offlinedocs.impl.execution.operation.copy;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.artifact.data.ByteArrayArtifactData;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.io.FileMatchers.aFileNamed;
import static org.hamcrest.io.FileMatchers.anExistingFile;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class CopyArtifactOperationTest extends TestBase {

    @Test
    public void execute() throws IOException {
        String artifactName = "SpringJavadoc";
        String artifactVersion = "4.3.7";
        String artifactType = "pdf";
        Artifact artifact = new ArtifactImpl("Spring", artifactName, artifactVersion,
                artifactTypeFactory.getTypeById(artifactType), 100);

        byte[] artifactArray = {1, 2, 3, 4, 5};
        ArtifactData data = new ByteArrayArtifactData(artifact, artifactArray);
        artifactStorage.save(data);

        File destDir = Files.createTempDirectory(getClass().getSimpleName() + "_").toFile();
        destDir.deleteOnExit();
        CopyArtifactInitParams params = new CopyArtifactInitParams(artifact, destDir);
        Operation<CopyArtifactInitParams, EmptyExecuteParams, EmptyResult> operation =
                executionFactory.getNewOperation(CopyArtifactOperation.class, params);
        operation.execute(EmptyExecuteParams.instance);
        File destFile = fileNameStrategy.artifactToFile(destDir, artifact);
        assertThat(destFile, anExistingFile());
        assertThat(destFile, aFileNamed(Matchers.equalTo(artifactName + "." + artifactType)));
    }
}