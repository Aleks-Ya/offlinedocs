package ru.yaal.offlinedocs.impl.artifact.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactTypeFactory;

import java.util.Map;

/**
 * @author Yablokov Aleksey
 */
@Component
public class ArtifactTypeFactoryImpl implements ArtifactTypeFactory {
    private final Map<String, ArtifactType> types;

    @Autowired
    public ArtifactTypeFactoryImpl(Map<String, ArtifactType> types) {
        this.types = types;
    }

    @Override
    public ArtifactType getTypeById(String artifactTypeId) {
        return types.get(artifactTypeId);
    }
}
