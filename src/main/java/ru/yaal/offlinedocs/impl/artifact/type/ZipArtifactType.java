package ru.yaal.offlinedocs.impl.artifact.type;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;

/**
 * @author Yablokov Aleksey
 */
@Component(ZipArtifactType.ID)
class ZipArtifactType implements ArtifactType {
    static final String ID = "zip";

    @Override
    public String getFileExtension() {
        return "zip";
    }

    @Override
    public String getId() {
        return ID;
    }
}
