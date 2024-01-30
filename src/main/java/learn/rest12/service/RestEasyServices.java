package learn.rest12.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

import learn.rest12.persist.GuicePersistenceInitializer;
import learn.rest12.resource.StudentResource;

public class RestEasyServices extends Application {

    private Set <Object> singletons = new HashSet <Object> ();

    public RestEasyServices() {
    	Injector injector = Guice.createInjector(new JpaPersistModule("jpa_guice_resteasy"));
    	injector.getInstance(GuicePersistenceInitializer.class);
    	
    	singletons.add(injector.getInstance(StudentResource.class));
    }

    @Override
    public Set <Object> getSingletons() {
        return singletons;
    }
}
