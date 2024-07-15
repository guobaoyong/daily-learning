package qqzsh.top.bingimages.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import qqzsh.top.bingimages.images.BingDownLoad;
import qqzsh.top.bingimages.images.FileZip;
import qqzsh.top.bingimages.images.ImageSimilarity;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author zsh
 * @site https://www.qqzsh.top
 * @create 2020-03-26 20:53
 * @Description 定时下载图片
 */
@Configuration
@EnableScheduling
@Slf4j
public class BingScheduleTask {

    @Autowired
    private BingDownLoad bingDownLoad;

    @Autowired
    private ImageSimilarity imageSimilarity;

    @Autowired
    private FileZip fileZip;

    /**
     * 定时下载图片
     * 进行相似度检测
     * 进行打包
     */
    @Scheduled(cron = "* 35 12 * * ?")
    private void configureTasks() throws IOException {
        log.info("执行定时任务，当前时间"+ LocalDateTime.now());
        log.info("开始执行下载任务");
        long start1 = System.currentTimeMillis();
        bingDownLoad.download();
        long end1 = System.currentTimeMillis();
        log.info("下载任务完成，耗时："+(end1-start1)/1000+"秒");
        log.info("开始执行查重任务");
        imageSimilarity.repeat();
        long end2 = System.currentTimeMillis();
        log.info("查重任务完成，耗时："+(end2-end1)/1000+"秒");
        log.info("开始执行压缩任务");
        fileZip.zip();
        long end3 = System.currentTimeMillis();
        log.info("压缩任务完成，耗时："+(end3-end2)/1000+"秒");
    }
}
