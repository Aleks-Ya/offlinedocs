package ru.yaal.offlinedocs.impl.artifact.type;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;

/**
 * @author Yablokov Aleksey
 */
@Component(PdfArtifactType.ID)
class PdfArtifactType implements ArtifactType {
    static final String ID = "pdf";

    @Override
    public String getFileExtension() {
        return "pdf";
    }

    @Override
    public String getId() {
        return ID;
    }
}
