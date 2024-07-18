package com.heima.service;

import com.heima.model.orc.dto.OcrResult;
import com.heima.model.orc.enums.LanguageTypeEnum;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dominick Li
 */
@Slf4j
@Service
public class TesseractServer {

    @Value("${tess4j.datapath}")
    private String datapath;

    private final static Map<LanguageTypeEnum, ITesseract> SERVER_INSTANCE = new HashMap<>();

    /**
     * 根据枚举配置的语种初始化Tesseract引擎
     */
    @PostConstruct
    public void init() {
        ITesseract iTesseract;
        for (LanguageTypeEnum languageTypeEnum : LanguageTypeEnum.values()) {
            iTesseract = new Tesseract();
            //设置训练集文件存储目录
            iTesseract.setDatapath(datapath);
            //设置语种
            iTesseract.setLanguage(languageTypeEnum.getValue());
            SERVER_INSTANCE.put(languageTypeEnum, iTesseract);
            log.info("load {}  ocr model", languageTypeEnum.getLanguage());
        }
    }

    /**
     * ocr识别
     */
    private OcrResult doOcr(ITesseract iTesseract, BufferedImage bufferedImage) throws Exception {
        String result;
        long startTime = System.currentTimeMillis();
        result = iTesseract.doOCR(bufferedImage);
        long time = System.currentTimeMillis() - startTime;
        log.info("Time is： {} 毫秒", time);
        return OcrResult.success(result, time);
    }

    public OcrResult ocrImage(LanguageTypeEnum languageTypeEnum, File file) throws Exception {
        return doOcr(SERVER_INSTANCE.get(languageTypeEnum), ImageIO.read(file));
    }

    public OcrResult ocrImage(LanguageTypeEnum languageTypeEnum, MultipartFile file) throws Exception {
        return ocrImage(languageTypeEnum, ImageIO.read(new ByteArrayInputStream(file.getBytes())));
    }

    public OcrResult ocrImage(LanguageTypeEnum languageTypeEnum, BufferedImage bufferedImage) throws Exception {
        return doOcr(SERVER_INSTANCE.get(languageTypeEnum), bufferedImage);
    }
}
