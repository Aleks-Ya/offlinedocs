package ru.yaal.offlinedocs.impl.execution.operation.copy;

import org.junit.Test;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.impl.execution.id.JustJobId;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.io.FileMatchers.aFileWithSize;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class CopyArtifactOpTest extends TestBase {

    @Test
    public void execute() throws IOException {
        String artifactName = "SpringJavadoc";
        String artifactVersion = "4.3.7";
        String artifactType = "pdf";
        Artifact artifact = new ArtifactImpl("Spring", artifactName, artifactVersion,
                artifactTypeFactory.getTypeById(artifactType));

        ByteArrayInputStream is = new ByteArrayInputStream(new byte[]{1, 2, 3, 4, 5});
        artifactStorage.save(artifact, is);

        File destFile = File.createTempFile(getClass().getSimpleName() + "_", ".tmp");
        destFile.deleteOnExit();
        assertThat(destFile, aFileWithSize(0));
        CopyArtifactOp.InitParams params = new CopyArtifactOp.InitParams(artifact, destFile);
        execFactory.getNewOperation(JustJobId.jobId, CopyArtifactOp.class, params).execute(EmptyExecParams.instance);
        assertThat(destFile, aFileWithSize(greaterThan(0L)));
    }
}