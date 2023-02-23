package org.acme.openshift.remotedev;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.eclipse.jkube.kit.remotedev.LocalService;
import org.eclipse.jkube.kit.remotedev.RemoteDevelopmentConfig;
import org.eclipse.jkube.kit.remotedev.RemoteService;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;

@ApplicationScoped
public class RemoteDevelopmentConfiguration {

    private RemoteDevelopmentConfig config;

    public RemoteDevelopmentConfig getConfig() {
        return config;
    }

    public void init(JsonObject jsonConfig) {
        RemoteDevelopmentConfig.RemoteDevelopmentConfigBuilder builder = RemoteDevelopmentConfig.builder();
        JsonArray localServices = jsonConfig.getJsonArray("localServices");
        if (localServices != null) {
            builder.localServices(localServices.stream().map(o -> {
                JsonObject j = (JsonObject) o;
                return LocalService.builder()
                        .serviceName(j.getString("serviceName"))
                        .type(j.getString("type"))
                        .port(j.getInteger("port")).build();
            }).collect(Collectors.toList()));
        }
        JsonArray remoteServices = jsonConfig.getJsonArray("remoteServices");
        if (remoteServices != null) {
            builder.remoteServices(remoteServices.stream().map(o -> {
                JsonObject j = (JsonObject) o;
                return RemoteService.builder()
                        .hostname(j.getString("hostname"))
                        .port(j.getInteger("port"))
                        .localPort(j.getInteger("localPort"))
                        .build();
            }).collect(Collectors.toList()));
        }
        config = builder.build();
    }

}
