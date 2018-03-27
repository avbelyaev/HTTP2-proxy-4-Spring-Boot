package ru.belyaev;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author anton.belyaev@bostongene.com
 */
@RestController
@RequestMapping("/sse")
class WebFluxController {

    @GetMapping("/mono")
    Mono<String> mono() {
        return Mono.just("Hello WebFlux!");
    }

    @GetMapping("/flux")
    Flux<String> randomStrings() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(Long::intValue)
                .map(WebFluxController::generateRandomNumbericString);
    }

    private static String generateRandomNumbericString(int t) {
        return 0 == t % 2
                ? " tick_" + t
                : " tack_" + t;
    }
}
