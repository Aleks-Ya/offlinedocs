package ru.yaal.offlinedocs.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.yaal.offlinedocs.spring.Config;

/**
 * @author Yablokov Aleksey
 */
@Configuration
//@ComponentScan(basePackages = "ru.yaal.offlinedocs.impl",
//        includeFilters = @ComponentScan.Filter(InheritedPrototypeComponent.class))
@Import(Config.class)
class TestConfig {
}
