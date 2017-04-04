package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.artifact.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactTypeFactory;
import ru.yaal.offlinedocs.api.system.NetApi;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.artifact.data.ByteArrayArtifactData;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadHelper;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class DownloadToStorageOp
        extends AbstractOp<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOpResult> {

    private final Logger LOG = LoggerFactory.getLogger(DownloadToStorageOp.class);
    @Autowired
    private NetApi netApi;
    @Autowired
    private ArtifactStorage storage;
    @Autowired
    private ArtifactTypeFactory typeFactory;

    public DownloadToStorageOp(DownloadToStorageInitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public ArtifactDataOpResult execute(EmptyExecuteParams executeParams) {
        DownloadToStorageInitParams params = getInitParams();
        Artifact artifact = new ArtifactImpl(
                params.getArtifactCategory(),
                params.getArtifactName(),
                params.getArtifactVersion(),
                typeFactory.getTypeById(params.getArtifactTypeId()));
        ArtifactData data;
        if (params.getSkipIfExists() && storage.has(artifact)) {
            LOG.debug("Skip exists artifact download: " + artifact);
            data = storage.read(artifact);
        } else {
            URL url = params.getArtifactUrl();
            LOG.debug("Start downloading: " + url);
            InputStream is = netApi.openUrl(url);
            byte[] bytes = DownloadHelper.inputStreamToByteArray(is, params.getLogEveryBytes());
            data = new ByteArrayArtifactData(artifact, bytes);
            storage.save(data);
            LOG.debug("Artifact downloaded: " + artifact);
        }
        return new ArtifactDataOpResult(data);
    }
}