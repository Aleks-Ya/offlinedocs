package ru.yaal.offlinedocs.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * TODO use InheritedPrototypeComponent on AbstractOp and AbstractJob
 *
 * @author Yablokov Aleksey
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope("prototype")
@Inherited
public @interface InheritedPrototypeComponent {
}
