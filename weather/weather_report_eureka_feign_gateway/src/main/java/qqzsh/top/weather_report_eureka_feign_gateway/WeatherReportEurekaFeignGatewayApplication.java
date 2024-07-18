package qqzsh.top.weather_report_eureka_feign_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WeatherReportEurekaFeignGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherReportEurekaFeignGatewayApplication.class, args);
    }

}
