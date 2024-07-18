package qqzsh.top.weather.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import qqzsh.top.weather.pojo.City;
import qqzsh.top.weather.pojo.CityList;
import qqzsh.top.weather.util.XmlBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:23
 * @description
 */
@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() throws Exception {
        //读取xml文件
        Resource resource = new ClassPathResource("city.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        //xml转为Java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());
        return cityList.getCityList();
    }
}
