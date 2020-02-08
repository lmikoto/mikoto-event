package io.github.lmikoto.mikoto.event;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EventBusConfiguration.class)
@Documented
public @interface EnableEventBus {

}
 