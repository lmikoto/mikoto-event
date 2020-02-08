package io.github.lmikoto.mikoto.event;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
public class EventBusConfiguration implements InitializingBean, DisposableBean {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, EventAdapter> beans = null;


    public void afterPropertiesSet() throws Exception {

        beans = applicationContext.getBeansOfType(EventAdapter.class);
        if (beans != null) {
            for (EventAdapter eventAbstract : beans.values()) {
                EventBusUtils.register(eventAbstract);
            }
        }
    }


    public void destroy() throws Exception {
        if (beans != null) {
            for (EventAdapter eventAbstract : beans.values()) {
                EventBusUtils.unregister(eventAbstract);
            }
        }
    }

}
