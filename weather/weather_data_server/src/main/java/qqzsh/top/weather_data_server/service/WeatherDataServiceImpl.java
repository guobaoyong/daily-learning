package qqzsh.top.weather_data_server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import qqzsh.top.weather_data_server.pojo.WeatherResponse;

import java.io.IOException;

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
            // 缓存没有，抛出异常
            throw new RuntimeException("Redis don't has data");
        }
        try{
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("Error!", e);
        }
        return resp;
    }
}