package io.github.lmikoto.mikoto.event;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventBusUtils {

    private static final Logger logger = LoggerFactory.getLogger(EventBusUtils.class);

    private final static EventBus eventBus = new EventBus();

    private final static Executor executor = Executors.newCachedThreadPool();

    public static void post(BaseEvent event) {
        execute(event);
    }

    /***
     * 同步执行
     * @param event
     */
    public static void execute(BaseEvent event) {
        if (event == null) {
            return;
        }
        eventBus.post(event);
    }

    /**
     * 异步提交
     * @param event
     */
    public static void submit(final BaseEvent event) {
        if (event == null) {
            return;
        }
        executor.execute(new Runnable() {
            public void run() {
                eventBus.post(event);
            }
        });
    }


    public static void register(EventAdapter<? extends BaseEvent> handler) {
        if (handler == null) {
            return;
        }
        eventBus.register(handler);
        logger.info("Registered eventAdapter class: {}", handler.getClass());
    }


    public static void unregister(EventAdapter<? extends BaseEvent> handler) {
        if (handler == null) {
            return;
        }
        eventBus.unregister(handler);
        logger.info("Unregisted eventAdapter class: {}", handler.getClass());
    }
}
