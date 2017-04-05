package ru.yaal.offlinedocs.impl.execution.operation.download;

import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;

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

    public DownloadToStorageOp(InitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public ArtifactDataOpResult execute(EmptyExecParams execParams) {
        InitParams params = getInitParams();
        Artifact artifact = new ArtifactImpl(
                params.getArtifactCategory(),
                params.getArtifactName(),
                params.getArtifactVersion(),
                artifactTypeFactory.getTypeById(params.getArtifactTypeId()));
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
        private final String artifactCategory;
        private final String artifactName;
        private final String artifactVersion;
        private final String artifactTypeId;
        private final Boolean skipIfExists;

        public InitParams(
                String artifactCategory, String artifactName, String artifactVersion, URL artifactUrl, String artifactTypeId, Boolean skipIfExists) {
            super(artifactUrl);
            this.artifactCategory = artifactCategory;
            this.artifactName = artifactName;
            this.artifactVersion = artifactVersion;
            this.artifactTypeId = artifactTypeId;
            this.skipIfExists = skipIfExists;
        }

        public InitParams(
                String artifactCategory, String artifactName, String artifactVersion, URL artifactUrl, String artifactTypeId) {
            this(artifactCategory, artifactName, artifactVersion, artifactUrl, artifactTypeId, true);
        }
    }
}
