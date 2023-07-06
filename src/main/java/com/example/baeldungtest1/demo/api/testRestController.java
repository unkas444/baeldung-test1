package com.example.baeldungtest1.demo.api;

import com.example.baeldungtest1.demo.model.Foo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
public class testRestController {

    /**
     * Endpoint that simply returns/emits one resource every second.
     */
    @GetMapping(value = "/foo", produces = (MediaType.TEXT_EVENT_STREAM_VALUE))
    public Flux<Foo> emitEverySecond() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Foo> fooStream = Flux.fromStream(Stream.generate(() -> new Foo(1L,"test1")));
        return Flux.zip(interval, fooStream).map(Tuple2::getT2);
    }
}
