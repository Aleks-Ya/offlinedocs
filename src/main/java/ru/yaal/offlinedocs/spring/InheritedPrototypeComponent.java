package ru.yaal.offlinedocs.spring;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Yablokov Aleksey
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope("prototype")
//@Lazy
@Inherited
public @interface InheritedPrototypeComponent {
}
