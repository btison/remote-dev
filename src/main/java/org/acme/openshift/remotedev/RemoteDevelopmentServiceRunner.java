package org.acme.openshift.remotedev;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.IOUtils;
import org.eclipse.jkube.kit.common.KitLogger;
import org.eclipse.jkube.kit.remotedev.RemoteDevelopmentService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class RemoteDevelopmentServiceRunner {

    private final KitLogger logger = LoggerFactory.getLogger();

    @Inject
    KubernetesClient client;

    @Inject
    RemoteDevelopmentConfiguration configuration;

    RemoteDevelopmentService remoteDevelopmentService;

    public void execute(String configFile) {
        configuration.init(readConfiguration(configFile));
        remoteDevelopmentService = new RemoteDevelopmentService(logger, client, configuration.getConfig());
        remoteDevelopmentService.start();
    }

    public void start() {
        remoteDevelopmentService.start();
    }

    public void stop() {
        remoteDevelopmentService.stop();
    }

    private JsonObject readConfiguration(String configFile) {
        try {
            FileInputStream fis = new FileInputStream(configFile);
            String data = IOUtils.toString(fis, StandardCharsets.UTF_8);
            return new JsonObject(data);
        } catch (Exception e) {
            logger.error("Exception while reading the remote development configuration file");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public RemoteDevelopmentService getRemoteDevelopmentService() {
        return remoteDevelopmentService;
    }

}
