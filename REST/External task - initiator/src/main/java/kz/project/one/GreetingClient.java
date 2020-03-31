package kz.project.one;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("client2")
public interface GreetingClient {
    @RequestMapping("/greeting")
    String greeting();

    @GetMapping("/hello")
    String hello();
}

