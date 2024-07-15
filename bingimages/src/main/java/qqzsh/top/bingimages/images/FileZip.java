package qqzsh.top.bingimages.images;

import cn.hutool.core.util.ZipUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author zsh
 * @site https://www.qqzsh.top
 * @create 2020-03-26 21:19
 * @Description 文件压缩
 */
@Configuration
public class FileZip {

    @Value("${zipPath}")
    private String zipPath;

    @Value("${downloadPath}")
    private String downloadPath;

    public void zip(){
        // 先判断文件是否存在，存在删除
        try {
            File file = new File(zipPath+"/bing.zip");
            if (file.exists()){
                file.delete();
            }
        }catch (Exception e){ }
        try {
            ZipUtil.zip(downloadPath, zipPath+"/bing.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
