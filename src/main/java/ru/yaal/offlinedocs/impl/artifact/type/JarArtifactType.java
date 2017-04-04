package ru.yaal.offlinedocs.impl.artifact.type;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;

/**
 * @author Yablokov Aleksey
 */
@Component(JarArtifactType.ID)
class JarArtifactType implements ArtifactType {
    static final String ID = "jar";

    @Override
    public String getFileExtension() {
        return "jar";
    }

    @Override
    public String getId() {
        return ID;
    }
}
