package com.EurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
//@RestController
@ComponentScan(basePackages = "com.CloudEureka.controller")
public class EurekaClient2Application {
	
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
        SpringApplication.run(EurekaClient2Application.class, args);
    }

}
