package io.github.lmikoto.mikoto.event;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;


public abstract class EventAdapter<E extends BaseEvent> {

    private static final Logger logger = LoggerFactory.getLogger(EventAdapter.class);

    private static final String METHOD_NAME = "process";

    @Subscribe
    @SuppressWarnings("all")
    public void onEvent(BaseEvent event) {
        if (ReflectionUtils.findMethod(this.getClass(), METHOD_NAME, event.getClass()) != null) {
            try {
                if (!process((E) event)) {
                    logger.warn("handle event {} fail", event.getClass());
                }
            } catch (Exception e) {
                logger.error(String.format("handle event %s exception", event.getClass()), e);
            }
        }
    }

    public abstract boolean process(E e);

}
