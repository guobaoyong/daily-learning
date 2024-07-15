package qqzsh.top.bingimages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.bingimages.config.StorageService;
import qqzsh.top.bingimages.images.FileZip;

import java.io.File;

@SpringBootApplication
@RestController
public class BingimagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BingimagesApplication.class, args);
    }

    @Value("${zipPath}")
    private String zipPath;

    @Value("${downloadPath}")
    private String downloadPath;

    @Autowired
    private FileZip fileZip;

    @Autowired
    private StorageService storageService;

    /**
     * 文件下载
     * @return
     */
    @GetMapping("/bing")
    public ResponseEntity<Resource> download(){
        // 判断压缩包是否存在
        File file = new File(zipPath + "/bing.zip");
        if (!file.exists()){
            fileZip.zip();
        }
        Resource result = storageService.loadAsResource("bing.zip");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getName() + "\"").body(result);
    }

}
