package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OperationListInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;

import java.io.ByteArrayInputStream;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToStorageOperationTest extends TestBase {
    @Autowired
    @Qualifier("springReference437Pdf")
    private Job<OperationListInitParams, EmptyExecuteParams, EmptyResult> job;

    @Test
    public void execute() throws Exception {
        String artifactCategory = "Spring";
        String artifactName = "SpringReferencePdf";
        String artifactVersion = "4.3.7";
        URL artifactUrl = new URL("http://docs.spring.io/spring/docs/4.3.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf");
        DownloadToStorageInitParams initParams = new DownloadToStorageInitParams(artifactCategory, artifactName, artifactVersion, artifactUrl);
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOperationResult> operation =
                factory.getNewOperation(DownloadToStorageOperation.class, initParams);
        byte[] isArray = {1, 2, 3, 4, 5};
        ByteArrayInputStream is = new ByteArrayInputStream(isArray);
        netApi.putEntry(artifactUrl, is);

        ArtifactDataOperationResult result = operation.execute(EmptyExecuteParams.instance);
        ArtifactImpl expArtifact = new ArtifactImpl(artifactCategory, artifactName, artifactVersion, isArray.length);

        ArtifactData artifactData = result.getArtifactData();
        assertThat(artifactData.getArtifact(), equalTo(expArtifact));
        assertTrue(storage.has(expArtifact));
    }

}