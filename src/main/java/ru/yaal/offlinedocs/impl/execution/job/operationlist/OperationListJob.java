package ru.yaal.offlinedocs.impl.execution.job.operationlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.configuration.ConfigurationApi;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.AbstractJob;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class OperationListJob extends AbstractJob<OperationListInitParams, EmptyExecuteParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(OperationListJob.class);
    @Autowired
    private ConfigurationApi configurationApi;

    public OperationListJob(OperationListInitParams initParams) {
        super(initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecuteParams executeParams) {

//        LOG.debug("Job started " + getClass().getSimpleName());
//        HadoopJavadocJobExecuteParameters parameters = configurationApi.getJobParameters(OperationListJob.class);
//        ArtifactDataOperationResult result = downloadToStorageOperation.execute(parameters.getDownloadToStorageOperationParameters());
//        LOG.debug("Job finished " + getClass().getSimpleName());
        return EmptyResult.instance;
    }
}
