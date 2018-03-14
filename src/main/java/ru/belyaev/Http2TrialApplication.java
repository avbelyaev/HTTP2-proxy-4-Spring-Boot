package ru.belyaev;

import io.undertow.UndertowOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author avbelyaev
 */
@SpringBootApplication
public class Http2TrialApplication {

        @Configuration
        protected static class UndertowHttp2Configuration
        {

            @Bean
            public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory()
            {
                UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
                factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
                return factory;
            }

        }

        public static void main(String[] args) {
            SpringApplication.run(Http2TrialApplication.class, args);
        }
    }
