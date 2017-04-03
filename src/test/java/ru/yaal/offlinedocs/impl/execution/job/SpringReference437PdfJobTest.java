package ru.yaal.offlinedocs.impl.execution.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yaal.offlinedocs.api.artifact.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OperationListInitParams;

/**
 * @author Yablokov Aleksey
 */
public class SpringReference437PdfJobTest extends TestBase {
    @Autowired
    @Qualifier("springReference437Pdf")
    private Job<OperationListInitParams, EmptyExecuteParams, EmptyResult> job;

    @Autowired
    private ArtifactStorage storage;

    @Test
    public void execute() throws Exception {
        EmptyResult result = job.execute(EmptyExecuteParams.instance);
//        storage.has(new ArtifactImpl());
    }

}