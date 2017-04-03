package ru.yaal.offlinedocs.impl.execution.operation.download.bytes;

import com.google.common.io.ByteStreams;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.api.system.NetApi;
import ru.yaal.offlinedocs.impl.execution.EmptyInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.ByteArrayOperationResult;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
public class DownloadToByteArrayOperation implements Operation<EmptyInitParams, DownloadToByteArrayExecuteParams, ByteArrayOperationResult> {
    private final Logger LOG = LoggerFactory.getLogger(DownloadToByteArrayOperation.class);
    private final NetApi netApi;

    @Autowired
    public DownloadToByteArrayOperation(NetApi netApi) {
        this.netApi = netApi;
    }

    @Override
    @SneakyThrows
    public ByteArrayOperationResult execute(DownloadToByteArrayExecuteParams parameters) {
        URL url = parameters.getURL();
        LOG.debug("Start downloading " + url);
        InputStream is = netApi.openUrl(url);
        byte[] bytes = ByteStreams.toByteArray(is);
        LOG.debug("URL downloaded '{}' ({} bytes)", url, bytes.length);
        return new ByteArrayOperationResult(bytes);
    }
}
