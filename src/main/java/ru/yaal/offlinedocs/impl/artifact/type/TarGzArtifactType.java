package ru.yaal.offlinedocs.impl.artifact.type;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;

/**
 * @author Yablokov Aleksey
 */
@Component(TarGzArtifactType.ID)
public class TarGzArtifactType implements ArtifactType {
    static final String ID = "tar.gz";

    @Override
    public String getFileExtension() {
        return "tar.gz";
    }

    @Override
    public String getId() {
        return ID;
    }
}
