package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import org.junit.Test;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;

import java.io.ByteArrayInputStream;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToStorageOpTest extends TestBase {
    @Test
    public void execute() throws Exception {
        String artifactCategory = "Spring";
        String artifactName = "SpringReferencePdf";
        String artifactVersion = "4.3.7";
        URL artifactUrl = new URL("http://docs.spring.io/spring/docs/4.3.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf");
        DownloadToStorageInitParams initParams =
                new DownloadToStorageInitParams(artifactCategory, artifactName, artifactVersion, artifactUrl, "pdf");
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOpResult> op =
                executionFactory.getNewOperation(DownloadToStorageOp.class, initParams);
        ByteArrayInputStream is = new ByteArrayInputStream(new byte[]{1, 2, 3, 4, 5});
        netApi.putEntry(artifactUrl, is);

        ArtifactDataOpResult result = op.execute(EmptyExecuteParams.instance);
        ArtifactImpl expArtifact = new ArtifactImpl(artifactCategory, artifactName, artifactVersion,
                artifactTypeFactory.getTypeById("pdf"));

        ArtifactData artifactData = result.getArtifactData();
        assertThat(artifactData.getArtifact(), equalTo(expArtifact));
        assertTrue(artifactStorage.has(expArtifact));
    }

}