package kz.project.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.logging.Logger;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient

public class External_initiator {
    private final static Logger LOGGER = Logger.getLogger(External_initiator.class.getName());
   @Autowired
    TestService testService;

    public static void main(String... args) {
        SpringApplication.run(External_initiator.class,args);

    }
}












