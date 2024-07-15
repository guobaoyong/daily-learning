package qqzsh.top.bingimages.utils;

import qqzsh.top.bingimages.entity.IPBean;

/**
 * @author zsh
 * @site https://www.qqzsh.top
 * @create 2020-03-16 10:14
 * @Description
 */
public class ProxyUtils {

    /**
     * 设置全局代理
     * @param ipBean
     */
    public static void setGlobalProxy(IPBean ipBean){
        System.setProperty("proxyPort", String.valueOf(ipBean.getPort()));
        System.setProperty("proxyHost", ipBean.getIp());
        System.setProperty("proxySet", "true");
    }

}
