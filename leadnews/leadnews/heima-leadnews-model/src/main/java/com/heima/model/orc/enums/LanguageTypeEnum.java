package com.heima.model.orc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自行扩展需要的OCR引擎语种
 *
 * @author Dominick Li
 **/
@Getter
@AllArgsConstructor
public enum LanguageTypeEnum {

    CHINESE_SIMPLIFIED("chi_sim", "简体中文"),
    ENGLISH("eng", "英文");

    private final String value;
    private final String language;

    /**
     * 根据语种查找枚举对象
     *
     * @param language 前端传的参数
     * @return 没找到对应的则默认使用简单中文
     */
    public static LanguageTypeEnum getLanguageByType(String language) {
        for (LanguageTypeEnum languageTypeEnum : LanguageTypeEnum.values()) {
            if (languageTypeEnum.getValue().equals(language)) {
                return languageTypeEnum;
            }
        }
        return CHINESE_SIMPLIFIED;
    }

}
