package com.example.springbootreactive.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

@RestController
@Slf4j
public class FileRestController {

    @GetMapping(value="/api/filecontent")
    public Flux<String> filecontent() throws IOException {
        Stream<String> linesStream = Files.lines(Path.of("/home/mike/projects/springboot-reactiveweb/script/mylongfile.txt"));
        Flux<String> stringFlux = Flux
                .fromStream(linesStream)
                .map(s -> s + "\r\n")
                .delayElements(Duration.ofMillis(500));
        return stringFlux;
    }
}
