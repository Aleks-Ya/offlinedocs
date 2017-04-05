package ru.yaal.offlinedocs.impl.execution.operation.download;

import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;
import ru.yaal.offlinedocs.impl.execution.operation.ByteArrayOpResult;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class DownloadToByteArrayOp
        extends AbstractOp<DownloadToByteArrayOp.InitParams, EmptyExecuteParams, ByteArrayOpResult> {

    private final Logger LOG = LoggerFactory.getLogger(DownloadToByteArrayOp.class);

    public DownloadToByteArrayOp(InitParams initParams) {
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

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final URL artifactUrl;
        private final int logEveryBytes;

        public InitParams(URL artifactUrl) {
            this.artifactUrl = artifactUrl;
            logEveryBytes = 1_000_000;
        }

        public InitParams(URL artifactUrl, int logEveryBytes) {
            this.artifactUrl = artifactUrl;
            this.logEveryBytes = logEveryBytes;
        }
    }
}
