package org.camunda.bpm.rest;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Client2 {

    public static void main(String[] args) {
        SpringApplication.run(Client2.class, args);
    }

    @GetMapping(value = "/getClient2")
    public String getClient2(){

        return "CLIENT2";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello my name is ";
    }

}
