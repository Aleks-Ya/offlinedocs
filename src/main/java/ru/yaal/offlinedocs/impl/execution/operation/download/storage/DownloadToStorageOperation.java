package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactData;
import ru.yaal.offlinedocs.api.artifact.ArtifactStorage;
import ru.yaal.offlinedocs.api.system.NetApi;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.artifact.ByteArrayArtifactData;
import ru.yaal.offlinedocs.impl.execution.EmptyInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOperation;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class DownloadToStorageOperation
        extends AbstractOperation<EmptyInitParams, DownloadToStorageExecuteParams, ArtifactDataOperationResult> {

    private final Logger LOG = LoggerFactory.getLogger(DownloadToStorageOperation.class);
    @Autowired
    private NetApi netApi;
    @Autowired
    private ArtifactStorage storage;

    public DownloadToStorageOperation(EmptyInitParams initParameters) {
        super(initParameters);
    }

    @Override
    @SneakyThrows
    public ArtifactDataOperationResult execute(DownloadToStorageExecuteParams parameters) {
        URL url = parameters.getURL();
        LOG.debug("Start downloading " + url);
        InputStream is = netApi.openUrl(url);
        byte[] bytes = inputStreamToByteArray(is);
        Artifact artifact = new ArtifactImpl(parameters.getArtifactName(), parameters.getArtifactVersion(), bytes.length);
        ArtifactData data = new ByteArrayArtifactData(artifact, bytes);
        storage.save(data);
        LOG.debug("Artifact downloaded " + artifact);
        return new ArtifactDataOperationResult(data);
    }

    private byte[] inputStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        long loaded = 0;
        int b;
        while ((b = bis.read()) != -1) {
            loaded++;
            if (loaded % 100_000 == 0) {
                LOG.debug("Downloaded {} bytes", loaded);
            }
            out.write(b);
        }
        return out.toByteArray();
    }
}
