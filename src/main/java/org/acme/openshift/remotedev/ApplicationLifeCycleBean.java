package org.acme.openshift.remotedev;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.jkube.kit.common.KitLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class ApplicationLifeCycleBean {

    @Inject
    RemoteDevelopmentServiceRunner runner;

    private static final KitLogger LOGGER = LoggerFactory.getLogger();

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
        runner.stop();
    }

}
