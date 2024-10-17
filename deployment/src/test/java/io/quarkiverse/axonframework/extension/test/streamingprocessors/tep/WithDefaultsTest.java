package io.quarkiverse.axonframework.extension.test.streamingprocessors.tep;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import jakarta.inject.Inject;

import org.axonframework.config.Configuration;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkiverse.axonframework.extension.test.AbstractConfigurationTest;
import io.quarkus.test.QuarkusUnitTest;

public class WithDefaultsTest extends AbstractConfigurationTest {

    @RegisterExtension
    static final QuarkusUnitTest config = application(
            javaArchiveBase().addAsResource(propertiesFile("/streamingeventprocessors/tep/withDefaults.properties"),
                    "application.properties"));

    @Inject
    Configuration configuration;

    @Test
    void eventprocessorIsTracking() {
        Optional<EventProcessor> eventProcessorOptional = configuration.eventProcessingConfiguration().eventProcessor(
                "io.quarkiverse.axonframework.extension.test.projection");

        // I don't like this kind of assertion, but I found no better way, how to validate that it is a tracking processor
        assertThat(eventProcessorOptional).isPresent().get()
                .isInstanceOf(TrackingEventProcessor.class);
    }

}
