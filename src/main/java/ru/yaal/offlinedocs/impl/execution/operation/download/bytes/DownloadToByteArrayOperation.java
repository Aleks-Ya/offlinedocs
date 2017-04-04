package ru.yaal.offlinedocs.impl.execution.operation.download.bytes;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.system.NetApi;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOperation;
import ru.yaal.offlinedocs.impl.execution.operation.ByteArrayOperationResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadHelper;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class DownloadToByteArrayOperation
        extends AbstractOperation<DownloadToByteArrayInitParams, EmptyExecuteParams, ByteArrayOperationResult> {

    private final Logger LOG = LoggerFactory.getLogger(DownloadToByteArrayOperation.class);

    @Autowired
    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    private NetApi netApi;

    public DownloadToByteArrayOperation(DownloadToByteArrayInitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public ByteArrayOperationResult execute(EmptyExecuteParams executeParams) {
        URL url = getInitParameters().getArtifactUrl();
        LOG.debug("Start downloading " + url);
        InputStream is = netApi.openUrl(url);
        byte[] bytes = DownloadHelper.inputStreamToByteArray(is, getInitParameters().getLogEveryBytes());
        LOG.debug("URL downloaded '{}' ({} bytes)", url, bytes.length);
        return new ByteArrayOperationResult(bytes);
    }
}
