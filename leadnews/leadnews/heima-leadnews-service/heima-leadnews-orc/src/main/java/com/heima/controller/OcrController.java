package com.heima.controller;

import com.heima.model.orc.dto.OcrResult;
import com.heima.model.orc.enums.LanguageTypeEnum;
import com.heima.service.TesseractServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本服务参考：<a href="http://t.csdnimg.cn/XxabO">【SpringBoot框架篇】36.整合Tess4J搭建提供图片文字识别的Web服务</a>
 *
 * @author Dominick Li
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class OcrController {

    @Resource
    private TesseractServer tesseractServer;

    /**
     * OCR识别  /ocr/chi_sim          /ocr/eng
     *
     * @param language 使用的模型语种  chi_sim=简体中文  eng=英文
     * @param file     需要识别的图片
     */
    @PostMapping("/ocr/{language}")
    public OcrResult recognize(@PathVariable String language, MultipartFile file) {
        try {
            // 对图片进行文字识别
            return tesseractServer.ocrImage(LanguageTypeEnum.getLanguageByType(language), file);
        } catch (Exception e) {
            log.error("error:{}", e.getMessage());
            return OcrResult.fail(e.getMessage());
        }
    }

}
