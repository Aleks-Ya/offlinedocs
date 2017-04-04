package ru.yaal.offlinedocs.impl.execution.operation.download.bytes;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;
import ru.yaal.offlinedocs.impl.execution.operation.ByteArrayOpResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadHelper;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class DownloadToByteArrayOp
        extends AbstractOp<DownloadToByteArrayInitParams, EmptyExecuteParams, ByteArrayOpResult> {

    private final Logger LOG = LoggerFactory.getLogger(DownloadToByteArrayOp.class);

    public DownloadToByteArrayOp(DownloadToByteArrayInitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public ByteArrayOpResult execute(EmptyExecuteParams executeParams) {
        URL url = getInitParams().getArtifactUrl();
        LOG.debug("Start downloading " + url);
        InputStream is = netApi.openUrl(url);
        byte[] bytes = DownloadHelper.inputStreamToByteArray(is, getInitParams().getLogEveryBytes());
        LOG.debug("URL downloaded '{}' ({} bytes)", url, bytes.length);
        return new ByteArrayOpResult(bytes);
    }
}
