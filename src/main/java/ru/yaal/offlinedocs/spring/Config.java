package ru.yaal.offlinedocs.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yablokov Aleksey
 */
@Configuration
@ComponentScan(basePackages = "ru.yaal.offlinedocs.impl",
        includeFilters = @ComponentScan.Filter(InheritedPrototypeComponent.class))
public class Config {
}
