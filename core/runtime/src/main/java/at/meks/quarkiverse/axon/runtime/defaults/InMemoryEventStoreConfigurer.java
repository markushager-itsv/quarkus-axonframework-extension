package at.meks.quarkiverse.axon.runtime.defaults;

import jakarta.enterprise.context.ApplicationScoped;

import org.axonframework.config.Configurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import at.meks.quarkiverse.axon.runtime.customizations.EventstoreConfigurer;
import io.quarkus.arc.DefaultBean;

@ApplicationScoped
@DefaultBean
public class InMemoryEventStoreConfigurer implements EventstoreConfigurer {

    @Override
    public void configure(Configurer configurer) {
        configurer.configureEmbeddedEventStore(configuration -> new InMemoryEventStorageEngine());
    }

}
