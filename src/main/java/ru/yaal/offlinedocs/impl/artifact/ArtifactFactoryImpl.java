package ru.yaal.offlinedocs.impl.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactFactory;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactTypeFactory;

/**
 * @author Yablokov Aleksey
 */
@Component
public class ArtifactFactoryImpl implements ArtifactFactory {
    private final ArtifactTypeFactory artifactTypeFactory;

    @Autowired
    public ArtifactFactoryImpl(ArtifactTypeFactory artifactTypeFactory) {
        this.artifactTypeFactory = artifactTypeFactory;
    }

    @Override
    public Artifact instantiate(String category, String name, String version, String typeId) {
        ArtifactType type = artifactTypeFactory.getTypeById(typeId);
        return new ArtifactImpl(category, name, version, type);
    }
}
