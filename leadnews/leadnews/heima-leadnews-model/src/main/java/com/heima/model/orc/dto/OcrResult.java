package com.heima.model.orc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Dominick Li
 */
@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OcrResult {

    /**
     * 是否识别成功
     */
    public boolean success;

    /**
     * 识别时间
     */
    public long time;

    /**
     * 识别结果
     */
    public String[] texts;

    /**
     * 异常信息
     */
    public String msg;

    public static OcrResult success(String text, long time) {
        return OcrResult.builder()
                .success(true)
                .texts(text.split("\n"))
                .time(time)
                .build();
    }

    public static OcrResult fail(String msg) {
        return OcrResult.builder()
                .success(false)
                .msg(msg)
                .build();
    }

}
