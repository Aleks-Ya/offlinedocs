package ru.yaal.offlinedocs.impl.execution.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOperation;

import java.io.ByteArrayInputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class HadoopJavadocJobTest extends TestBase {
    @Autowired
    @Qualifier("hadoopJavadocJob")
    private Job job;

    @Test
    public void name() throws Exception {
        String artifactCategory = "Hadoop";
        String artifactName = "HadoopJavadoc";
        String artifactVersion = "2.8.0";
        URL artifactUrl = new URL("http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz");
        DownloadToStorageInitParams initParams =
                new DownloadToStorageInitParams(artifactCategory, artifactName, artifactVersion, artifactUrl, "pdf");
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOperationResult> operation =
                factory.getNewOperation(DownloadToStorageOperation.class, initParams);
        byte[] isArray = {1, 2, 3, 4, 5};
        ByteArrayInputStream is = new ByteArrayInputStream(isArray);
        netApi.putEntry(artifactUrl, is);
        Result result = job.execute(EmptyExecuteParams.instance);
    }
}
