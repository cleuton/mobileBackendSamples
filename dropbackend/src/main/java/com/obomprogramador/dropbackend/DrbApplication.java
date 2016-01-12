package com.obomprogramador.dropbackend;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DrbApplication extends Application<DrbConfiguration> {

    public static void main(String[] args) throws Exception {
        new DrbApplication().run(args);
    }
	
    @Override
    public String getName() {
        return "dropbackend";
    }
    	
	@Override
	public void run(DrbConfiguration configuration, Environment environment) throws Exception {
        final RestResource resource = new RestResource(
                configuration.getDbUrl()
            );
        final LoginResource loginResource = new LoginResource(
                configuration.getDbUrl()
            );
                		
        final DrbCheck healthCheck =
                new DrbCheck();
        environment.healthChecks().register("dropbackend", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(loginResource);
	}

}
