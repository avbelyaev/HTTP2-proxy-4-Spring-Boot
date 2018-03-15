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

//    /**
//     * Tomcat HTTP/2
//     */
//    @Bean
//    public EmbeddedServletContainerCustomizer tomcatCustomizer() {
//        return (container) -> {
//            if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                ((TomcatEmbeddedServletContainerFactory) container)
//                        .addConnectorCustomizers((connector) -> {
//                            connector.addUpgradeProtocol(new Http2Protocol());
//                        });
//            }
//        };
//    }

    /**
     * Undertow HTTP/2
     */
    @Configuration
    protected static class UndertowHttp2Configuration {

        @Bean
        public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
            UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
            factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
            return factory;
        }

    }

//    /**
//     * Jetty HTTP/2
//     */
//    @Bean
//    public EmbeddedServletContainerCustomizer customizer() {
//        return container -> {
//            if (container instanceof JettyEmbeddedServletContainerFactory) {
//                ((JettyEmbeddedServletContainerFactory) container)
//                        .addServerCustomizers(server -> {
//                                    ServerConnector sc = (ServerConnector) server
//                                            .getConnectors()[0];
//                                    sc.addConnectionFactory(
//                                            new HTTP2CServerConnectionFactory(new HttpConfiguration())
//                                    );
//                                }
//                        );
//            }
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(Http2TrialApplication.class, args);
    }
}
