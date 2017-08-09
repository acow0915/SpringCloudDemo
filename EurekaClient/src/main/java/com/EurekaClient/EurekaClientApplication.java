package com.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CloudEureka.controller.WebAccountsService;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
//@RestController
@ComponentScan(basePackages = "com.CloudEureka.controller")
public class EurekaClientApplication {
	
//	@Autowired
//    private DiscoveryClient client;
	
	@LoadBalanced    // Make sure to create the load-balanced template
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
//	@Bean
//    public WebAccountsService webAccountsService() {
//        return new WebAccountsService("CLIENT-SERVICE");
//    }


	
//	@RequestMapping(value = "/getName" ,method = RequestMethod.GET)
//	public String clientName(){
//		ServiceInstance instance = client.getLocalServiceInstance();
//		String data = "HOST : " + instance.getHost() + "," +
//					  "PORT : " + instance.getPort() + "," +
//				      "SERVICE_ID : " + instance.getServiceId();
//		
//		String account = accountsService().getByNumber("");
//		
//		return data + "%%%" + account;
//	}
	

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
