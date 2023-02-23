package org.acme.openshift.remotedev;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;

@QuarkusMain
public class RemoteDevelopmentApplication implements QuarkusApplication {

    @Inject
    RemoteDevelopmentServiceRunner remoteDevelopmentServiceRunner;

    @Override
    public int run(String... args) throws Exception {
        if (args.length == 0) {
            return -1;
        }
        try {
            remoteDevelopmentServiceRunner.execute(args[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Quarkus.waitForExit();
        return 0;
    }
}
