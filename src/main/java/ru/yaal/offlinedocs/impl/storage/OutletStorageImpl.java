package ru.yaal.offlinedocs.impl.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.api.storage.OutletStorage;
import ru.yaal.offlinedocs.api.system.FileApi;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
class OutletStorageImpl implements OutletStorage {

    private final File outletDir;
    private final String sep;

    @Autowired
    public OutletStorageImpl(DataAppProps dataAppProps, FileApi fileApi) {
        outletDir = dataAppProps.getOutletDir();
        sep = fileApi.getFileSeparator();
    }

    @Override
    public File getArtifactDir(Artifact artifact) {
        return new File(outletDir, artifact.getCategory() + sep + artifact.getName() + sep + artifact.getVersion());
    }

    @Override
    public File getArtifactFile(Artifact artifact) {
        String artifactFile = String.format("%s-%s.%s",
                artifact.getName(), artifact.getVersion(), artifact.getType().getFileExtension());
        return new File(getArtifactDir(artifact), artifactFile);
    }

    @Override
    public boolean isArtifactExists(Artifact artifact) {
        File file = getArtifactFile(artifact);
        File dir = getArtifactDir(artifact);
        return file.exists() || (dir.list() != null && dir.list().length > 0);
    }
}
