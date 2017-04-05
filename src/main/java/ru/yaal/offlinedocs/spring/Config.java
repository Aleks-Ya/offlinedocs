package ru.yaal.offlinedocs.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Yablokov Aleksey
 */
@Configuration
@ComponentScan(basePackages = "ru.yaal.offlinedocs.impl",
        includeFilters = @ComponentScan.Filter(InheritedPrototypeComponent.class))
@Import(Timer.class)
public class Config {
}
