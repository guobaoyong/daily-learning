package qqzsh.top.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:21
 * @description
 */
public class XmlBuilder {

    /**
     * 将XML转为指定的POJO
     * @param clazz
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public static Object xmlStrToObject(Class<?> clazz, String xmlStr) throws Exception {
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        // XML 转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);

        xmlObject = unmarshaller.unmarshal(reader);

        if (null != reader) {
            reader.close();
        }
        return xmlObject;
    }
}
