package ind.venture.objectivenotionclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @GetMapping("/health")
    public void health() {
        log.info("health check");
    }
}
