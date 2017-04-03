package ru.yaal.offlinedocs.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yaal.offlinedocs.spring.Profiles;

/**
 * @author Yablokov Aleksey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles(Profiles.TEST)
public abstract class TestBase {
}
