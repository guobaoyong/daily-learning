package qqzsh.top.contentcenter;

import org.springframework.web.client.RestTemplate;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 20:57
 * @Description
 */
public class SentinelTest {

    public static void main1(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10000; i++) {
            String object = restTemplate.getForObject("http://localhost:8010/actuator/sentinel", String.class);
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10000; i++) {
            String object = restTemplate.getForObject("http://localhost:8010/test-a", String.class);
            System.out.println("-----" + object + "-----");
        }
    }
}
