package org.acme.openshift.remotedev;

import org.eclipse.jkube.kit.common.KitLogger;
import org.eclipse.jkube.kit.common.util.Slf4jKitLogger;
import org.slf4j.Logger;

public class LoggerFactory {

    private static final KitLogger LOGGER = initLogger();

    private static KitLogger initLogger() {
        Logger logger = org.slf4j.LoggerFactory.getLogger("remote-dev");
        return new Slf4jKitLogger(logger);
    }

    public static KitLogger getLogger() {
        return LOGGER;
    }

}
