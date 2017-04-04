package ru.yaal.offlinedocs.impl.execution.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OperationListInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOperation;

import java.io.ByteArrayInputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class SpringReference437PdfJobTest extends TestBase {
    @Autowired
    @Qualifier("springReference437Pdf")
    private Job<OperationListInitParams, EmptyExecuteParams, EmptyResult> job;

    @Test
    public void execute() throws Exception {
        String artifactCategory = "Spring";
        String artifactName = "SpringReferencePdf";
        String artifactVersion = "4.3.7";
        URL artifactUrl = new URL("http://docs.spring.io/spring/docs/4.3.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf");
        DownloadToStorageInitParams initParams = new DownloadToStorageInitParams(artifactCategory, artifactName, artifactVersion, artifactUrl, "pdf");
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOperationResult> operation =
                factory.getNewOperation(DownloadToStorageOperation.class, initParams);
        byte[] isArray = {1, 2, 3, 4, 5};
        ByteArrayInputStream is = new ByteArrayInputStream(isArray);
        netApi.putEntry(artifactUrl, is);

        EmptyResult result = job.execute(EmptyExecuteParams.instance);
//        storage.has(new ArtifactImpl());
    }

}