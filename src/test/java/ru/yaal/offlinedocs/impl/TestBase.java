package ru.yaal.offlinedocs.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yaal.offlinedocs.api.artifact.ArtifactFactory;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactTypeFactory;
import ru.yaal.offlinedocs.api.execution.ExecFactory;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.api.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.storage.OutletStorage;
import ru.yaal.offlinedocs.api.storage.TempStorage;
import ru.yaal.offlinedocs.api.system.FileApi;
import ru.yaal.offlinedocs.spring.Profiles;

/**
 * @author Yablokov Aleksey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles(Profiles.TEST)
public abstract class TestBase {

    @Autowired
    protected ArtifactStorage artifactStorage;

    @Autowired
    protected OutletStorage outletStorage;

    @Autowired
    protected ExecFactory execFactory;

    @Autowired
    protected MockNetApi netApi;

    @Autowired
    protected ArtifactTypeFactory artifactTypeFactory;

    @Autowired
    protected DataAppProps dataAppProps;

    @Autowired
    protected FileApi fileApi;

    @Autowired
    protected TempStorage tempStorage;

    @Autowired
    protected ArtifactFactory artifactFactory;
}
