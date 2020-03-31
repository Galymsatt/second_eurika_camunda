package kz.project.one;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TestService {
//    @Autowired
//    ServiceFeignClient serviceFeignClient;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;


    @Autowired
    private GreetingClient greetingClient;


    @PostConstruct
    public  void test() {
        ExternalTaskClient client = ExternalTaskClient.create()
//                .baseUrl("http://localhost:8080/rest")
                .baseUrl(getBaseUrl()+"rest")
                .asyncResponseTimeout(10000)
                .build();


         client.subscribe("initiator")
                 .lockDuration(1000)
                 .handler((externalTask, externalTaskService) -> {

                    String name = externalTask.getVariable("name");
                    Map<String, Object> newVars = new HashMap<>();
//                    newVars.put("greeting", "Hello my name is "+name);
                     newVars.put("greeting", greetingClient.hello()+name);
                    externalTaskService.complete(externalTask, newVars);
                 })
                .open();
    }

    public String getBaseUrl() {

        Application application = eurekaClient.getApplication("camunda-server");
        InstanceInfo instance = application.getInstances().get(0);

//        System.out.println("getHomePageUrl: "+instance.getHomePageUrl());

        return instance.getHomePageUrl();
    }


}
