package ru.yaal.offlinedocs.impl.execution.operation.download;

import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
import ru.yaal.offlinedocs.impl.execution.param.EmptyExecParams;

import java.io.InputStream;
import java.net.URL;

/**
 * TODO possibility works with the download HTML page (e.g. press "Accept" when download JDK Documentation)
 * TODO download sites with documentation
 *
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class DownloadToStorageOp
        extends AbstractOp<DownloadToStorageOp.InitParams, EmptyExecParams, ArtifactDataOpResult> {

    private final Logger LOG = LoggerFactory.getLogger(DownloadToStorageOp.class);

    public DownloadToStorageOp(OpId opId, InitParams initParams) {
        super(opId, initParams);
    }

    @Override
    @SneakyThrows
    public ArtifactDataOpResult execute(EmptyExecParams execParams) {
        InitParams params = getInitParams();
        Artifact artifact = params.getArtifact();
        ArtifactData data;
        if (params.getSkipIfExists() && artifactStorage.has(artifact)) {
            LOG.debug("Skip exists artifact download: " + artifact);
            data = artifactStorage.read(artifact);
        } else {
            URL url = params.getArtifactUrl();
            LOG.debug("Start downloading: " + url);
            InputStream is = netApi.openUrl(url);
            data = artifactStorage.save(artifact, is);
            LOG.debug("Artifact downloaded: " + artifact);
        }
        return new ArtifactDataOpResult(data);
    }

    @Getter
    public static class InitParams extends DownloadToByteArrayOp.InitParams {
        private final Artifact artifact;
        private final Boolean skipIfExists;

        public InitParams(Artifact artifact, URL artifactUrl, Boolean skipIfExists) {
            super(artifactUrl);
            this.artifact = artifact;
            this.skipIfExists = skipIfExists;
        }

        public InitParams(Artifact artifact, URL artifactUrl) {
            this(artifact, artifactUrl, true);
        }
    }
}
