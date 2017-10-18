package com.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringCloudApplication
public class HystrixDashboardApplication 
{
    public static void main( String[] args )
    {
    	//http://localhost:8889/hystrix 判斷是否有啟動
    	SpringApplication.run(HystrixDashboardApplication.class, args);
    	//http://localhost:2101/hystrix.stream 監控範例
    }
}
