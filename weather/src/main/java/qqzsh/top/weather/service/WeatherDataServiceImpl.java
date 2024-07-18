package qqzsh.top.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import qqzsh.top.weather.pojo.WeatherResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 16:34
 * @description
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIMEOUT = 1800L;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存中
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200){
            strBody = respString.getBody();
        }
        // 数据写入缓存
        ops.set(key,strBody,TIMEOUT, TimeUnit.SECONDS);
    }

    private WeatherResponse doGetWeather(String uri) {
        String key = uri;
        String strBody = null;
        WeatherResponse resp = null;
        ObjectMapper mapper = new ObjectMapper();
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        // 先查缓存，缓存有的取缓存中的数据
        if (stringRedisTemplate.hasKey(key)) {
            logger.info("Redis has data");
            strBody = ops.get(key);
        } else {
            logger.info("Redis don't has data");
            // 缓存没有，再调用服务接口来获取
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

            if (respString.getStatusCodeValue() == 200){
                strBody = respString.getBody();
            }
            // 数据写入缓存
            ops.set(key,strBody,TIMEOUT, TimeUnit.SECONDS);
        }
        try{
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("Error!", e);
        }
        return resp;
    }
}