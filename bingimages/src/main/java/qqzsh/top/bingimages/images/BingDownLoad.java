package qqzsh.top.bingimages.images;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import qqzsh.top.bingimages.entity.IPBean;
import qqzsh.top.bingimages.entity.IPList;
import qqzsh.top.bingimages.entity.IPSpider;
import qqzsh.top.bingimages.utils.IPUtils;
import qqzsh.top.bingimages.utils.ProxyUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zsh
 * @site https://www.qqzsh.top
 * @create 2020-03-26 21:06
 * @Description 下载图片
 */
@Configuration
public class BingDownLoad {

    private static final String URL = "https://bing.ioliu.cn/v1";

    private static final String MY_API = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";

    private static List<IPBean> ipBeanList = null;

    @Value("${downloadPath}")
    private String downloadPath;

    /**
     * 每日自动获取bing壁纸
     */
    public void download(){
        // 判断当前文件夹是否有文件
        if (check()){
            spider();
            firstDown();
        }else {
            try {
                HttpResponse execute = HttpRequest.get(MY_API).execute();
                JSONArray images = JSONObject.parseObject(execute.body()).getJSONArray("images");
                for (int i = 0; i < images.size(); i++) {
                    download("https://cn.bing.com/"+images.getJSONObject(i).getString("url"), images.getJSONObject(i).getString("enddate")+".jpg",downloadPath);
                }
            }catch (Exception e){ }
        }
    }

    /**
     * 判断当前文件夹是否有文件
     * @return
     */
    public boolean check(){
        try {
            return new File(downloadPath).listFiles().length == 0;
        }catch (Exception e){
            return new File(downloadPath).listFiles() == null;
        }
    }

    /**
     * 初次下载
     */
    public void firstDown(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String path = URL + "?d={0}";
        // 记录使用的IP地址Index
        int index = 0;
        for (int i = 1000;i< 1500 ; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                download(MessageFormat.format(path,String.valueOf(i)), simpleDateFormat.format(new Date())+".jpg",downloadPath);
                System.out.println(MessageFormat.format(path,String.valueOf(i)));
            }catch (Exception e){
                // 进行代理切换
                for (int j = index; j < ipBeanList.size(); j++) {
                    IPBean ipBean = ipBeanList.get(j);
                    ProxyUtils.setGlobalProxy(ipBean);
                    // 检测代理是否生效
                    if (IPUtils.getMyIp(ipBean).equalsIgnoreCase(ipBean.getIp())){
                        index = j+1;
                        break;
                    }
                }
                try {
                    download(MessageFormat.format(path,String.valueOf(i)), simpleDateFormat.format(new Date())+".jpg",downloadPath);
                } catch (IOException ex) { }
                System.out.println(MessageFormat.format(path,String.valueOf(i)));
            }
        }
    }

    /**
     * 下载图片
     * @param urlString url地址
     * @param filename 文件名
     * @param savePath 保存路径
     * @throws IOException
     */
    public static void download(String urlString, String filename,String savePath) throws IOException {
        // 构造URL
        java.net.URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        //伪装浏览器
        con.addRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        String split = "/";
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            split = "\\";
        }
        OutputStream os = new FileOutputStream(sf.getPath()+split+filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    /**
     * 进行IP代理爬取
     */
    public static void spider(){
        //进行IP代理爬取
        System.out.println("Start: ");

        IPSpider spider = new IPSpider();
        List<IPBean> list = spider.crawlHttp(1);

        System.out.println("爬取数量：" + list.size());

        Gson gson = new Gson();
        for (IPBean ipBean : list) {
            System.out.println(gson.toJson(ipBean));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean valid = IPUtils.isValid(ipBean);
                    if (valid){
                        IPList.add(ipBean);
                    }
                    IPList.increase();
                }
            }).start();
        }

        while (true){
            // 判断所有副线程是否完成
            if (IPList.getCount() == list.size()){
                System.out.println("有效数量：" + IPList.getSize());
                break;
            }
        }
        ipBeanList = IPList.getIpBeanList();
    }
}
