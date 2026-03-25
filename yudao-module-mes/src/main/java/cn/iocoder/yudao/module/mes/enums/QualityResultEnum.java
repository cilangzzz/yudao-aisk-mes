package cn.iocoder.yudao.module.mes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 检验结果枚举
 */
@Getter
@AllArgsConstructor
public enum QualityResultEnum {

    PASS(0, "合格"),
    FAIL(1, "不合格");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(QualityResultEnum::getResult).toArray();

    /**
     * 结果
     */
    private final Integer result;
    /**
     * 名称
     */
    private final String name;

    /**
     * 根据结果获取名称
     */
    public static String getNameByResult(Integer result) {
        if (result == null) {
            return "";
        }
        for (QualityResultEnum value : values()) {
            if (value.getResult().equals(result)) {
                return value.getName();
            }
        }
        return "";
    }
}