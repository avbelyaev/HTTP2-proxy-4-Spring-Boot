package ru.belyaev;

import io.undertow.UndertowOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.belyaev.filters.pre.DummyFilter;

/**
 * @author avbelyaev
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

    @Bean
    public DummyFilter dummyFilter() {
        return new DummyFilter();
    }

    /**
     * Undertow HTTP/2
     */
    @Configuration
    protected static class UndertowHttp2Configuration {

        private static final int INITIAL_WINDOW_SIZE_BYTES = 1024 * 1024; // 1 MiB

        @Bean
        public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
            UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
            factory.addBuilderCustomizers(builder -> {
                builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
                builder.setServerOption(UndertowOptions.HTTP2_SETTINGS_INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE_BYTES);
            });
            return factory;
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
